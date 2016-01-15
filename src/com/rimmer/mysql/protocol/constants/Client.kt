package com.rimmer.mysql.protocol.constants

val defaultAuthMethod = "mysql_native_password"

object Client {
    val CLIENT_LONG_PASSWORD     = 1 /* new more secure passwords */
    val CLIENT_FOUND_ROWS        = 2 /* Found instead of affected rows */
    val CLIENT_LONG_FLAG         = 4 /* Get all column flags */
    val CLIENT_CONNECT_WITH_DB   = 8 /* One can specify db on connect */
    val CLIENT_NO_SCHEMA         = 16 /* Don't allow database.table.column */
    val CLIENT_COMPRESS          = 32 /* Can use compression protocol */
    val CLIENT_ODBC              = 64 /* Odbc client */
    val CLIENT_LOCAL_FILES       = 128 /* Can use LOAD DATA LOCAL */
    val CLIENT_IGNORE_SPACE      = 256 /* Ignore spaces before '(' */
    val CLIENT_PROTOCOL_41       = 512 /* New 4.1 protocol */
    val CLIENT_INTERACTIVE       = 1024 /* This is an interactive client */
    val CLIENT_SSL               = 2048 /* Switch to SSL after handshake */
    val CLIENT_IGNORE_SIGPIPE    = 4096    /* IGNORE sigpipes */
    val CLIENT_TRANSACTIONS      = 8192 /* Client knows about transactions */
    val CLIENT_RESERVED          = 16384   /* Old flag for 4.1 protocol  */
    val CLIENT_SECURE_CONNECTION = 32768  /* New 4.1 authentication */

    val CLIENT_MULTI_STATEMENTS = 65536 /* Enable/disable multi-stmt support */
    val CLIENT_MULTI_RESULTS    = 131072 /* Enable/disable multi-results */
    val CLIENT_PS_MULTI_RESULTS = 262144 /* Multi-results in PS-protocol */

    val CLIENT_PLUGIN_AUTH = 524288 /* Client supports plugin authentication */

    val CLIENT_SSL_VERIFY_SERVER_CERT = 1073741824
    val CLIENT_REMEMBER_OPTIONS       = 2147483648
}
