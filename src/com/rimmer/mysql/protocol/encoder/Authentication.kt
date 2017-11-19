package com.rimmer.mysql.protocol.encoder

import com.rimmer.mysql.protocol.constants.ServerCapability
import com.rimmer.mysql.protocol.constants.defaultAuthMethod
import com.rimmer.mysql.protocol.decoder.writeCString
import io.netty.buffer.ByteBuf
import io.netty.util.CharsetUtil
import java.security.MessageDigest

val defaultClientFlags =
    ServerCapability.PROTOCOL41 or
    ServerCapability.SECURE_CONNECTION or
    ServerCapability.MULTI_RESULTS or
    ServerCapability.TRANSACTIONS or
    ServerCapability.PLUGIN_AUTH

fun makeToken(password: String, secret: ByteArray): ByteArray {
    val sha = MessageDigest.getInstance("SHA-1")
    val passwordBytes = password.toByteArray(CharsetUtil.US_ASCII)

    // Hash the provided password.
    val pass1 = sha.digest(passwordBytes)
    sha.reset()

    // Hash the hashed password.
    val pass2 = sha.digest(pass1)
    sha.reset()

    // Hash the server secret together with dual-hashed password.
    sha.update(secret)
    sha.update(pass2)
    val token = sha.digest()

    // Xor each byte in the token with the password hash.
    for(i in 0 until token.size) {
        token[i] = (token[i].toInt() xor pass1[i].toInt()).toByte()
    }

    return token
}

fun writeAuthentication(maxPacketSize: Int, charset: Int, user: String, password: String, seed: ByteArray, database: String?): ByteBuf {
    val packet = packetBuffer(128)

    val clientFlags = if(database == null) {
        defaultClientFlags
    } else {
        defaultClientFlags or ServerCapability.WITH_DB
    }

    val token = makeToken(password, seed)
    packet.writeInt(clientFlags)
    packet.writeInt(maxPacketSize)
    packet.writeByte(charset)
    packet.writeBytes(authPadding)
    packet.writeCString(user)
    packet.writeByte(token.size)
    packet.writeBytes(token)

    if(database != null) {
        packet.writeCString(database)
    }

    packet.writeCString(defaultAuthMethod)
    writePacketLength(packet, 1)
    return packet
}

// We cache this here to avoid recreating it each time.
private val authPadding = ByteArray(23)