package com.rimmer.mysql.protocol.decoder

import com.rimmer.mysql.protocol.CodecExtender
import com.rimmer.mysql.protocol.SqlException
import com.rimmer.mysql.protocol.constants.Type
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.math.BigDecimal

val mysqlDateFormat: DateTimeFormatter = DateTimeFormat.forPattern("YYYY-MM-DD HH:mm:ss").withZoneUTC()

inline fun readTextResultRow(
    packet: ByteBuf,
    columns: Int,
    types: ShortArray,
    targetTypes: List<Class<*>>?,
    codec: CodecExtender?,
    f: (column: Int, value: Any?) -> Unit
) {
    var column = 0
    while(column < columns) {
        f(column, decodeText(packet, types[column].toInt(), targetTypes?.get(column), codec))
        column++
    }
}

fun decodeText(buffer: ByteBuf, type: Int, targetType: Class<*>?, codec: CodecExtender?): Any? {
    val length = buffer.readNullableLengthEncoded() ?: return null
    val bytes = ByteArray(length.toInt())
    buffer.readBytes(bytes)

    if(targetType === stringType) return String(bytes)

    return when(type) {
        Type.DECIMAL -> decodeTextDecimal(bytes, targetType, codec)
        Type.TINY -> decodeTextByte(bytes, targetType, codec)
        Type.SHORT -> decodeTextShort(bytes, targetType, codec)
        Type.LONG -> decodeTextInt(bytes, targetType, codec)
        Type.FLOAT -> decodeTextFloat(bytes, targetType, codec)
        Type.DOUBLE -> decodeTextDouble(bytes, targetType, codec)
        Type.NULL -> null
        Type.TIMESTAMP -> decodeTextDate(bytes, targetType, codec)
        Type.LONGLONG -> decodeTextLong(bytes, targetType, codec)
        Type.INT24 -> decodeTextInt(bytes, targetType, codec)
        Type.DATE -> decodeTextDate(bytes, targetType, codec)
        Type.TIME -> decodeTextDate(bytes, targetType, codec)
        Type.DATETIME -> decodeTextDate(bytes, targetType, codec)
        Type.YEAR -> decodeTextShort(bytes, targetType, codec)
        Type.NEWDATE -> decodeTextDate(bytes, targetType, codec)
        Type.VARCHAR -> decodeTextString(bytes, targetType, codec)
        Type.BIT -> decodeTextBit(bytes, targetType, codec)
        Type.NEWDECIMAL -> decodeTextDecimal(bytes, targetType, codec)
        Type.ENUM -> decodeTextString(bytes, targetType, codec)
        Type.SET -> decodeTextString(bytes, targetType, codec)
        Type.TINY_BLOB -> decodeTextString(bytes, targetType, codec)
        Type.MEDIUM_BLOB -> decodeTextString(bytes, targetType, codec)
        Type.LONG_BLOB -> decodeTextString(bytes, targetType, codec)
        Type.BLOB -> decodeTextString(bytes, targetType, codec)
        Type.VAR_STRING -> decodeTextString(bytes, targetType, codec)
        Type.STRING -> decodeTextString(bytes, targetType, codec)
        Type.GEOMETRY -> decodeTextString(bytes, targetType, codec)
        else -> throw SqlException(0, "", "Unknown result type $type")
    }
}

fun decodeTextString(buffer: ByteArray, targetType: Class<*>?, codec: CodecExtender?): Any {
    return if(targetType === null || targetType === stringType) {
        String(buffer)
    } else if(targetType === byteArrayType) {
        buffer
    } else if(targetType === byteBufType) {
        Unpooled.wrappedBuffer(buffer)
    } else {
        codec?.decodeText(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}

fun decodeTextDecimal(buffer: ByteArray, targetType: Class<*>?, codec: CodecExtender?): Any {
    val string = String(buffer)
    if(targetType === null || targetType === doubleType) {
        return try {
            java.lang.Double.parseDouble(string)
        } catch(e: Throwable) {
            0.0
        }
    } else if(targetType === intType || targetType === longType) {
        return try {
            val v = java.lang.Long.parseLong(string)
            if(targetType === intType) v.toInt() else v
        } catch(e: Throwable) {
            if(targetType === intType) 0 else 0L
        }
    } else if(targetType === floatType) {
        return try {
            java.lang.Float.parseFloat(string)
        } catch(e: Throwable) {
            0f
        }
    } else if(targetType === BigDecimal::class.java) {
        return BigDecimal(string)
    } else if(targetType === stringType) {
        return string
    } else {
        return codec?.decodeText(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}

fun decodeTextBit(buffer: ByteArray, targetType: Class<*>?, codec: CodecExtender?): Any {
    val numbers = buffer.filter { it != 0.toByte() }.toByteArray()
    val result = try {
        java.lang.Long.parseLong(String(numbers))
    } catch(e: NumberFormatException) {
        0L
    }

    if(targetType === longType) return result
    if(targetType === intType || targetType === null) return result.toInt()
    if(targetType === shortType) return result.toShort()
    if(targetType === byteType) return result.toByte()
    if(targetType === booleanType) return result != 0L

    return codec?.decodeText(buffer, targetType) ?: throw unknownTarget(targetType)
}

fun decodeTextLong(buffer: ByteArray, targetType: Class<*>?, codec: CodecExtender?): Any {
    val v = try {
        java.lang.Long.parseLong(String(buffer))
    } catch(e: NumberFormatException) {
        0L
    }

    return if(targetType === null || targetType === longType) {
        v
    } else if(targetType === intType) {
        v.toInt()
    } else if(targetType === booleanType) {
        v != 0L
    } else if(targetType.isEnum) {
        targetType.enumConstants.getOrNull(v.toInt()) ?: targetType
    } else {
        return codec?.decodeText(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}

fun decodeTextInt(buffer: ByteArray, targetType: Class<*>?, codec: CodecExtender?): Any {
    val v = try {
        Integer.parseInt(String(buffer))
    } catch(e: NumberFormatException) {
        0
    }

    return if(targetType === null || targetType === intType) {
        v
    } else if(targetType === longType) {
        v.toLong()
    } else if(targetType === booleanType) {
        v != 0
    } else if(targetType.isEnum) {
        targetType.enumConstants.getOrNull(v) ?: targetType
    } else {
        return codec?.decodeText(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}

fun decodeTextShort(buffer: ByteArray, targetType: Class<*>?, codec: CodecExtender?): Any {
    val v = try {
        java.lang.Short.parseShort(String(buffer))
    } catch(e: NumberFormatException) {
        0.toShort()
    }

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
        return codec?.decodeText(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}

fun decodeTextByte(buffer: ByteArray, targetType: Class<*>?, codec: CodecExtender?): Any {
    val v = try {
        java.lang.Byte.parseByte(String(buffer))
    } catch(e: NumberFormatException) {
        0.toByte()
    }

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
        codec?.decodeText(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}

fun decodeTextFloat(buffer: ByteArray, targetType: Class<*>?, codec: CodecExtender?): Any {
    val v = try {
        java.lang.Float.parseFloat(String(buffer))
    } catch(e: NumberFormatException) {
        java.lang.Float.NaN
    }

    return if(targetType === null || targetType === floatType) {
        v
    } else if(targetType === intType) {
        v.toInt()
    } else if(targetType === longType) {
        v.toLong()
    } else if(targetType === doubleType) {
        v.toDouble()
    } else {
        return codec?.decodeText(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}

fun decodeTextDouble(buffer: ByteArray, targetType: Class<*>?, codec: CodecExtender?): Any {
    val v = try {
        java.lang.Double.parseDouble(String(buffer))
    } catch(e: NumberFormatException) {
        java.lang.Double.NaN
    }

    return if(targetType === null || targetType === doubleType) {
        v
    } else if(targetType === intType) {
        v.toInt()
    } else if(targetType === longType) {
        v.toLong()
    } else if(targetType === floatType) {
        v.toFloat()
    } else {
        return codec?.decodeText(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}

fun decodeTextDate(buffer: ByteArray, targetType: Class<*>?, codec: CodecExtender?): Any? {
    val time = try {
        mysqlDateFormat.parseDateTime(String(buffer))
    } catch(e: Exception) {
        DateTime(0)
    }

    return if(targetType === null || targetType === dateTimeType) {
        time
    } else if(targetType === dateType) {
        time.toDate()
    } else if(targetType === longType) {
        time.millis
    } else {
        return codec?.decodeText(buffer, targetType) ?: throw unknownTarget(targetType)
    }
}
