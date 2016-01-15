package com.rimmer.mysql.protocol.constants

/** Header constants that define command types. */
object CommandType {
    val SLEEP               = 0x00
    val QUIT                = 0x01
    val INIT_DB             = 0x02
    val QUERY               = 0x03
    val FIELD_LIST          = 0x04
    val CREATE_DB           = 0x05
    val DROP_DB             = 0x06
    val REFRESH             = 0x07
    val SHUTDOWN            = 0x08
    val STATISTICS          = 0x09
    val PROCESS_INFO        = 0x0a
    val CONNECT             = 0x0b
    val PROCESS_KILL        = 0x0c
    val DEBUG               = 0x0d
    val PING                = 0x0e
    val TIME                = 0x0f
    val DELAYED_INSERT      = 0x10
    val CHANGE_USER         = 0x11
    val BINLOG_DUBP         = 0x12
    val TABLE_DUMP          = 0x13
    val CONNECT_OUT         = 0x14
    val REGISTER_SLAVE      = 0x15
    val STMT_PREPARE        = 0x16
    val STMT_EXECUTE        = 0x17
    val STMT_SEND_LONG_DATA = 0x18
    val STMT_CLOSE          = 0x19
    val STMT_RESET          = 0x1a
    val STMT_OPTION         = 0x1b
    val STMT_FETCH          = 0x1c
}

/** Marks the type of a result packet sent in response to a command. */
object ResultMarker {
    /** The server reports an error */
    val error = 0xff.toByte()

    /** No error, no result set. */
    val ok = 0x00.toByte()

    /** The server reports end of data */
    val eof = 0xfe.toByte()
}

/** Defines the flags table fields can have. */
object FieldFlags {
    /** The field can't be NULL */
    val NOT_NULL         = 0x0001

    /** The field is part of a primary key */
    val PRIMARY_KEY      = 0x0002

    /** The field is part of a unique key */
    val UNIQUE_KEY       = 0x0004

    /** The field is part of a key */
    val MULTIPLE_KEY     = 0x0008

    /** The field is a blob */
    val BLOB             = 0x0010

    /** The field is unsigned */
    val UNSIGNED         = 0x0020

    /** The field is zerofill */
    val ZEROFILL         = 0x0040

    /** The field is binary */
    val BINARY           = 0x0080

    /** The field is an enum */
    val ENUM             = 0x0100

    /** The field is an autoincrement field */
    val AUTO_INCREMENT   = 0x0200

    /** The field is a timestamp */
    val TIMESTAMP        = 0x0400

    /** The field is a set */
    val SET              = 0x0800

    /** The field doesn't have default value */
    val NO_DEFAULT_VALUE = 0x1000

    /** The field is set to NOW on UPDATE commands */
    val ON_UPDATE_NOW    = 0x2000

    /** The field is num (for clients) */
    val NUM              = 0x8000
}

/** Constants that define optional server capabilities. */
object ServerCapability {
    /** Long old-style passwords (Not 4.1+ passwords) */
    val OLD_LONG_PASSWORD   = 0x00001

    /** Report rows found rather than rows affected */
    val FOUND_NOT_AFFECTED  = 0x00002

    /** Send all column flags */
    val ALL_COLUMN_FLAGS    = 0x00004

    /** Can take database as part of login */
    val WITH_DB             = 0x00008

    /** Can disallow database name as part of column name database.table.column */
    val NO_SCHEMA           = 0x00010

    /** Can compress packets */
    val CAN_COMPRESS        = 0x00020

    /** Can handle ODBC */
    val ODBC                = 0x00040

    /** Can use LOAD DATA LOCAL */
    val LOCAL_FILES         = 0x00080

    /** Can ignore spaces before '$(LPAREN)' */
    val IGNORE_SPACE        = 0x00100

    /** Can use 4.1+ protocol */
    val PROTOCOL41          = 0x00200

    /** Interactive client? */
    val INTERACTIVE         = 0x00400

    /** Can switch to SSL after handshake */
    val SSL                 = 0x00800

    /** Ignore sigpipes? */
    val IGNORE_SIGPIPE      = 0x01000

    /** Transaction support */
    val TRANSACTIONS        = 0x02000

    // Old flag for 4.1 protocol
    val RESERVED            = 0x04000

    /** 4.1+ authentication */
    val SECURE_CONNECTION   = 0x08000

    /** Multiple statement support */
    val MULTI_STATEMENTS    = 0x10000

    /** Multiple result set support */
    val MULTI_RESULTS       = 0x20000

    /** Authentication plugin support. */
    val PLUGIN_AUTH         = 0x80000
}

/** Constants that define MySQL type descriptors. */
object Type {
    val DECIMAL     = 0x00
    val TINY        = 0x01 // aka TINYINT, 1 byte
    val SHORT       = 0x02 // aka SMALLINT, 2 bytes
    val LONG        = 0x03 // aka INT, 4 bytes
    val FLOAT       = 0x04 // aka FLOAT, 4-8 bytes
    val DOUBLE      = 0x05 // aka DOUBLE, 8 bytes
    val NULL        = 0x06
    val TIMESTAMP   = 0x07
    val LONGLONG    = 0x08 // aka BIGINT, 8 bytes
    val INT24       = 0x09 // aka MEDIUMINT, 3 bytes
    val DATE        = 0x0a
    val TIME        = 0x0b
    val DATETIME    = 0x0c
    val YEAR        = 0x0d // 1 byte
    val NEWDATE     = 0x0e
    val VARCHAR     = 0x0f
    val BIT         = 0x10 // aka BIT, 1-8 byte
    val NEWDECIMAL  = 0xf6 // aka DECIMAL
    val ENUM        = 0xf7
    val SET         = 0xf8
    val TINY_BLOB   = 0xf9 // aka TINYBLOB, TINYTEXT
    val MEDIUM_BLOB = 0xfa // aka MEDIUMBLOB, MEDIUMTEXT
    val LONG_BLOB   = 0xfb // aka LONGBLOG, LONGTEXT
    val BLOB        = 0xfc // aka BLOB, TEXT
    val VAR_STRING  = 0xfd // aka VARCHAR, VARBINARY
    val STRING      = 0xfe // aka CHAR, BINARY
    val GEOMETRY    = 0xff
}

/** Constants that define server refresh flags. */
object RefreshFlags {
    val GRANT    =   1
    val LOG      =   2
    val TABLES   =   4
    val HOSTS    =   8
    val STATUS   =  16
    val THREADS  =  32
    val SLAVE    =  64
    val MASTER   = 128
}