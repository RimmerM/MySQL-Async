package com.rimmer.mysql.protocol.decoder

import com.rimmer.mysql.protocol.CodecExtender
import com.rimmer.mysql.protocol.SqlException
import com.rimmer.mysql.protocol.constants.Type
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufUtil
import org.joda.time.DateTime
import org.joda.time.chrono.ISOChronology
import java.math.BigDecimal
import java.util.*

val booleanType = Boolean::class.javaObjectType
val byteType = Byte::class.javaObjectType
val charType = Char::class.javaObjectType
val shortType = Short::class.javaObjectType
val intType = Int::class.javaObjectType
val longType = Long::class.javaObjectType
val floatType = Float::class.javaObjectType
val doubleType = Double::class.javaObjectType
val dateType = Date::class.java
val dateTimeType = DateTime::class.java
val stringType = String::class.java

fun unknownTarget(targetType: Class<*>?) = SqlException(0, "", "Unknown target type $targetType")

/** Parses a prepare-statement result from the server. */
inline fun readPrepareStatement(packet: ByteBuf, f: (statementId: Int, columnCount: Int, paramCount: Int, warningCount: Int) -> Unit) {
    packet.skipBytes(1)
    val statementId = ByteBufUtil.swapInt(packet.readInt())
    val columnCount = ByteBufUtil.swapShort(packet.readShort())
    val paramCount = ByteBufUtil.swapShort(packet.readShort())
    packet.skipBytes(1)
    val warningCount = ByteBufUtil.swapShort(packet.readShort())

    f(statementId, columnCount.toInt(), paramCount.toInt(), warningCount.toInt())
}

/** Parses a column definition from the server. */
inline fun readColumnDefinition(packet: ByteBuf, f: (catalog: String, database: String, table: String, originalTable: String, name: String, originalName: String, charSet: Int, columnLength: Long, columnType: Short, flags: Short, decimals: Byte) -> Unit) {
    val catalog = packet.readLengthEncodedString()
    val database = packet.readLengthEncodedString()
    val table = packet.readLengthEncodedString()
    val originalTable = packet.readLengthEncodedString()
    val name = packet.readLengthEncodedString()
    val originalName = packet.readLengthEncodedString()

    // Skip the length of fixed-size fields.
    packet.readLengthEncoded()

    val charSet = java.lang.Short.toUnsignedInt(ByteBufUtil.swapShort(packet.readShort()))
    val columnLength = Integer.toUnsignedLong(ByteBufUtil.swapInt(packet.readInt()))
    val columnType = packet.readUnsignedByte()
    val flags = ByteBufUtil.swapShort(packet.readShort())
    val decimals = packet.readByte()

    // Skip filler bytes
    packet.skipBytes(2)

    f(catalog, database, table, originalTable, name, originalName, charSet, columnLength, columnType, flags, decimals)
}

/** Parses a result row from the server. */
inline fun readResultRow(
    packet: ByteBuf,
    columns: Int,
    types: ShortArray,
    targetTypes: List<Class<*>>?,
    codec: CodecExtender?,
    f: (column: Int, value: Any?) -> Unit
) {
    // Skip the type marker.
    packet.skipBytes(1)

    // The packet starts with a bitmap of null values.
    val bitmapSize = (columns + 7 + 2) / 8
    val bitmapStart = packet.readerIndex()
    var bitmapIndex = 0
    var bitMask = 0b100

    // Read the value of each column.
    packet.skipBytes(bitmapSize)
    var column = 0
    while(column < columns) {
        if(packet.getUnsignedByte(bitmapStart + bitmapIndex).toInt() and bitMask != 0) {
            f(column, null)
        } else {
            f(column, decodeBinary(packet, types[column].toInt(), targetTypes?.get(column), codec))
        }

        column++
        bitMask = bitMask shl 1
        if(bitMask and 0xff == 0) {
            bitMask = 1
            bitmapIndex++
        }
    }
}

fun decodeBinary(buffer: ByteBuf, type: Int, targetType: Class<*>?, codec: CodecExtender?): Any? = when(type) {
    // Ordered by likely usage frequency.
    // If most calls do only a few comparisons this is a lot faster than a map structure.
    Type.DECIMAL -> decodeDecimal(buffer, targetType, codec)
    Type.TINY -> decodeByte(buffer, targetType, codec)
    Type.SHORT -> decodeShort(buffer, targetType, codec)
    Type.LONG -> decodeInt(buffer, targetType, codec)
    Type.FLOAT -> decodeFloat(buffer, targetType, codec)
    Type.DOUBLE -> decodeDouble(buffer, targetType, codec)
    Type.NULL -> null
    Type.TIMESTAMP -> decodeDate(buffer, targetType, codec)
    Type.LONGLONG -> decodeLong(buffer, targetType, codec)
    Type.INT24 -> decodeInt(buffer, targetType, codec)
    Type.DATE -> decodeDate(buffer, targetType, codec)
    Type.TIME -> decodeDate(buffer, targetType, codec)
    Type.DATETIME -> decodeDate(buffer, targetType, codec)
    Type.YEAR -> decodeShort(buffer, targetType, codec)
    Type.NEWDATE -> decodeDate(buffer, targetType, codec)
    Type.VARCHAR -> decodeString(buffer, targetType, codec)
    Type.BIT -> decodeBit(buffer, targetType, codec)
    Type.NEWDECIMAL -> decodeDecimal(buffer, targetType, codec)
    Type.ENUM -> decodeString(buffer, targetType, codec)
    Type.SET -> decodeString(buffer, targetType, codec)
    Type.TINY_BLOB -> decodeString(buffer, targetType, codec)
    Type.MEDIUM_BLOB -> decodeString(buffer, targetType, codec)
    Type.LONG_BLOB -> decodeString(buffer, targetType, codec)
    Type.BLOB -> decodeString(buffer, targetType, codec)
    Type.VAR_STRING -> decodeString(buffer, targetType, codec)
    Type.STRING -> decodeString(buffer, targetType, codec)
    Type.GEOMETRY -> decodeString(buffer, targetType, codec)
    else -> throw SqlException(0, "", "Unknown result type $type")
}

fun decodeString(buffer: ByteBuf, targetType: Class<*>?, codec: CodecExtender?): Any {
    if(targetType === null || targetType === stringType) {
        return buffer.readLengthEncodedString()
    } else if(targetType === ByteArray::class.java) {
        val length = buffer.readLengthEncoded().toInt()
        val bytes = ByteArray(length)
        buffer.readBytes(bytes)
        return bytes
    } else if(targetType === ByteBuf::class.java) {
        val length = buffer.readLengthEncoded().toInt()
        return buffer.readBytes(length)
    } else {
        return codec?.decodeString(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}

fun decodeDecimal(buffer: ByteBuf, targetType: Class<*>?, codec: CodecExtender?): Any {
    val string = buffer.readLengthEncodedString()

    if(targetType === null || targetType === doubleType) {
        try {
            return java.lang.Double.parseDouble(string)
        } catch(e: Throwable) {
            return 0.0
        }
    } else if(targetType === intType || targetType === longType) {
        try {
            val v = java.lang.Long.parseLong(string)
            return if(targetType === intType) v.toInt() else v
        } catch(e: Throwable) {
            return if(targetType === intType) 0 else 0L
        }
    } else if(targetType === floatType) {
        try {
            return java.lang.Float.parseFloat(string)
        } catch(e: Throwable) {
            return 0f
        }
    } else if(targetType === BigDecimal::class.java) {
        return BigDecimal(string)
    } else if(targetType === stringType) {
        return string
    } else {
        return codec?.decodeDecimal(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}

fun decodeBit(buffer: ByteBuf, targetType: Class<*>?, codec: CodecExtender?): Any {
    val length = buffer.readLengthEncoded().toInt()
    if(targetType === null || targetType === ByteArray::class.java) {
        val bytes = ByteArray(length)
        buffer.readBytes(bytes)
        return bytes
    } else {
        var result = 0L
        for(i in 0..length - 1) {
            result = result or (buffer.readByte().toLong() shl (8*i))
        }

        if(targetType === longType) return result
        if(targetType === intType) return result.toInt()
        if(targetType === shortType) return result.toShort()
        if(targetType === byteType) return result.toByte()
        if(targetType === booleanType) return result != 0L

        return codec?.decodeBit(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}

fun decodeLong(buffer: ByteBuf, targetType: Class<*>?, codec: CodecExtender?): Any {
    val v = ByteBufUtil.swapLong(buffer.readLong())

    return if(targetType === null || targetType === longType) {
        v
    } else if(targetType === intType) {
        v.toInt()
    } else if(targetType === booleanType) {
        v != 0L
    } else if(targetType.isEnum) {
        targetType.enumConstants.getOrNull(v.toInt()) ?: targetType
    } else {
        return codec?.decodeLong(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}

fun decodeInt(buffer: ByteBuf, targetType: Class<*>?, codec: CodecExtender?): Any {
    val v = ByteBufUtil.swapInt(buffer.readInt())

    return if(targetType === null || targetType === intType) {
        v
    } else if(targetType === longType) {
        v.toLong()
    } else if(targetType === booleanType) {
        v != 0
    } else if(targetType.isEnum) {
        targetType.enumConstants.getOrNull(v.toInt()) ?: targetType
    } else {
        return codec?.decodeInt(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}

fun decodeShort(buffer: ByteBuf, targetType: Class<*>?, codec: CodecExtender?): Any {
    val v = ByteBufUtil.swapShort(buffer.readShort())

    return if(targetType === null || targetType === shortType) {
        v
    } else if(targetType === intType) {
        v.toInt()
    } else if(targetType === longType) {
        v.toLong()
    } else if(targetType === booleanType) {
        v != 0.toShort()
    } else if(targetType.isEnum) {
        targetType.enumConstants.getOrNull(v.toInt()) ?: targetType
    } else {
        return codec?.decodeShort(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}

fun decodeByte(buffer: ByteBuf, targetType: Class<*>?, codec: CodecExtender?): Any {
    val v = buffer.readByte()

    return if(targetType === null || targetType === byteType) {
        v
    } else if(targetType === booleanType) {
        v != 0.toByte()
    } else if(targetType === intType) {
        v.toInt()
    } else if(targetType === longType) {
        v.toLong()
    } else if(targetType === shortType) {
        v.toShort()
    } else if(targetType.isEnum) {
        targetType.enumConstants.getOrNull(v.toInt()) ?: targetType
    } else {
        codec?.decodeByte(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}

fun decodeFloat(buffer: ByteBuf, targetType: Class<*>?, codec: CodecExtender?): Any {
    val v = java.lang.Float.intBitsToFloat(ByteBufUtil.swapInt(buffer.readInt()))

    return if(targetType === null || targetType === floatType) {
        v
    } else if(targetType === intType) {
        v.toInt()
    } else if(targetType === longType) {
        v.toLong()
    } else if(targetType === doubleType) {
        v.toDouble()
    } else {
        return codec?.decodeFloat(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}

fun decodeDouble(buffer: ByteBuf, targetType: Class<*>?, codec: CodecExtender?): Any {
    val v = java.lang.Double.longBitsToDouble(ByteBufUtil.swapLong(buffer.readLong()))

    return if(targetType === null || targetType === doubleType) {
        v
    } else if(targetType === intType) {
        v.toInt()
    } else if(targetType === longType) {
        v.toLong()
    } else if(targetType === floatType) {
        v.toFloat()
    } else {
        return codec?.decodeDouble(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}

fun decodeDate(buffer: ByteBuf, targetType: Class<*>?, codec: CodecExtender?): Any? {
    val length = buffer.readByte().toInt()
    var year = 0
    var month = 0
    var day = 0
    var hour = 0
    var minute = 0
    var second = 0
    var ms = 0

    val chronology = ISOChronology.getInstanceUTC()

    // Special case for empty dates.
    val timestamp = if(length == 0) {
        0L
    } else {
        if (length >= 4) {
            year = ByteBufUtil.swapShort(buffer.readShort()).toInt()
            month = buffer.readByte().toInt()
            day = buffer.readByte().toInt()
        }

        if (length >= 7) {
            hour = buffer.readByte().toInt()
            minute = buffer.readByte().toInt()
            second = buffer.readByte().toInt()
        }

        if (length >= 11) {
            ms = ByteBufUtil.swapInt(buffer.readInt())
        }

        chronology.getDateTimeMillis(year, month, day, hour, minute, second, ms)
    }

    return if(targetType === null || targetType === dateTimeType) {
        DateTime(timestamp, chronology)
    } else if(targetType === dateType) {
        Date(timestamp)
    } else if(targetType === longType) {
        timestamp
    } else {
        return codec?.decodeDate(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}
