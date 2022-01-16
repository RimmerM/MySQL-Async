package com.rimmer.mysql.protocol.decoder

import io.netty.buffer.ByteBuf
import java.nio.charset.Charset

/** Reads a MySQL variable length integer. */
fun ByteBuf.readLengthEncoded(): Long {
    val flag = readUnsignedByte()
    return when(flag) {
        0xfb.toShort() -> 0L
        0xfc.toShort() -> readUnsignedShortLE().toLong()
        0xfd.toShort() -> readUnsignedMediumLE().toLong()
        0xfe.toShort() -> readLongLE()
        else -> flag.toLong()
    }
}

fun ByteBuf.readNullableLengthEncoded(): Long? {
    val flag = readUnsignedByte()
    return when(flag) {
        0xfb.toShort() -> null
        0xfc.toShort() -> readUnsignedShortLE().toLong()
        0xfd.toShort() -> readUnsignedMediumLE().toLong()
        0xfe.toShort() -> readLongLE()
        else -> flag.toLong()
    }
}

/** Reads a MySQL string with variable length integer size. */
fun ByteBuf.readLengthEncodedString(): String {
    val length = readLengthEncoded().toInt()
    val buffer = ByteArray(length)
    readBytes(buffer)
    return String(buffer)
}

/** Writes a MySQL variable length integer. */
fun ByteBuf.writeLengthEncoded(value: Int) {
    if(value < 251) {
        writeByte(value)
    } else if(value < 65535) {
        writeByte(0xfc)
        writeShort(value)
    } else if(value < 16777215) {
        writeByte(0xfd)
        writeMedium(value)
    } else {
        writeByte(0xfe)
        writeLong(value.toLong())
    }
}

/** Reads a C-string. */
fun ByteBuf.readCString(): String {
    val length = bytesBefore(0)
    val string = toString(readerIndex(), length, Charset.defaultCharset())
    skipBytes(length + 1)
    return string
}

/** Writes a C-string. */
fun ByteBuf.writeCString(string: String) {
    writeBytes(string.toByteArray(Charset.defaultCharset()))
    writeByte(0)
}