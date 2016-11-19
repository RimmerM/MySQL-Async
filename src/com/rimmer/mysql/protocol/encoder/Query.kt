package com.rimmer.mysql.protocol.encoder

import com.rimmer.mysql.protocol.CodecExtender
import com.rimmer.mysql.protocol.constants.CommandType
import com.rimmer.mysql.protocol.constants.Type
import com.rimmer.mysql.protocol.decoder.writeLengthEncoded
import io.netty.buffer.ByteBuf
import org.joda.time.DateTime
import org.joda.time.LocalDate
import java.math.BigDecimal
import java.math.BigInteger
import java.nio.ByteBuffer
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

fun writePrepareStatement(statement: String): ByteBuf {
    val encodedQuery = statement.toByteArray()
    val buffer = packetBuffer(5 + encodedQuery.size)
    buffer.writeByte(CommandType.STMT_PREPARE)
    buffer.writeBytes(encodedQuery)
    writePacketLength(buffer)
    return buffer
}

fun writeQuery(statementId: Int, parameters: List<Any?>, codec: CodecExtender?): ByteBuf {
    val buffer = packetBuffer()
    buffer.writeByte(CommandType.STMT_EXECUTE)
    buffer.writeInt(statementId)
    buffer.writeByte(0) // CURSOR_TYPE_NO_CURSOR
    buffer.writeInt(1) // The iteration count; always 1.
    writeBinaryParameters(buffer, parameters, codec)
    writePacketLength(buffer)
    return buffer
}

fun writeBinaryParameters(buffer: ByteBuf, values: List<Any?>, codec: CodecExtender?) {
    // Create a bitmap indicating which parameter values are null.
    val count = values.size
    if(count < 1) return

    val bitmapSize = (count + 7) / 8
    val bitmap = ByteArray(bitmapSize)

    val typesSize = count * 2
    val types = ByteArray(typesSize)

    val bitmapOffset = buffer.writerIndex()
    buffer.ensureWritable(bitmapSize + 1 + typesSize, true)
    buffer.writerIndex(bitmapOffset + bitmapSize + 1 + typesSize)

    // Fill each array with data.
    for(i in 0..count-1) {
        val value = values[i]
        if(value === null) {
            bitmap[i / 8] = (bitmap[i / 8].toInt() or (1 shl (i and 7))).toByte()
            types[i * 2 + 1] = Type.NULL.toByte()
        } else {
            encodeBinary(buffer, types, i, value, codec)
        }
    }

    val bufferEnd = buffer.writerIndex()

    // Write the null-bitmap to the buffer.
    buffer.writerIndex(bitmapOffset)
    buffer.writeBytes(bitmap)

    // Write the new-params-bound-flag depending on if we have any parameters.
    // An empty parameter set will re-use the previous set if the statement contains parameters.
    buffer.writeByte(if(values.size > 0) 1 else 0)

    // Write the type map to the buffer. The parameter values are already located after this.
    buffer.writeBytes(types)

    buffer.writerIndex(bufferEnd)
    writePacketLength(buffer)
}

fun encodeBinary(buffer: ByteBuf, types: ByteArray, index: Int, value: Any, codec: CodecExtender?) {
    when(value) {
        // Ordered by likely usage frequency.
        // If most calls do only a few comparisons this is a lot faster than a map structure.
        is Int -> encodeInt(buffer, types, index, value)
        is Long -> encodeLong(buffer, types, index, value)
        is String, is BigInteger, is BigDecimal -> encodeString(buffer, types, index, value)
        is Boolean -> encodeBoolean(buffer, types, index, value)
        is DateTime -> encodeDateTime(buffer, types, index, value)
        is Short -> encodeShort(buffer, types, index, value)
        is Date -> encodeDate(buffer, types, index, value)
        is Float -> encodeFloat(buffer, types, index, value)
        is Double -> encodeDouble(buffer, types, index, value)
        is Byte -> encodeByte(buffer, types, index, value)
        is Enum<*> -> encodeInt(buffer, types, index, value.ordinal)
        is Timestamp -> encodeSqlTimestamp(buffer, types, index, value)
        is java.sql.Date -> encodeSqlDate(buffer, types, index, value)
        is java.sql.Time -> encodeSqlTime(buffer, types, index, value)
        is ByteArray -> encodeByteArray(buffer, types, index, value)
        is ByteBuf -> encodeByteBuf(buffer, types, index, value)
        is ByteBuffer -> encodeByteBuffer(buffer, types, index, value)
        is LocalDateTime -> encodeLocalDateTime(buffer, types, index, value)
        is LocalDate -> encodeLocalDate(buffer, types, index, value)
        is LocalTime -> encodeLocalTime(buffer, types, index, value)
        is java.time.LocalDate -> encodeJavaLocalDate(buffer, types, index, value)
        else -> codec?.encode(buffer, types, index, value)
    }
}

fun encodeString(buffer: ByteBuf, types: ByteArray, index: Int, value: Any) {
    val string = value.toString()
    types[index * 2] = Type.VARCHAR.toByte()
    val bytes = string.toByteArray(Charsets.UTF_8)
    buffer.writeLengthEncoded(bytes.size)
    buffer.writeBytes(bytes)
}

fun encodeByte(buffer: ByteBuf, types: ByteArray, index: Int, value: Byte) {
    types[index * 2] = Type.TINY.toByte()
    buffer.writeByte(value.toInt())
}

fun encodeShort(buffer: ByteBuf, types: ByteArray, index: Int, value: Short) {
    types[index * 2] = Type.SHORT.toByte()
    buffer.writeShort(value.toInt())
}

fun encodeInt(buffer: ByteBuf, types: ByteArray, index: Int, value: Int) {
    types[index * 2] = Type.LONG.toByte()
    buffer.writeInt(value)
}

fun encodeLong(buffer: ByteBuf, types: ByteArray, index: Int, value: Long) {
    types[index * 2] = Type.LONGLONG.toByte()
    buffer.writeLong(value)
}

fun encodeFloat(buffer: ByteBuf, types: ByteArray, index: Int, value: Float) {
    types[index * 2] = Type.FLOAT.toByte()
    buffer.writeFloat(value)
}

fun encodeDouble(buffer: ByteBuf, types: ByteArray, index: Int, value: Double) {
    types[index * 2] = Type.DOUBLE.toByte()
    buffer.writeDouble(value)
}

fun encodeLocalDateTime(buffer: ByteBuf, types: ByteArray, index: Int, value: LocalDateTime) {
    types[index * 2] = Type.TIMESTAMP.toByte()

    val hasNano = value.nano > 0
    buffer.writeByte(if(hasNano) 11 else 7)
    buffer.writeShort(value.year)
    buffer.writeByte(value.monthValue)
    buffer.writeByte(value.dayOfMonth)
    buffer.writeByte(value.hour)
    buffer.writeByte(value.minute)
    buffer.writeByte(value.second)
    if(hasNano) {
        // Write as microseconds.
        buffer.writeInt(value.nano / 1000)
    }
}

fun encodeLocalTime(buffer: ByteBuf, types: ByteArray, index: Int, value: LocalTime) {
    types[index * 2] = Type.TIME.toByte()

    val hasNano = value.nano > 0
    buffer.writeByte(if(hasNano) 12 else 8)
    buffer.writeByte(0)
    buffer.writeInt(0)
    buffer.writeByte(value.hour)
    buffer.writeByte(value.minute)
    buffer.writeByte(value.second)
    if(hasNano) {
        // Write as microseconds.
        buffer.writeInt(value.nano / 1000)
    }
}

fun encodeDateTime(buffer: ByteBuf, types: ByteArray, index: Int, value: DateTime) {
    types[index * 2] = Type.TIMESTAMP.toByte()

    val hasMillis = value.millisOfSecond > 0
    buffer.writeByte(if(hasMillis) 11 else 7)
    buffer.writeShort(value.year)
    buffer.writeByte(value.monthOfYear)
    buffer.writeByte(value.dayOfMonth)
    buffer.writeByte(value.hourOfDay)
    buffer.writeByte(value.minuteOfHour)
    buffer.writeByte(value.secondOfMinute)
    if(hasMillis) {
        // Write as microseconds.
        buffer.writeInt(value.millisOfSecond * 1000)
    }
}

fun encodeLocalDate(buffer: ByteBuf, types: ByteArray, index: Int, value: LocalDate) {
    types[index * 2] = Type.DATE.toByte()

    buffer.writeByte(4)
    buffer.writeShort(value.year)
    buffer.writeByte(value.monthOfYear)
    buffer.writeByte(value.dayOfMonth)
}

fun encodeJavaLocalDate(buffer: ByteBuf, types: ByteArray, index: Int, value: java.time.LocalDate) {
    types[index * 2] = Type.DATE.toByte()

    buffer.writeByte(4)
    buffer.writeShort(value.year)
    buffer.writeByte(value.monthValue)
    buffer.writeByte(value.dayOfMonth)
}

fun encodeDate(buffer: ByteBuf, types: ByteArray, index: Int, value: Date) {
    encodeDateTime(buffer, types, index, DateTime(value))
}

fun encodeSqlTimestamp(buffer: ByteBuf, types: ByteArray, index: Int, value: Timestamp) {
    encodeLocalDateTime(buffer, types, index, value.toLocalDateTime())
}

fun encodeSqlDate(buffer: ByteBuf, types: ByteArray, index: Int, value: java.sql.Date) {
    encodeJavaLocalDate(buffer, types, index, value.toLocalDate())
}

fun encodeSqlTime(buffer: ByteBuf, types: ByteArray, index: Int, value: java.sql.Time) {
    encodeLocalTime(buffer, types, index, value.toLocalTime())
}

fun encodeByteArray(buffer: ByteBuf, types: ByteArray, index: Int, value: ByteArray) {
    types[index * 2] = Type.BLOB.toByte()
    buffer.writeLengthEncoded(value.size)
    buffer.writeBytes(value)
}

fun encodeBoolean(buffer: ByteBuf, types: ByteArray, index: Int, value: Boolean) {
    types[index * 2] = Type.TINY.toByte()
    buffer.writeByte(if(value) 1 else 0)
}

fun encodeByteBuf(buffer: ByteBuf, types: ByteArray, index: Int, value: ByteBuf) {
    types[index * 2] = Type.BLOB.toByte()
    buffer.writeLengthEncoded(value.readableBytes())
    buffer.writeBytes(value)
}

fun encodeByteBuffer(buffer: ByteBuf, types: ByteArray, index: Int, value: ByteBuffer) {
    types[index * 2] = Type.BLOB.toByte()
    buffer.writeLengthEncoded(value.remaining())
    buffer.writeBytes(value)
}