package com.rimmer.mysql.protocol.constants

/** Defines server status flag constants. */
object ServerStatus {
    /**
     * Is raised when a multi-statement transaction
     * has been started, either explicitly, by means
     * of BEGIN or COMMIT AND CHAIN, or
     * implicitly, by the first transactional
     * statement, when autocommit=off.
     */
    val SERVER_STATUS_IN_TRANS          = 1

    /** The server is in auto_commit mode */
    val SERVER_STATUS_AUTOCOMMIT        = 2

    /** Indicates that the next query exists in a multi-query. */
    val SERVER_MORE_RESULTS_EXISTS      = 8
    val SERVER_QUERY_NO_GOOD_INDEX_USED = 16
    val SERVER_QUERY_NO_INDEX_USED      = 32

    /**
     * The server was able to fulfill the client's request and opened a
     * read-only non-scrollable cursor for a query. This flag comes
     * in reply to COM_STMT_EXECUTE and COM_STMT_FETCH commands.
     */
    val SERVER_STATUS_CURSOR_EXISTS = 64

    /**
     * This flag is sent when a read-only cursor is exhausted, in reply to
     * COM_STMT_FETCH command.
     */
    val SERVER_STATUS_LAST_ROW_SENT        = 128

    /** A database was dropped */
    val SERVER_STATUS_DB_DROPPED           = 256
    val SERVER_STATUS_NO_BACKSLASH_ESCAPES = 512

    /**
     * Sent to the client if after a prepared statement reprepare
     * we discovered that the new statement returns a different
     * number of result set columns.
     */
    val SERVER_STATUS_METADATA_CHANGED = 1024
    val SERVER_QUERY_WAS_SLOW          = 2048

    /** Marks a ResultSet containing output parameter values. */
    val SERVER_PS_OUT_PARAMS = 4096
}