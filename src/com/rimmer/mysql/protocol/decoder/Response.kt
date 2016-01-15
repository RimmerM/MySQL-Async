package com.rimmer.mysql.protocol.decoder

import com.rimmer.mysql.protocol.constants.CharSet
import com.rimmer.mysql.protocol.constants.ServerCapability
import com.rimmer.mysql.protocol.constants.defaultAuthMethod
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufUtil
import java.nio.charset.Charset

/** Parses an Ok response from the server. */
inline fun readOk(packet: ByteBuf, capabilities: Int, f: (affectedRows: Long, lastInsertId: Long, serverStatus: Short, warnings: Short, message: String) -> Unit) {
    val affectedRows = packet.readLengthEncoded()
    val lastInsert = packet.readLengthEncoded()
    var serverStatus = 0.toShort()
    var warnings = 0.toShort()
    if(capabilities and ServerCapability.PROTOCOL41 != 0) {
        serverStatus = ByteBufUtil.swapShort(packet.readShort())
        warnings = ByteBufUtil.swapShort(packet.readShort())
    } else if(capabilities and ServerCapability.TRANSACTIONS != 0) {
        serverStatus = ByteBufUtil.swapShort(packet.readShort())
    }

    // Read the result message.
    val info = packet.toString(Charset.defaultCharset())
    packet.readerIndex(packet.readerIndex() + info.length)

    // Ok packets are always received at the end of a stream.
    assert(packet.readableBytes() == 0)

    f(affectedRows, lastInsert, serverStatus, warnings, info)
}

/** Parses an Ok or Error response from the server. */
inline fun readError(packet: ByteBuf, capabilities: Int, f: (errorCode: Short, sqlState: String, message: String) -> Unit) {
    packet.skipBytes(1)
    val errorCode = ByteBufUtil.swapShort(packet.readShort())

    // MySQL 4.1 and above also send the sql state.
    val sqlState = if(capabilities and ServerCapability.PROTOCOL41 != 0) {
        // Skip the marker.
        packet.skipBytes(1)

        // Read the sql state.
        val array = ByteArray(5)
        packet.readBytes(array)
        String(array)
    } else ""

    // Read the error message.
    val message = packet.toString(Charset.defaultCharset())
    packet.readerIndex(packet.readerIndex() + message.length)

    // Error packets are always received at the end of a stream.
    assert(packet.readableBytes() == 0)

    f(errorCode, sqlState, message)
}

/**
 * Parses an EOF response from the server.
 * An EOF packet is sent after after sequence of field- and parameter description packets,
 * and after a sequence of result row packets.
 */
inline fun readEOF(packet: ByteBuf, capabilities: Int, f: (warnings: Short, serverStatus: Short) -> Unit) {
    packet.skipBytes(1)
    var warnings: Short = 0
    var serverStatus: Short = 0

    if(capabilities and ServerCapability.PROTOCOL41 != 0) {
        warnings = ByteBufUtil.swapShort(packet.readShort())
        serverStatus = ByteBufUtil.swapShort(packet.readShort())
    }

    f(warnings, serverStatus)
}

/** Parses a handshake packet, received from the server after connecting. */
inline fun readHandshake(packet: ByteBuf, f: (version: String, connection: Int, capabilities: Int, charSet: Int, statusFlags: Int, authSecret: ByteArray, authMethod: String) -> Unit) {
    packet.skipBytes(1)
    val version = packet.readCString()
    val connection = ByteBufUtil.swapInt(packet.readInt())
    val auth = ByteArray(20)
    packet.readBytes(auth, 0, 8)
    packet.skipBytes(1)
    val capabilitiesLow = ByteBufUtil.swapShort(packet.readShort()).toInt()

    if(packet.readableBytes() > 0) {
        val charSet = packet.readByte().toInt()
        val statusFlags = ByteBufUtil.swapShort(packet.readShort()).toInt()
        val capabilities = capabilitiesLow or (ByteBufUtil.swapShort(packet.readShort()).toInt() shl 16)

        // If the server supports authentication plugins, the next byte indicates the type.
        val authPluginSize = if(capabilities and ServerCapability.PLUGIN_AUTH != 0) {
            packet.readByte().toInt() and 0xff
        } else {
            packet.skipBytes(1)
            0
        }

        // 10 reserved bytes.
        packet.skipBytes(10)

        if(capabilities and ServerCapability.SECURE_CONNECTION != 0) {
            val complement = if(authPluginSize > 0) {
                authPluginSize - 1 - 8
            } else {
                12
            }

            // Read the secure plugin seed into the auth secret.
            packet.readBytes(auth, 8, complement)
            packet.skipBytes(1)
        }

        // Read the server authentication method if it supports plugins.
        val authentication = if(capabilities and ServerCapability.PLUGIN_AUTH != 0) {
            packet.readCString()
        } else {
            defaultAuthMethod
        }

        f(version, connection, capabilities, charSet, statusFlags, auth, authentication)
    } else {
        f(version, connection, capabilitiesLow, CharSet.UTF8, 0, auth, defaultAuthMethod)
    }
}
