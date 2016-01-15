package com.rimmer.mysql.protocol.decoder

import com.rimmer.mysql.protocol.SqlException
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufUtil
import java.nio.ByteOrder
import java.nio.charset.Charset

/** Parses a packet header and creates a buffer with the correct size. */
fun ByteBuf.readPacket(): ByteBuf {
    val length = ByteBufUtil.swapInt(readInt())
    return slice(4, length).order(ByteOrder.LITTLE_ENDIAN)
}

/** Starts a new packet and reserves space for the packet length. */
fun ByteBuf.startPacket(): ByteBuf {
    writeInt(0)
    return order(ByteOrder.LITTLE_ENDIAN)
}

/** Reads a MySQL variable length integer. */
fun ByteBuf.readLengthEncoded(): Long {
    val flag = readUnsignedByte()
    return when(flag) {
        0xfc.toShort() -> ByteBufUtil.swapShort(readShort()).toLong()
        0xfd.toShort() -> ByteBufUtil.swapMedium(readMedium()).toLong()
        0xfe.toShort() -> ByteBufUtil.swapLong(readLong())
        else -> flag.toLong()
    }
}

/** Reads a MySQL string with variable length integer size. */
fun ByteBuf.readLengthEncodedString(): String {
    val length = readLengthEncoded().toInt()
    val buffer = kotlin.ByteArray(length)
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