package com.rimmer.mysql.protocol.encoder

import com.rimmer.mysql.protocol.constants.CommandType
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufAllocator
import io.netty.buffer.Unpooled
import java.nio.ByteOrder

/** Creates a buffer used for writing MySQL packets. */
fun packetBuffer(initialSize: Int = 1024): ByteBuf {
    // TODO: Use pooled direct buffers instead.
    val buffer = ByteBufAllocator.DEFAULT.buffer(initialSize).order(ByteOrder.LITTLE_ENDIAN)

    // Add space at the beginning for the final packet size. This is calculated afterwards.
    buffer.writeInt(0)
    return buffer
}

/** Writes the final packet length to the provided packet buffer. */
fun writePacketLength(buffer: ByteBuf, sequenceIndex: Int = 0) {
    // The length is the total index, minus the space we reserved at the beginning.
    val length = buffer.writerIndex() - 4

    // Write the size and sequence index to the beginning of the buffer.
    buffer.markWriterIndex()
    buffer.writerIndex(0)

    buffer.writeMedium(length)
    buffer.writeByte(sequenceIndex)

    // Reset the index so writes after this go to the buffer end.
    buffer.resetWriterIndex()
}

/** Writes a quit message to the server. */
fun writeQuit(): ByteBuf {
    val buf = packetBuffer(5)
    buf.writeByte(CommandType.QUIT)
    writePacketLength(buf)
    return buf
}