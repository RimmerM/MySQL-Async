package com.rimmer.mysql.protocol.decoder

import com.rimmer.mysql.protocol.SqlException
import com.rimmer.mysql.protocol.constants.Type
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufUtil
import org.joda.time.DateTime
import org.joda.time.chrono.ISOChronology
import java.math.BigDecimal
import java.util.*

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
inline fun readResultRow(packet: ByteBuf, columns: Int, types: ShortArray, targetTypes: List<Class<*>>?, f: (column: Int, value: Any?) -> Unit) {
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
            f(column, decodeBinary(packet, types[column].toInt(), targetTypes?.get(column)))
        }

        column++
        bitMask = bitMask shl 1
        if(bitMask and 0xff == 0) {
            bitMask = 1
            bitmapIndex++
        }
    }
}

fun decodeBinary(buffer: ByteBuf, type: Int, targetType: Class<*>?): Any? = when(type) {
    // Ordered by likely usage frequency.
    // If most calls do only a few comparisons this is a lot faster than a map structure.
    Type.DECIMAL -> decodeDecimal(buffer, targetType)
    Type.TINY -> decodeByte(buffer, targetType)
    Type.SHORT -> decodeShort(buffer, targetType)
    Type.LONG -> decodeInt(buffer, targetType)
    Type.FLOAT -> decodeFloat(buffer, targetType)
    Type.DOUBLE -> decodeDouble(buffer, targetType)
    Type.NULL -> null
    Type.TIMESTAMP -> decodeDate(buffer, targetType)
    Type.LONGLONG -> decodeLong(buffer, targetType)
    Type.INT24 -> decodeInt(buffer, targetType)
    Type.DATE -> decodeDate(buffer, targetType)
    Type.TIME -> decodeDate(buffer, targetType)
    Type.DATETIME -> decodeDate(buffer, targetType)
    Type.YEAR -> decodeShort(buffer, targetType)
    Type.NEWDATE -> decodeDate(buffer, targetType)
    Type.VARCHAR -> decodeString(buffer, targetType)
    Type.BIT -> decodeString(buffer, targetType)
    Type.NEWDECIMAL -> decodeDecimal(buffer, targetType)
    Type.ENUM -> decodeString(buffer, targetType)
    Type.SET -> decodeString(buffer, targetType)
    Type.TINY_BLOB -> decodeString(buffer, targetType)
    Type.MEDIUM_BLOB -> decodeString(buffer, targetType)
    Type.LONG_BLOB -> decodeString(buffer, targetType)
    Type.BLOB -> decodeString(buffer, targetType)
    Type.VAR_STRING -> decodeString(buffer, targetType)
    Type.STRING -> decodeString(buffer, targetType)
    Type.GEOMETRY -> decodeString(buffer, targetType)
    else -> throw SqlException("Unknown result type $type")
}

fun decodeString(buffer: ByteBuf, targetType: Class<*>?): Any {
    if(targetType === null || targetType === String::class.java) {
        return buffer.readLengthEncodedString()
    } else if(targetType === ByteArray::class.java) {
        val length = buffer.readLengthEncoded().toInt()
        val bytes = ByteArray(length)
        buffer.readBytes(bytes)
        return bytes
    } else {
        throw SqlException("Unknown target type $targetType")
    }
}

fun decodeDecimal(buffer: ByteBuf, targetType: Class<*>?): Any {
    val string = buffer.readLengthEncodedString()

    if(targetType === null || targetType === Double::class.java) {
        try {
            return java.lang.Double.parseDouble(string)
        } catch(e: Throwable) {
            return 0.0
        }
    } else if(targetType === Int::class.java || targetType === Long::class.java) {
        try {
            val v = java.lang.Long.parseLong(string)
            return if(targetType === Int::class.java) v.toInt() else v
        } catch(e: Throwable) {
            return if(targetType === Int::class.java) 0 else 0L
        }
    } else if(targetType === Float::class.java) {
        try {
            return java.lang.Float.parseFloat(string)
        } catch(e: Throwable) {
            return 0f
        }
    } else if(targetType === BigDecimal::class.java) {
        return BigDecimal(string)
    } else if(targetType === String::class.java) {
        return string
    } else {
        throw SqlException("Unknown target type $targetType")
    }
}

fun decodeLong(buffer: ByteBuf, targetType: Class<*>?): Any {
    val v = ByteBufUtil.swapLong(buffer.readLong())

    return if(targetType === null || targetType === Long::class.java) {
        v
    } else if(targetType === Int::class.java) {
        v.toInt()
    } else if(targetType === Boolean::class.java) {
        v != 0L
    } else {
        throw SqlException("Unknown target type $targetType")
    }
}

fun decodeInt(buffer: ByteBuf, targetType: Class<*>?): Any {
    val v = ByteBufUtil.swapInt(buffer.readInt())

    return if(targetType === null || targetType === Int::class.java) {
        v
    } else if(targetType === Long::class.java) {
        v.toLong()
    } else if(targetType === Boolean::class.java) {
        v != 0
    } else {
        throw SqlException("Unknown target type $targetType")
    }
}

fun decodeShort(buffer: ByteBuf, targetType: Class<*>?): Any {
    val v = ByteBufUtil.swapShort(buffer.readShort())

    return if(targetType === null || targetType === Short::class.java) {
        v
    } else if(targetType === Int::class.java) {
        v.toInt()
    } else if(targetType === Long::class.java) {
        v.toLong()
    } else if(targetType === Boolean::class.java) {
        v != 0.toShort()
    } else {
        throw SqlException("Unknown target type $targetType")
    }
}

fun decodeByte(buffer: ByteBuf, targetType: Class<*>?): Any {
    val v = buffer.readByte()

    return if(targetType === null || targetType === Byte::class.java) {
        v
    } else if(targetType === Boolean::class.java) {
        v != 0.toByte()
    } else if(targetType === Int::class.java) {
        v.toInt()
    } else if(targetType === Long::class.java) {
        v.toLong()
    } else if(targetType === Short::class.java) {
        v.toShort()
    } else {
        throw SqlException("Unknown target type $targetType")
    }
}

fun decodeFloat(buffer: ByteBuf, targetType: Class<*>?): Any {
    val v = java.lang.Float.intBitsToFloat(ByteBufUtil.swapInt(buffer.readInt()))

    return if(targetType === null || targetType === Float::class.java) {
        v
    } else if(targetType === Int::class.java) {
        v.toInt()
    } else if(targetType === Long::class.java) {
        v.toLong()
    } else if(targetType === Double::class.java) {
        v.toDouble()
    } else {
        throw SqlException("Unknown target type $targetType")
    }
}

fun decodeDouble(buffer: ByteBuf, targetType: Class<*>?): Any {
    val v = java.lang.Double.longBitsToDouble(ByteBufUtil.swapLong(buffer.readLong()))

    return if(targetType === null || targetType === Double::class.java) {
        v
    } else if(targetType === Int::class.java) {
        v.toInt()
    } else if(targetType === Long::class.java) {
        v.toLong()
    } else if(targetType === Float::class.java) {
        v.toFloat()
    } else {
        throw SqlException("Unknown target type $targetType")
    }
}

fun decodeDate(buffer: ByteBuf, targetType: Class<*>?): Any? {
    val length = buffer.readByte().toInt()
    var year = 0
    var month = 0
    var day = 0
    var hour = 0
    var minute = 0
    var second = 0
    var ms = 0

    // Special case for empty dates.
    if(length == 0) {
        return null
    }

    if(length >= 4) {
        year = ByteBufUtil.swapShort(buffer.readShort()).toInt()
        month = buffer.readByte().toInt()
        day = buffer.readByte().toInt()
    }

    if(length >= 7) {
        hour = buffer.readByte().toInt()
        minute = buffer.readByte().toInt()
        second = buffer.readByte().toInt()
    }

    if(length >= 11) {
        ms = ByteBufUtil.swapInt(buffer.readInt())
    }

    val chronology = ISOChronology.getInstanceUTC()
    val timestamp = chronology.getDateTimeMillis(year, month, day, hour, minute, second, ms)

    return if(targetType === null || targetType === DateTime::class.java) {
        DateTime(timestamp, chronology)
    } else if(targetType === Date::class.java) {
        Date(timestamp)
    } else if(targetType === Long::class.java) {
        timestamp
    } else {
        throw SqlException("Unknown target type $targetType")
    }
}
