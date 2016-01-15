package com.rimmer.mysql.protocol.constants

object Error {
    val EE_CANTCREATEFILE                                                   = 1
    val EE_READ                                                             = 2
    val EE_WRITE                                                            = 3
    val EE_BADCLOSE                                                         = 4
    val EE_OUTOFMEMORY                                                      = 5
    val EE_DELETE                                                           = 6
    val EE_LINK                                                             = 7
    val EE_EOFERR                                                           = 9
    val EE_CANTLOCK                                                         = 10
    val EE_CANTUNLOCK                                                       = 11
    val EE_DIR                                                              = 12
    val EE_STAT                                                             = 13
    val EE_CANT_CHSIZE                                                      = 14
    val EE_CANT_OPEN_STREAM                                                 = 15
    val EE_GETWD                                                            = 16
    val EE_SETWD                                                            = 17
    val EE_LINK_WARNING                                                     = 18
    val EE_OPEN_WARNING                                                     = 19
    val EE_DISK_FULL                                                        = 20
    val EE_CANT_MKDIR                                                       = 21
    val EE_UNKNOWN_CHARSET                                                  = 22
    val EE_OUT_OF_FILERESOURCES                                             = 23
    val EE_CANT_READLINK                                                    = 24
    val EE_CANT_SYMLINK                                                     = 25
    val EE_REALPATH                                                         = 26
    val EE_SYNC                                                             = 27
    val EE_UNKNOWN_COLLATION                                                = 28
    val EE_FILENOTFOUND                                                     = 29
    val EE_FILE_NOT_CLOSED                                                  = 30
    val EE_CHANGE_OWNERSHIP                                                 = 31
    val EE_CHANGE_PERMISSIONS                                               = 32
    val EE_CANT_SEEK                                                        = 33
    val HA_ERR_KEY_NOT_FOUND                                                = 120
    val HA_ERR_FOUND_DUPP_KEY                                               = 121
    val HA_ERR_INTERNAL_ERROR                                               = 122
    val HA_ERR_RECORD_CHANGED                                               = 123
    val HA_ERR_WRONG_INDEX                                                  = 124
    val HA_ERR_CRASHED                                                      = 126
    val HA_ERR_WRONG_IN_RECORD                                              = 127
    val HA_ERR_OUT_OF_MEM                                                   = 128
    val HA_ERR_NOT_A_TABLE                                                  = 130
    val HA_ERR_WRONG_COMMAND                                                = 131
    val HA_ERR_OLD_FILE                                                     = 132
    val HA_ERR_NO_ACTIVE_RECORD                                             = 133
    val HA_ERR_RECORD_DELETED                                               = 134
    val HA_ERR_RECORD_FILE_FULL                                             = 135
    val HA_ERR_INDEX_FILE_FULL                                              = 136
    val HA_ERR_END_OF_FILE                                                  = 137
    val HA_ERR_UNSUPPORTED                                                  = 138
    val HA_ERR_TO_BIG_ROW                                                   = 139
    val HA_WRONG_CREATE_OPTION                                              = 140
    val HA_ERR_FOUND_DUPP_UNIQUE                                            = 141
    val HA_ERR_UNKNOWN_CHARSET                                              = 142
    val HA_ERR_WRONG_MRG_TABLE_DEF                                          = 143
    val HA_ERR_CRASHED_ON_REPAIR                                            = 144
    val HA_ERR_CRASHED_ON_USAGE                                             = 145
    val HA_ERR_LOCK_WAIT_TIMEOUT                                            = 146
    val HA_ERR_LOCK_TABLE_FULL                                              = 147
    val HA_ERR_READ_ONLY_TRANSACTION                                        = 148
    val HA_ERR_LOCK_DEADLOCK                                                = 149
    val HA_ERR_CANNOT_ADD_FOREIGN                                           = 150
    val HA_ERR_NO_REFERENCED_ROW                                            = 151
    val HA_ERR_ROW_IS_REFERENCED                                            = 152
    val HA_ERR_NO_SAVEPOINT                                                 = 153
    val HA_ERR_NON_UNIQUE_BLOCK_SIZE                                        = 154
    val HA_ERR_NO_SUCH_TABLE                                                = 155
    val HA_ERR_TABLE_EXIST                                                  = 156
    val HA_ERR_NO_CONNECTION                                                = 157
    val HA_ERR_NULL_IN_SPATIAL                                              = 158
    val HA_ERR_TABLE_DEF_CHANGED                                            = 159
    val HA_ERR_NO_PARTITION_FOUND                                           = 160
    val HA_ERR_RBR_LOGGING_FAILED                                           = 161
    val HA_ERR_DROP_INDEX_FK                                                = 162
    val HA_ERR_FOREIGN_DUPLICATE_KEY                                        = 163
    val HA_ERR_TABLE_NEEDS_UPGRADE                                          = 164
    val HA_ERR_TABLE_READONLY                                               = 165
    val HA_ERR_AUTOINC_READ_FAILED                                          = 166
    val HA_ERR_AUTOINC_ERANGE                                               = 167
    val HA_ERR_GENERIC                                                      = 168
    val HA_ERR_RECORD_IS_THE_SAME                                           = 169
    val HA_ERR_LOGGING_IMPOSSIBLE                                           = 170
    val HA_ERR_CORRUPT_EVENT                                                = 171
    val HA_ERR_NEW_FILE                                                     = 172
    val HA_ERR_ROWS_EVENT_APPLY                                             = 173
    val HA_ERR_INITIALIZATION                                               = 174
    val HA_ERR_FILE_TOO_SHORT                                               = 175
    val HA_ERR_WRONG_CRC                                                    = 176
    val HA_ERR_TOO_MANY_CONCURRENT_TRXS                                     = 177
    val HA_ERR_NOT_IN_LOCK_PARTITIONS                                       = 178
    val HA_ERR_INDEX_COL_TOO_LONG                                           = 179
    val HA_ERR_INDEX_CORRUPT                                                = 180
    val HA_ERR_UNDO_REC_TOO_BIG                                             = 181
    val HA_FTS_INVALID_DOCID                                                = 182
    val HA_ERR_TABLE_IN_FK_CHECK                                            = 183
    val HA_ERR_TABLESPACE_EXISTS                                            = 184
    val HA_ERR_TOO_MANY_FIELDS                                              = 185
    val HA_ERR_ROW_IN_WRONG_PARTITION                                       = 186
    val HA_ERR_INNODB_READ_ONLY                                             = 187
    val HA_ERR_FTS_EXCEED_RESULT_CACHE_LIMIT                                = 188
    val HA_ERR_TEMP_FILE_WRITE_FAILURE                                      = 189
    val HA_ERR_INNODB_FORCED_RECOVERY                                       = 190
    val HA_ERR_FTS_TOO_MANY_WORDS_IN_PHRASE                                 = 191
    val ER_HASHCHK                                                          = 1000
    val ER_NISAMCHK                                                         = 1001
    val ER_NO                                                               = 1002
    val ER_YES                                                              = 1003
    val ER_CANT_CREATE_FILE                                                 = 1004
    val ER_CANT_CREATE_TABLE                                                = 1005
    val ER_CANT_CREATE_DB                                                   = 1006
    val ER_DB_CREATE_EXISTS                                                 = 1007
    val ER_DB_DROP_EXISTS                                                   = 1008
    val ER_DB_DROP_DELETE                                                   = 1009
    val ER_DB_DROP_RMDIR                                                    = 1010
    val ER_CANT_DELETE_FILE                                                 = 1011
    val ER_CANT_FIND_SYSTEM_REC                                             = 1012
    val ER_CANT_GET_STAT                                                    = 1013
    val ER_CANT_GET_WD                                                      = 1014
    val ER_CANT_LOCK                                                        = 1015
    val ER_CANT_OPEN_FILE                                                   = 1016
    val ER_FILE_NOT_FOUND                                                   = 1017
    val ER_CANT_READ_DIR                                                    = 1018
    val ER_CANT_SET_WD                                                      = 1019
    val ER_CHECKREAD                                                        = 1020
    val ER_DISK_FULL                                                        = 1021
    val ER_DUP_KEY                                                          = 1022
    val ER_ERROR_ON_CLOSE                                                   = 1023
    val ER_ERROR_ON_READ                                                    = 1024
    val ER_ERROR_ON_RENAME                                                  = 1025
    val ER_ERROR_ON_WRITE                                                   = 1026
    val ER_FILE_USED                                                        = 1027
    val ER_FILSORT_ABORT                                                    = 1028
    val ER_FORM_NOT_FOUND                                                   = 1029
    val ER_GET_ERRNO                                                        = 1030
    val ER_ILLEGAL_HA                                                       = 1031
    val ER_KEY_NOT_FOUND                                                    = 1032
    val ER_NOT_FORM_FILE                                                    = 1033
    val ER_NOT_KEYFILE                                                      = 1034
    val ER_OLD_KEYFILE                                                      = 1035
    val ER_OPEN_AS_READONLY                                                 = 1036
    val ER_OUTOFMEMORY                                                      = 1037
    val ER_OUT_OF_SORTMEMORY                                                = 1038
    val ER_UNEXPECTED_EOF                                                   = 1039
    val ER_CON_COUNT_ERROR                                                  = 1040
    val ER_OUT_OF_RESOURCES                                                 = 1041
    val ER_BAD_HOST_ERROR                                                   = 1042
    val ER_HANDSHAKE_ERROR                                                  = 1043
    val ER_DBACCESS_DENIED_ERROR                                            = 1044
    val ER_ACCESS_DENIED_ERROR                                              = 1045
    val ER_NO_DB_ERROR                                                      = 1046
    val ER_UNKNOWN_COM_ERROR                                                = 1047
    val ER_BAD_NULL_ERROR                                                   = 1048
    val ER_BAD_DB_ERROR                                                     = 1049
    val ER_TABLE_EXISTS_ERROR                                               = 1050
    val ER_BAD_TABLE_ERROR                                                  = 1051
    val ER_NON_UNIQ_ERROR                                                   = 1052
    val ER_SERVER_SHUTDOWN                                                  = 1053
    val ER_BAD_FIELD_ERROR                                                  = 1054
    val ER_WRONG_FIELD_WITH_GROUP                                           = 1055
    val ER_WRONG_GROUP_FIELD                                                = 1056
    val ER_WRONG_SUM_SELECT                                                 = 1057
    val ER_WRONG_VALUE_COUNT                                                = 1058
    val ER_TOO_LONG_IDENT                                                   = 1059
    val ER_DUP_FIELDNAME                                                    = 1060
    val ER_DUP_KEYNAME                                                      = 1061
    val ER_DUP_ENTRY                                                        = 1062
    val ER_WRONG_FIELD_SPEC                                                 = 1063
    val ER_PARSE_ERROR                                                      = 1064
    val ER_EMPTY_QUERY                                                      = 1065
    val ER_NONUNIQ_TABLE                                                    = 1066
    val ER_INVALID_DEFAULT                                                  = 1067
    val ER_MULTIPLE_PRI_KEY                                                 = 1068
    val ER_TOO_MANY_KEYS                                                    = 1069
    val ER_TOO_MANY_KEY_PARTS                                               = 1070
    val ER_TOO_LONG_KEY                                                     = 1071
    val ER_KEY_COLUMN_DOES_NOT_EXITS                                        = 1072
    val ER_BLOB_USED_AS_KEY                                                 = 1073
    val ER_TOO_BIG_FIELDLENGTH                                              = 1074
    val ER_WRONG_AUTO_KEY                                                   = 1075
    val ER_READY                                                            = 1076
    val ER_NORMAL_SHUTDOWN                                                  = 1077
    val ER_GOT_SIGNAL                                                       = 1078
    val ER_SHUTDOWN_COMPLETE                                                = 1079
    val ER_FORCING_CLOSE                                                    = 1080
    val ER_IPSOCK_ERROR                                                     = 1081
    val ER_NO_SUCH_INDEX                                                    = 1082
    val ER_WRONG_FIELD_TERMINATORS                                          = 1083
    val ER_BLOBS_AND_NO_TERMINATED                                          = 1084
    val ER_TEXTFILE_NOT_READABLE                                            = 1085
    val ER_FILE_EXISTS_ERROR                                                = 1086
    val ER_LOAD_INFO                                                        = 1087
    val ER_ALTER_INFO                                                       = 1088
    val ER_WRONG_SUB_KEY                                                    = 1089
    val ER_CANT_REMOVE_ALL_FIELDS                                           = 1090
    val ER_CANT_DROP_FIELD_OR_KEY                                           = 1091
    val ER_INSERT_INFO                                                      = 1092
    val ER_UPDATE_TABLE_USED                                                = 1093
    val ER_NO_SUCH_THREAD                                                   = 1094
    val ER_KILL_DENIED_ERROR                                                = 1095
    val ER_NO_TABLES_USED                                                   = 1096
    val ER_TOO_BIG_SET                                                      = 1097
    val ER_NO_UNIQUE_LOGFILE                                                = 1098
    val ER_TABLE_NOT_LOCKED_FOR_WRITE                                       = 1099
    val ER_TABLE_NOT_LOCKED                                                 = 1100
    val ER_BLOB_CANT_HAVE_DEFAULT                                           = 1101
    val ER_WRONG_DB_NAME                                                    = 1102
    val ER_WRONG_TABLE_NAME                                                 = 1103
    val ER_TOO_BIG_SELECT                                                   = 1104
    val ER_UNKNOWN_ERROR                                                    = 1105
    val ER_UNKNOWN_PROCEDURE                                                = 1106
    val ER_WRONG_PARAMCOUNT_TO_PROCEDURE                                    = 1107
    val ER_WRONG_PARAMETERS_TO_PROCEDURE                                    = 1108
    val ER_UNKNOWN_TABLE                                                    = 1109
    val ER_FIELD_SPECIFIED_TWICE                                            = 1110
    val ER_INVALID_GROUP_FUNC_USE                                           = 1111
    val ER_UNSUPPORTED_EXTENSION                                            = 1112
    val ER_TABLE_MUST_HAVE_COLUMNS                                          = 1113
    val ER_RECORD_FILE_FULL                                                 = 1114
    val ER_UNKNOWN_CHARACTER_SET                                            = 1115
    val ER_TOO_MANY_TABLES                                                  = 1116
    val ER_TOO_MANY_FIELDS                                                  = 1117
    val ER_TOO_BIG_ROWSIZE                                                  = 1118
    val ER_STACK_OVERRUN                                                    = 1119
    val ER_WRONG_OUTER_JOIN                                                 = 1120
    val ER_NULL_COLUMN_IN_INDEX                                             = 1121
    val ER_CANT_FIND_UDF                                                    = 1122
    val ER_CANT_INITIALIZE_UDF                                              = 1123
    val ER_UDF_NO_PATHS                                                     = 1124
    val ER_UDF_EXISTS                                                       = 1125
    val ER_CANT_OPEN_LIBRARY                                                = 1126
    val ER_CANT_FIND_DL_ENTRY                                               = 1127
    val ER_FUNCTION_NOT_DEFINED                                             = 1128
    val ER_HOST_IS_BLOCKED                                                  = 1129
    val ER_HOST_NOT_PRIVILEGED                                              = 1130
    val ER_PASSWORD_ANONYMOUS_USER                                          = 1131
    val ER_PASSWORD_NOT_ALLOWED                                             = 1132
    val ER_PASSWORD_NO_MATCH                                                = 1133
    val ER_UPDATE_INFO                                                      = 1134
    val ER_CANT_CREATE_THREAD                                               = 1135
    val ER_WRONG_VALUE_COUNT_ON_ROW                                         = 1136
    val ER_CANT_REOPEN_TABLE                                                = 1137
    val ER_INVALID_USE_OF_NULL                                              = 1138
    val ER_REGEXP_ERROR                                                     = 1139
    val ER_MIX_OF_GROUP_FUNC_AND_FIELDS                                     = 1140
    val ER_NONEXISTING_GRANT                                                = 1141
    val ER_TABLEACCESS_DENIED_ERROR                                         = 1142
    val ER_COLUMNACCESS_DENIED_ERROR                                        = 1143
    val ER_ILLEGAL_GRANT_FOR_TABLE                                          = 1144
    val ER_GRANT_WRONG_HOST_OR_USER                                         = 1145
    val ER_NO_SUCH_TABLE                                                    = 1146
    val ER_NONEXISTING_TABLE_GRANT                                          = 1147
    val ER_NOT_ALLOWED_COMMAND                                              = 1148
    val ER_SYNTAX_ERROR                                                     = 1149
    val ER_DELAYED_CANT_CHANGE_LOCK                                         = 1150
    val ER_TOO_MANY_DELAYED_THREADS                                         = 1151
    val ER_ABORTING_CONNECTION                                              = 1152
    val ER_NET_PACKET_TOO_LARGE                                             = 1153
    val ER_NET_READ_ERROR_FROM_PIPE                                         = 1154
    val ER_NET_FCNTL_ERROR                                                  = 1155
    val ER_NET_PACKETS_OUT_OF_ORDER                                         = 1156
    val ER_NET_UNCOMPRESS_ERROR                                             = 1157
    val ER_NET_READ_ERROR                                                   = 1158
    val ER_NET_READ_INTERRUPTED                                             = 1159
    val ER_NET_ERROR_ON_WRITE                                               = 1160
    val ER_NET_WRITE_INTERRUPTED                                            = 1161
    val ER_TOO_LONG_STRING                                                  = 1162
    val ER_TABLE_CANT_HANDLE_BLOB                                           = 1163
    val ER_TABLE_CANT_HANDLE_AUTO_INCREMENT                                 = 1164
    val ER_DELAYED_INSERT_TABLE_LOCKED                                      = 1165
    val ER_WRONG_COLUMN_NAME                                                = 1166
    val ER_WRONG_KEY_COLUMN                                                 = 1167
    val ER_WRONG_MRG_TABLE                                                  = 1168
    val ER_DUP_UNIQUE                                                       = 1169
    val ER_BLOB_KEY_WITHOUT_LENGTH                                          = 1170
    val ER_PRIMARY_CANT_HAVE_NULL                                           = 1171
    val ER_TOO_MANY_ROWS                                                    = 1172
    val ER_REQUIRES_PRIMARY_KEY                                             = 1173
    val ER_NO_RAID_COMPILED                                                 = 1174
    val ER_UPDATE_WITHOUT_KEY_IN_SAFE_MODE                                  = 1175
    val ER_KEY_DOES_NOT_EXITS                                               = 1176
    val ER_CHECK_NO_SUCH_TABLE                                              = 1177
    val ER_CHECK_NOT_IMPLEMENTED                                            = 1178
    val ER_CANT_DO_THIS_DURING_AN_TRANSACTION                               = 1179
    val ER_ERROR_DURING_COMMIT                                              = 1180
    val ER_ERROR_DURING_ROLLBACK                                            = 1181
    val ER_ERROR_DURING_FLUSH_LOGS                                          = 1182
    val ER_ERROR_DURING_CHECKPOINT                                          = 1183
    val ER_NEW_ABORTING_CONNECTION                                          = 1184
    val ER_DUMP_NOT_IMPLEMENTED                                             = 1185
    val ER_FLUSH_MASTER_BINLOG_CLOSED                                       = 1186
    val ER_INDEX_REBUILD                                                    = 1187
    val ER_MASTER                                                           = 1188
    val ER_MASTER_NET_READ                                                  = 1189
    val ER_MASTER_NET_WRITE                                                 = 1190
    val ER_FT_MATCHING_KEY_NOT_FOUND                                        = 1191
    val ER_LOCK_OR_ACTIVE_TRANSACTION                                       = 1192
    val ER_UNKNOWN_SYSTEM_VARIABLE                                          = 1193
    val ER_CRASHED_ON_USAGE                                                 = 1194
    val ER_CRASHED_ON_REPAIR                                                = 1195
    val ER_WARNING_NOT_COMPLETE_ROLLBACK                                    = 1196
    val ER_TRANS_CACHE_FULL                                                 = 1197
    val ER_SLAVE_MUST_STOP                                                  = 1198
    val ER_SLAVE_NOT_RUNNING                                                = 1199
    val ER_BAD_SLAVE                                                        = 1200
    val ER_MASTER_INFO                                                      = 1201
    val ER_SLAVE_THREAD                                                     = 1202
    val ER_TOO_MANY_USER_CONNECTIONS                                        = 1203
    val ER_SET_CONSTANTS_ONLY                                               = 1204
    val ER_LOCK_WAIT_TIMEOUT                                                = 1205
    val ER_LOCK_TABLE_FULL                                                  = 1206
    val ER_READ_ONLY_TRANSACTION                                            = 1207
    val ER_DROP_DB_WITH_READ_LOCK                                           = 1208
    val ER_CREATE_DB_WITH_READ_LOCK                                         = 1209
    val ER_WRONG_ARGUMENTS                                                  = 1210
    val ER_NO_PERMISSION_TO_CREATE_USER                                     = 1211
    val ER_UNION_TABLES_IN_DIFFERENT_DIR                                    = 1212
    val ER_LOCK_DEADLOCK                                                    = 1213
    val ER_TABLE_CANT_HANDLE_FT                                             = 1214
    val ER_CANNOT_ADD_FOREIGN                                               = 1215
    val ER_NO_REFERENCED_ROW                                                = 1216
    val ER_ROW_IS_REFERENCED                                                = 1217
    val ER_CONNECT_TO_MASTER                                                = 1218
    val ER_QUERY_ON_MASTER                                                  = 1219
    val ER_ERROR_WHEN_EXECUTING_COMMAND                                     = 1220
    val ER_WRONG_USAGE                                                      = 1221
    val ER_WRONG_NUMBER_OF_COLUMNS_IN_SELECT                                = 1222
    val ER_CANT_UPDATE_WITH_READLOCK                                        = 1223
    val ER_MIXING_NOT_ALLOWED                                               = 1224
    val ER_DUP_ARGUMENT                                                     = 1225
    val ER_USER_LIMIT_REACHED                                               = 1226
    val ER_SPECIFIC_ACCESS_DENIED_ERROR                                     = 1227
    val ER_LOCAL_VARIABLE                                                   = 1228
    val ER_GLOBAL_VARIABLE                                                  = 1229
    val ER_NO_DEFAULT                                                       = 1230
    val ER_WRONG_VALUE_FOR_VAR                                              = 1231
    val ER_WRONG_TYPE_FOR_VAR                                               = 1232
    val ER_VAR_CANT_BE_READ                                                 = 1233
    val ER_CANT_USE_OPTION_HERE                                             = 1234
    val ER_NOT_SUPPORTED_YET                                                = 1235
    val ER_MASTER_FATAL_ERROR_READING_BINLOG                                = 1236
    val ER_SLAVE_IGNORED_TABLE                                              = 1237
    val ER_INCORRECT_GLOBAL_LOCAL_VAR                                       = 1238
    val ER_WRONG_FK_DEF                                                     = 1239
    val ER_KEY_REF_DO_NOT_MATCH_TABLE_REF                                   = 1240
    val ER_OPERAND_COLUMNS                                                  = 1241
    val ER_SUBQUERY_NO_1_ROW                                                = 1242
    val ER_UNKNOWN_STMT_HANDLER                                             = 1243
    val ER_CORRUPT_HELP_DB                                                  = 1244
    val ER_CYCLIC_REFERENCE                                                 = 1245
    val ER_AUTO_CONVERT                                                     = 1246
    val ER_ILLEGAL_REFERENCE                                                = 1247
    val ER_DERIVED_MUST_HAVE_ALIAS                                          = 1248
    val ER_SELECT_REDUCED                                                   = 1249
    val ER_TABLENAME_NOT_ALLOWED_HERE                                       = 1250
    val ER_NOT_SUPPORTED_AUTH_MODE                                          = 1251
    val ER_SPATIAL_CANT_HAVE_NULL                                           = 1252
    val ER_COLLATION_CHARSET_MISMATCH                                       = 1253
    val ER_SLAVE_WAS_RUNNING                                                = 1254
    val ER_SLAVE_WAS_NOT_RUNNING                                            = 1255
    val ER_TOO_BIG_FOR_UNCOMPRESS                                           = 1256
    val ER_ZLIB_Z_MEM_ERROR                                                 = 1257
    val ER_ZLIB_Z_BUF_ERROR                                                 = 1258
    val ER_ZLIB_Z_DATA_ERROR                                                = 1259
    val ER_CUT_VALUE_GROUP_CONCAT                                           = 1260
    val ER_WARN_TOO_FEW_RECORDS                                             = 1261
    val ER_WARN_TOO_MANY_RECORDS                                            = 1262
    val ER_WARN_NULL_TO_NOTNULL                                             = 1263
    val ER_WARN_DATA_OUT_OF_RANGE                                           = 1264
    val WARN_DATA_TRUNCATED                                                 = 1265
    val ER_WARN_USING_OTHER_HANDLER                                         = 1266
    val ER_CANT_AGGREGATE_2COLLATIONS                                       = 1267
    val ER_DROP_USER                                                        = 1268
    val ER_REVOKE_GRANTS                                                    = 1269
    val ER_CANT_AGGREGATE_3COLLATIONS                                       = 1270
    val ER_CANT_AGGREGATE_NCOLLATIONS                                       = 1271
    val ER_VARIABLE_IS_NOT_STRUCT                                           = 1272
    val ER_UNKNOWN_COLLATION                                                = 1273
    val ER_SLAVE_IGNORED_SSL_PARAMS                                         = 1274
    val ER_SERVER_IS_IN_SECURE_AUTH_MODE                                    = 1275
    val ER_WARN_FIELD_RESOLVED                                              = 1276
    val ER_BAD_SLAVE_UNTIL_COND                                             = 1277
    val ER_MISSING_SKIP_SLAVE                                               = 1278
    val ER_UNTIL_COND_IGNORED                                               = 1279
    val ER_WRONG_NAME_FOR_INDEX                                             = 1280
    val ER_WRONG_NAME_FOR_CATALOG                                           = 1281
    val ER_WARN_QC_RESIZE                                                   = 1282
    val ER_BAD_FT_COLUMN                                                    = 1283
    val ER_UNKNOWN_KEY_CACHE                                                = 1284
    val ER_WARN_HOSTNAME_WONT_WORK                                          = 1285
    val ER_UNKNOWN_STORAGE_ENGINE                                           = 1286
    val ER_WARN_DEPRECATED_SYNTAX                                           = 1287
    val ER_NON_UPDATABLE_TABLE                                              = 1288
    val ER_FEATURE_DISABLED                                                 = 1289
    val ER_OPTION_PREVENTS_STATEMENT                                        = 1290
    val ER_DUPLICATED_VALUE_IN_TYPE                                         = 1291
    val ER_TRUNCATED_WRONG_VALUE                                            = 1292
    val ER_TOO_MUCH_AUTO_TIMESTAMP_COLS                                     = 1293
    val ER_INVALID_ON_UPDATE                                                = 1294
    val ER_UNSUPPORTED_PS                                                   = 1295
    val ER_GET_ERRMSG                                                       = 1296
    val ER_GET_TEMPORARY_ERRMSG                                             = 1297
    val ER_UNKNOWN_TIME_ZONE                                                = 1298
    val ER_WARN_INVALID_TIMESTAMP                                           = 1299
    val ER_INVALID_CHARACTER_STRING                                         = 1300
    val ER_WARN_ALLOWED_PACKET_OVERFLOWED                                   = 1301
    val ER_CONFLICTING_DECLARATIONS                                         = 1302
    val ER_SP_NO_RECURSIVE_CREATE                                           = 1303
    val ER_SP_ALREADY_EXISTS                                                = 1304
    val ER_SP_DOES_NOT_EXIST                                                = 1305
    val ER_SP_DROP_FAILED                                                   = 1306
    val ER_SP_STORE_FAILED                                                  = 1307
    val ER_SP_LILABEL_MISMATCH                                              = 1308
    val ER_SP_LABEL_REDEFINE                                                = 1309
    val ER_SP_LABEL_MISMATCH                                                = 1310
    val ER_SP_UNINIT_VAR                                                    = 1311
    val ER_SP_BADSELECT                                                     = 1312
    val ER_SP_BADRETURN                                                     = 1313
    val ER_SP_BADSTATEMENT                                                  = 1314
    val ER_UPDATE_LOG_DEPRECATED_IGNORED                                    = 1315
    val ER_UPDATE_LOG_DEPRECATED_TRANSLATED                                 = 1316
    val ER_QUERY_INTERRUPTED                                                = 1317
    val ER_SP_WRONG_NO_OF_ARGS                                              = 1318
    val ER_SP_COND_MISMATCH                                                 = 1319
    val ER_SP_NORETURN                                                      = 1320
    val ER_SP_NORETURNEND                                                   = 1321
    val ER_SP_BAD_CURSOR_QUERY                                              = 1322
    val ER_SP_BAD_CURSOR_SELECT                                             = 1323
    val ER_SP_CURSOR_MISMATCH                                               = 1324
    val ER_SP_CURSOR_ALREADY_OPEN                                           = 1325
    val ER_SP_CURSOR_NOT_OPEN                                               = 1326
    val ER_SP_UNDECLARED_VAR                                                = 1327
    val ER_SP_WRONG_NO_OF_FETCH_ARGS                                        = 1328
    val ER_SP_FETCH_NO_DATA                                                 = 1329
    val ER_SP_DUP_PARAM                                                     = 1330
    val ER_SP_DUP_VAR                                                       = 1331
    val ER_SP_DUP_COND                                                      = 1332
    val ER_SP_DUP_CURS                                                      = 1333
    val ER_SP_CANT_ALTER                                                    = 1334
    val ER_SP_SUBSELECT_NYI                                                 = 1335
    val ER_STMT_NOT_ALLOWED_IN_SF_OR_TRG                                    = 1336
    val ER_SP_VARCOND_AFTER_CURSHNDLR                                       = 1337
    val ER_SP_CURSOR_AFTER_HANDLER                                          = 1338
    val ER_SP_CASE_NOT_FOUND                                                = 1339
    val ER_FPARSER_TOO_BIG_FILE                                             = 1340
    val ER_FPARSER_BAD_HEADER                                               = 1341
    val ER_FPARSER_EOF_IN_COMMENT                                           = 1342
    val ER_FPARSER_ERROR_IN_PARAMETER                                       = 1343
    val ER_FPARSER_EOF_IN_UNKNOWN_PARAMETER                                 = 1344
    val ER_VIEW_NO_EXPLAIN                                                  = 1345
    val ER_FRM_UNKNOWN_TYPE                                                 = 1346
    val ER_WRONG_OBJECT                                                     = 1347
    val ER_NONUPDATEABLE_COLUMN                                             = 1348
    val ER_VIEW_SELECT_DERIVED                                              = 1349
    val ER_VIEW_SELECT_CLAUSE                                               = 1350
    val ER_VIEW_SELECT_VARIABLE                                             = 1351
    val ER_VIEW_SELECT_TMPTABLE                                             = 1352
    val ER_VIEW_WRONG_LIST                                                  = 1353
    val ER_WARN_VIEW_MERGE                                                  = 1354
    val ER_WARN_VIEW_WITHOUT_KEY                                            = 1355
    val ER_VIEW_INVALID                                                     = 1356
    val ER_SP_NO_DROP_SP                                                    = 1357
    val ER_SP_GOTO_IN_HNDLR                                                 = 1358
    val ER_TRG_ALREADY_EXISTS                                               = 1359
    val ER_TRG_DOES_NOT_EXIST                                               = 1360
    val ER_TRG_ON_VIEW_OR_TEMP_TABLE                                        = 1361
    val ER_TRG_CANT_CHANGE_ROW                                              = 1362
    val ER_TRG_NO_SUCH_ROW_IN_TRG                                           = 1363
    val ER_NO_DEFAULT_FOR_FIELD                                             = 1364
    val ER_DIVISION_BY_ZERO                                                 = 1365
    val ER_TRUNCATED_WRONG_VALUE_FOR_FIELD                                  = 1366
    val ER_ILLEGAL_VALUE_FOR_TYPE                                           = 1367
    val ER_VIEW_NONUPD_CHECK                                                = 1368
    val ER_VIEW_CHECK_FAILED                                                = 1369
    val ER_PROCACCESS_DENIED_ERROR                                          = 1370
    val ER_RELAY_LOG_FAIL                                                   = 1371
    val ER_PASSWD_LENGTH                                                    = 1372
    val ER_UNKNOWN_TARGET_BINLOG                                            = 1373
    val ER_IO_ERR_LOG_INDEX_READ                                            = 1374
    val ER_BINLOG_PURGE_PROHIBITED                                          = 1375
    val ER_FSEEK_FAIL                                                       = 1376
    val ER_BINLOG_PURGE_FATAL_ERR                                           = 1377
    val ER_LOG_IN_USE                                                       = 1378
    val ER_LOG_PURGE_UNKNOWN_ERR                                            = 1379
    val ER_RELAY_LOG_INIT                                                   = 1380
    val ER_NO_BINARY_LOGGING                                                = 1381
    val ER_RESERVED_SYNTAX                                                  = 1382
    val ER_WSAS_FAILED                                                      = 1383
    val ER_DIFF_GROUPS_PROC                                                 = 1384
    val ER_NO_GROUP_FOR_PROC                                                = 1385
    val ER_ORDER_WITH_PROC                                                  = 1386
    val ER_LOGGING_PROHIBIT_CHANGING_OF                                     = 1387
    val ER_NO_FILE_MAPPING                                                  = 1388
    val ER_WRONG_MAGIC                                                      = 1389
    val ER_PS_MANY_PARAM                                                    = 1390
    val ER_KEY_PART_0                                                       = 1391
    val ER_VIEW_CHECKSUM                                                    = 1392
    val ER_VIEW_MULTIUPDATE                                                 = 1393
    val ER_VIEW_NO_INSERT_FIELD_LIST                                        = 1394
    val ER_VIEW_DELETE_MERGE_VIEW                                           = 1395
    val ER_CANNOT_USER                                                      = 1396
    val ER_XAER_NOTA                                                        = 1397
    val ER_XAER_INVAL                                                       = 1398
    val ER_XAER_RMFAIL                                                      = 1399
    val ER_XAER_OUTSIDE                                                     = 1400
    val ER_XAER_RMERR                                                       = 1401
    val ER_XA_RBROLLBACK                                                    = 1402
    val ER_NONEXISTING_PROC_GRANT                                           = 1403
    val ER_PROC_AUTO_GRANT_FAIL                                             = 1404
    val ER_PROC_AUTO_REVOKE_FAIL                                            = 1405
    val ER_DATA_TOO_LONG                                                    = 1406
    val ER_SP_BAD_SQLSTATE                                                  = 1407
    val ER_STARTUP                                                          = 1408
    val ER_LOAD_FROM_FIXED_SIZE_ROWS_TO_VAR                                 = 1409
    val ER_CANT_CREATE_USER_WITH_GRANT                                      = 1410
    val ER_WRONG_VALUE_FOR_TYPE                                             = 1411
    val ER_TABLE_DEF_CHANGED                                                = 1412
    val ER_SP_DUP_HANDLER                                                   = 1413
    val ER_SP_NOT_VAR_ARG                                                   = 1414
    val ER_SP_NO_RETSET                                                     = 1415
    val ER_CANT_CREATE_GEOMETRY_OBJECT                                      = 1416
    val ER_FAILED_ROUTINE_BREAK_BINLOG                                      = 1417
    val ER_BINLOG_UNSAFE_ROUTINE                                            = 1418
    val ER_BINLOG_CREATE_ROUTINE_NEED_SUPER                                 = 1419
    val ER_EXEC_STMT_WITH_OPEN_CURSOR                                       = 1420
    val ER_STMT_HAS_NO_OPEN_CURSOR                                          = 1421
    val ER_COMMIT_NOT_ALLOWED_IN_SF_OR_TRG                                  = 1422
    val ER_NO_DEFAULT_FOR_VIEW_FIELD                                        = 1423
    val ER_SP_NO_RECURSION                                                  = 1424
    val ER_TOO_BIG_SCALE                                                    = 1425
    val ER_TOO_BIG_PRECISION                                                = 1426
    val ER_M_BIGGER_THAN_D                                                  = 1427
    val ER_WRONG_LOCK_OF_SYSTEM_TABLE                                       = 1428
    val ER_CONNECT_TO_FOREIGN_DATA_SOURCE                                   = 1429
    val ER_QUERY_ON_FOREIGN_DATA_SOURCE                                     = 1430
    val ER_FOREIGN_DATA_SOURCE_DOESNT_EXIST                                 = 1431
    val ER_FOREIGN_DATA_STRING_INVALID_CANT_CREATE                          = 1432
    val ER_FOREIGN_DATA_STRING_INVALID                                      = 1433
    val ER_CANT_CREATE_FEDERATED_TABLE                                      = 1434
    val ER_TRG_IN_WRONG_SCHEMA                                              = 1435
    val ER_STACK_OVERRUN_NEED_MORE                                          = 1436
    val ER_TOO_LONG_BODY                                                    = 1437
    val ER_WARN_CANT_DROP_DEFAULT_KEYCACHE                                  = 1438
    val ER_TOO_BIG_DISPLAYWIDTH                                             = 1439
    val ER_XAER_DUPID                                                       = 1440
    val ER_DATETIME_FUNCTION_OVERFLOW                                       = 1441
    val ER_CANT_UPDATE_USED_TABLE_IN_SF_OR_TRG                              = 1442
    val ER_VIEW_PREVENT_UPDATE                                              = 1443
    val ER_PS_NO_RECURSION                                                  = 1444
    val ER_SP_CANT_SET_AUTOCOMMIT                                           = 1445
    val ER_MALFORMED_DEFINER                                                = 1446
    val ER_VIEW_FRM_NO_USER                                                 = 1447
    val ER_VIEW_OTHER_USER                                                  = 1448
    val ER_NO_SUCH_USER                                                     = 1449
    val ER_FORBID_SCHEMA_CHANGE                                             = 1450
    val ER_ROW_IS_REFERENCED_2                                              = 1451
    val ER_NO_REFERENCED_ROW_2                                              = 1452
    val ER_SP_BAD_VAR_SHADOW                                                = 1453
    val ER_TRG_NO_DEFINER                                                   = 1454
    val ER_OLD_FILE_FORMAT                                                  = 1455
    val ER_SP_RECURSION_LIMIT                                               = 1456
    val ER_SP_PROC_TABLE_CORRUPT                                            = 1457
    val ER_SP_WRONG_NAME                                                    = 1458
    val ER_TABLE_NEEDS_UPGRADE                                              = 1459
    val ER_SP_NO_AGGREGATE                                                  = 1460
    val ER_MAX_PREPARED_STMT_COUNT_REACHED                                  = 1461
    val ER_VIEW_RECURSIVE                                                   = 1462
    val ER_NON_GROUPING_FIELD_USED                                          = 1463
    val ER_TABLE_CANT_HANDLE_SPKEYS                                         = 1464
    val ER_NO_TRIGGERS_ON_SYSTEM_SCHEMA                                     = 1465
    val ER_REMOVED_SPACES                                                   = 1466
    val ER_AUTOINC_READ_FAILED                                              = 1467
    val ER_USERNAME                                                         = 1468
    val ER_HOSTNAME                                                         = 1469
    val ER_WRONG_STRING_LENGTH                                              = 1470
    val ER_NON_INSERTABLE_TABLE                                             = 1471
    val ER_ADMIN_WRONG_MRG_TABLE                                            = 1472
    val ER_TOO_HIGH_LEVEL_OF_NESTING_FOR_SELECT                             = 1473
    val ER_NAME_BECOMES_EMPTY                                               = 1474
    val ER_AMBIGUOUS_FIELD_TERM                                             = 1475
    val ER_FOREIGN_SERVER_EXISTS                                            = 1476
    val ER_FOREIGN_SERVER_DOESNT_EXIST                                      = 1477
    val ER_ILLEGAL_HA_CREATE_OPTION                                         = 1478
    val ER_PARTITION_REQUIRES_VALUES_ERROR                                  = 1479
    val ER_PARTITION_WRONG_VALUES_ERROR                                     = 1480
    val ER_PARTITION_MAXVALUE_ERROR                                         = 1481
    val ER_PARTITION_SUBPARTITION_ERROR                                     = 1482
    val ER_PARTITION_SUBPART_MIX_ERROR                                      = 1483
    val ER_PARTITION_WRONG_NO_PART_ERROR                                    = 1484
    val ER_PARTITION_WRONG_NO_SUBPART_ERROR                                 = 1485
    val ER_WRONG_EXPR_IN_PARTITION_FUNC_ERROR                               = 1486
    val ER_NO_CONST_EXPR_IN_RANGE_OR_LIST_ERROR                             = 1487
    val ER_FIELD_NOT_FOUND_PART_ERROR                                       = 1488
    val ER_LIST_OF_FIELDS_ONLY_IN_HASH_ERROR                                = 1489
    val ER_INCONSISTENT_PARTITION_INFO_ERROR                                = 1490
    val ER_PARTITION_FUNC_NOT_ALLOWED_ERROR                                 = 1491
    val ER_PARTITIONS_MUST_BE_DEFINED_ERROR                                 = 1492
    val ER_RANGE_NOT_INCREASING_ERROR                                       = 1493
    val ER_INCONSISTENT_TYPE_OF_FUNCTIONS_ERROR                             = 1494
    val ER_MULTIPLE_DEF_CONST_IN_LIST_PART_ERROR                            = 1495
    val ER_PARTITION_ENTRY_ERROR                                            = 1496
    val ER_MIX_HANDLER_ERROR                                                = 1497
    val ER_PARTITION_NOT_DEFINED_ERROR                                      = 1498
    val ER_TOO_MANY_PARTITIONS_ERROR                                        = 1499
    val ER_SUBPARTITION_ERROR                                               = 1500
    val ER_CANT_CREATE_HANDLER_FILE                                         = 1501
    val ER_BLOB_FIELD_IN_PART_FUNC_ERROR                                    = 1502
    val ER_UNIQUE_KEY_NEED_ALL_FIELDS_IN_PF                                 = 1503
    val ER_NO_PARTS_ERROR                                                   = 1504
    val ER_PARTITION_MGMT_ON_NONPARTITIONED                                 = 1505
    val ER_FOREIGN_KEY_ON_PARTITIONED                                       = 1506
    val ER_DROP_PARTITION_NON_EXISTENT                                      = 1507
    val ER_DROP_LAST_PARTITION                                              = 1508
    val ER_COALESCE_ONLY_ON_HASH_PARTITION                                  = 1509
    val ER_REORG_HASH_ONLY_ON_SAME_NO                                       = 1510
    val ER_REORG_NO_PARAM_ERROR                                             = 1511
    val ER_ONLY_ON_RANGE_LIST_PARTITION                                     = 1512
    val ER_ADD_PARTITION_SUBPART_ERROR                                      = 1513
    val ER_ADD_PARTITION_NO_NEW_PARTITION                                   = 1514
    val ER_COALESCE_PARTITION_NO_PARTITION                                  = 1515
    val ER_REORG_PARTITION_NOT_EXIST                                        = 1516
    val ER_SAME_NAME_PARTITION                                              = 1517
    val ER_NO_BINLOG_ERROR                                                  = 1518
    val ER_CONSECUTIVE_REORG_PARTITIONS                                     = 1519
    val ER_REORG_OUTSIDE_RANGE                                              = 1520
    val ER_PARTITION_FUNCTION_FAILURE                                       = 1521
    val ER_PART_STATE_ERROR                                                 = 1522
    val ER_LIMITED_PART_RANGE                                               = 1523
    val ER_PLUGIN_IS_NOT_LOADED                                             = 1524
    val ER_WRONG_VALUE                                                      = 1525
    val ER_NO_PARTITION_FOR_GIVEN_VALUE                                     = 1526
    val ER_FILEGROUP_OPTION_ONLY_ONCE                                       = 1527
    val ER_CREATE_FILEGROUP_FAILED                                          = 1528
    val ER_DROP_FILEGROUP_FAILED                                            = 1529
    val ER_TABLESPACE_AUTO_EXTEND_ERROR                                     = 1530
    val ER_WRONG_SIZE_NUMBER                                                = 1531
    val ER_SIZE_OVERFLOW_ERROR                                              = 1532
    val ER_ALTER_FILEGROUP_FAILED                                           = 1533
    val ER_BINLOG_ROW_LOGGING_FAILED                                        = 1534
    val ER_BINLOG_ROW_WRONG_TABLE_DEF                                       = 1535
    val ER_BINLOG_ROW_RBR_TO_SBR                                            = 1536
    val ER_EVENT_ALREADY_EXISTS                                             = 1537
    val ER_EVENT_STORE_FAILED                                               = 1538
    val ER_EVENT_DOES_NOT_EXIST                                             = 1539
    val ER_EVENT_CANT_ALTER                                                 = 1540
    val ER_EVENT_DROP_FAILED                                                = 1541
    val ER_EVENT_INTERVAL_NOT_POSITIVE_OR_TOO_BIG                           = 1542
    val ER_EVENT_ENDS_BEFORE_STARTS                                         = 1543
    val ER_EVENT_EXEC_TIME_IN_THE_PAST                                      = 1544
    val ER_EVENT_OPEN_TABLE_FAILED                                          = 1545
    val ER_EVENT_NEITHER_M_EXPR_NOR_M_AT                                    = 1546
    val ER_COL_COUNT_DOESNT_MATCH_CORRUPTED                                 = 1547
    val ER_CANNOT_LOAD_FROM_TABLE                                           = 1548
    val ER_EVENT_CANNOT_DELETE                                              = 1549
    val ER_EVENT_COMPILE_ERROR                                              = 1550
    val ER_EVENT_SAME_NAME                                                  = 1551
    val ER_EVENT_DATA_TOO_LONG                                              = 1552
    val ER_DROP_INDEX_FK                                                    = 1553
    val ER_WARN_DEPRECATED_SYNTAX_WITH_VER                                  = 1554
    val ER_CANT_WRITE_LOCK_LOG_TABLE                                        = 1555
    val ER_CANT_LOCK_LOG_TABLE                                              = 1556
    val ER_FOREIGN_DUPLICATE_KEY                                            = 1557
    val ER_COL_COUNT_DOESNT_MATCH_PLEASE_UPDATE                             = 1558
    val ER_TEMP_TABLE_PREVENTS_SWITCH_OUT_OF_RBR                            = 1559
    val ER_STORED_FUNCTION_PREVENTS_SWITCH_BINLOG_FORMAT                    = 1560
    val ER_NDB_CANT_SWITCH_BINLOG_FORMAT                                    = 1561
    val ER_PARTITION_NO_TEMPORARY                                           = 1562
    val ER_PARTITION_CONST_DOMAIN_ERROR                                     = 1563
    val ER_PARTITION_FUNCTION_IS_NOT_ALLOWED                                = 1564
    val ER_DDL_LOG_ERROR                                                    = 1565
    val ER_NULL_IN_VALUES_LESS_THAN                                         = 1566
    val ER_WRONG_PARTITION_NAME                                             = 1567
    val ER_CANT_CHANGE_TX_CHARACTERISTICS                                   = 1568
    val ER_DUP_ENTRY_AUTOINCREMENT_CASE                                     = 1569
    val ER_EVENT_MODIFY_QUEUE_ERROR                                         = 1570
    val ER_EVENT_SET_VAR_ERROR                                              = 1571
    val ER_PARTITION_MERGE_ERROR                                            = 1572
    val ER_CANT_ACTIVATE_LOG                                                = 1573
    val ER_RBR_NOT_AVAILABLE                                                = 1574
    val ER_BASE64_DECODE_ERROR                                              = 1575
    val ER_EVENT_RECURSION_FORBIDDEN                                        = 1576
    val ER_EVENTS_DB_ERROR                                                  = 1577
    val ER_ONLY_INTEGERS_ALLOWED                                            = 1578
    val ER_UNSUPORTED_LOG_ENGINE                                            = 1579
    val ER_BAD_LOG_STATEMENT                                                = 1580
    val ER_CANT_RENAME_LOG_TABLE                                            = 1581
    val ER_WRONG_PARAMCOUNT_TO_NATIVE_FCT                                   = 1582
    val ER_WRONG_PARAMETERS_TO_NATIVE_FCT                                   = 1583
    val ER_WRONG_PARAMETERS_TO_STORED_FCT                                   = 1584
    val ER_NATIVE_FCT_NAME_COLLISION                                        = 1585
    val ER_DUP_ENTRY_WITH_KEY_NAME                                          = 1586
    val ER_BINLOG_PURGE_EMFILE                                              = 1587
    val ER_EVENT_CANNOT_CREATE_IN_THE_PAST                                  = 1588
    val ER_EVENT_CANNOT_ALTER_IN_THE_PAST                                   = 1589
    val ER_SLAVE_INCIDENT                                                   = 1590
    val ER_NO_PARTITION_FOR_GIVEN_VALUE_SILENT                              = 1591
    val ER_BINLOG_UNSAFE_STATEMENT                                          = 1592
    val ER_SLAVE_FATAL_ERROR                                                = 1593
    val ER_SLAVE_RELAY_LOG_READ_FAILURE                                     = 1594
    val ER_SLAVE_RELAY_LOG_WRITE_FAILURE                                    = 1595
    val ER_SLAVE_CREATE_EVENT_FAILURE                                       = 1596
    val ER_SLAVE_MASTER_COM_FAILURE                                         = 1597
    val ER_BINLOG_LOGGING_IMPOSSIBLE                                        = 1598
    val ER_VIEW_NO_CREATION_CTX                                             = 1599
    val ER_VIEW_INVALID_CREATION_CTX                                        = 1600
    val ER_SR_INVALID_CREATION_CTX                                          = 1601
    val ER_TRG_CORRUPTED_FILE                                               = 1602
    val ER_TRG_NO_CREATION_CTX                                              = 1603
    val ER_TRG_INVALID_CREATION_CTX                                         = 1604
    val ER_EVENT_INVALID_CREATION_CTX                                       = 1605
    val ER_TRG_CANT_OPEN_TABLE                                              = 1606
    val ER_CANT_CREATE_SROUTINE                                             = 1607
    val ER_NEVER_USED                                                       = 1608
    val ER_NO_FORMAT_DESCRIPTION_EVENT_BEFORE_BINLOG_STATEMENT              = 1609
    val ER_SLAVE_CORRUPT_EVENT                                              = 1610
    val ER_LOAD_DATA_INVALID_COLUMN                                         = 1611
    val ER_LOG_PURGE_NO_FILE                                                = 1612
    val ER_XA_RBTIMEOUT                                                     = 1613
    val ER_XA_RBDEADLOCK                                                    = 1614
    val ER_NEED_REPREPARE                                                   = 1615
    val ER_DELAYED_NOT_SUPPORTED                                            = 1616
    val WARN_NO_MASTER_INFO                                                 = 1617
    val WARN_OPTION_IGNORED                                                 = 1618
    val WARN_PLUGIN_DELETE_BUILTIN                                          = 1619
    val WARN_PLUGIN_BUSY                                                    = 1620
    val ER_VARIABLE_IS_READONLY                                             = 1621
    val ER_WARN_ENGINE_TRANSACTION_ROLLBACK                                 = 1622
    val ER_SLAVE_HEARTBEAT_FAILURE                                          = 1623
    val ER_SLAVE_HEARTBEAT_VALUE_OUT_OF_RANGE                               = 1624
    val ER_NDB_REPLICATION_SCHEMA_ERROR                                     = 1625
    val ER_CONFLICT_FN_PARSE_ERROR                                          = 1626
    val ER_EXCEPTIONS_WRITE_ERROR                                           = 1627
    val ER_TOO_LONG_TABLE_COMMENT                                           = 1628
    val ER_TOO_LONG_FIELD_COMMENT                                           = 1629
    val ER_FUNC_INEXISTENT_NAME_COLLISION                                   = 1630
    val ER_DATABASE_NAME                                                    = 1631
    val ER_TABLE_NAME                                                       = 1632
    val ER_PARTITION_NAME                                                   = 1633
    val ER_SUBPARTITION_NAME                                                = 1634
    val ER_TEMPORARY_NAME                                                   = 1635
    val ER_RENAMED_NAME                                                     = 1636
    val ER_TOO_MANY_CONCURRENT_TRXS                                         = 1637
    val WARN_NON_ASCII_SEPARATOR_NOT_IMPLEMENTED                            = 1638
    val ER_DEBUG_SYNC_TIMEOUT                                               = 1639
    val ER_DEBUG_SYNC_HIT_LIMIT                                             = 1640
    val ER_DUP_SIGNAL_SET                                                   = 1641
    val ER_SIGNAL_WARN                                                      = 1642
    val ER_SIGNAL_NOT_FOUND                                                 = 1643
    val ER_SIGNAL_EXCEPTION                                                 = 1644
    val ER_RESIGNAL_WITHOUT_ACTIVE_HANDLER                                  = 1645
    val ER_SIGNAL_BAD_CONDITION_TYPE                                        = 1646
    val WARN_COND_ITEM_TRUNCATED                                            = 1647
    val ER_COND_ITEM_TOO_LONG                                               = 1648
    val ER_UNKNOWN_LOCALE                                                   = 1649
    val ER_SLAVE_IGNORE_SERVER_IDS                                          = 1650
    val ER_QUERY_CACHE_DISABLED                                             = 1651
    val ER_SAME_NAME_PARTITION_FIELD                                        = 1652
    val ER_PARTITION_COLUMN_LIST_ERROR                                      = 1653
    val ER_WRONG_TYPE_COLUMN_VALUE_ERROR                                    = 1654
    val ER_TOO_MANY_PARTITION_FUNC_FIELDS_ERROR                             = 1655
    val ER_MAXVALUE_IN_VALUES_IN                                            = 1656
    val ER_TOO_MANY_VALUES_ERROR                                            = 1657
    val ER_ROW_SINGLE_PARTITION_FIELD_ERROR                                 = 1658
    val ER_FIELD_TYPE_NOT_ALLOWED_AS_PARTITION_FIELD                        = 1659
    val ER_PARTITION_FIELDS_TOO_LONG                                        = 1660
    val ER_BINLOG_ROW_ENGINE_AND_STMT_ENGINE                                = 1661
    val ER_BINLOG_ROW_MODE_AND_STMT_ENGINE                                  = 1662
    val ER_BINLOG_UNSAFE_AND_STMT_ENGINE                                    = 1663
    val ER_BINLOG_ROW_INJECTION_AND_STMT_ENGINE                             = 1664
    val ER_BINLOG_STMT_MODE_AND_ROW_ENGINE                                  = 1665
    val ER_BINLOG_ROW_INJECTION_AND_STMT_MODE                               = 1666
    val ER_BINLOG_MULTIPLE_ENGINES_AND_SELF_LOGGING_ENGINE                  = 1667
    val ER_BINLOG_UNSAFE_LIMIT                                              = 1668
    val ER_BINLOG_UNSAFE_INSERT_DELAYED                                     = 1669
    val ER_BINLOG_UNSAFE_SYSTEM_TABLE                                       = 1670
    val ER_BINLOG_UNSAFE_AUTOINC_COLUMNS                                    = 1671
    val ER_BINLOG_UNSAFE_UDF                                                = 1672
    val ER_BINLOG_UNSAFE_SYSTEM_VARIABLE                                    = 1673
    val ER_BINLOG_UNSAFE_SYSTEM_FUNCTION                                    = 1674
    val ER_BINLOG_UNSAFE_NONTRANS_AFTER_TRANS                               = 1675
    val ER_MESSAGE_AND_STATEMENT                                            = 1676
    val ER_SLAVE_CONVERSION_FAILED                                          = 1677
    val ER_SLAVE_CANT_CREATE_CONVERSION                                     = 1678
    val ER_INSIDE_TRANSACTION_PREVENTS_SWITCH_BINLOG_FORMAT                 = 1679
    val ER_PATH_LENGTH                                                      = 1680
    val ER_WARN_DEPRECATED_SYNTAX_NO_REPLACEMENT                            = 1681
    val ER_WRONG_NATIVE_TABLE_STRUCTURE                                     = 1682
    val ER_WRONG_PERFSCHEMA_USAGE                                           = 1683
    val ER_WARN_I_S_SKIPPED_TABLE                                           = 1684
    val ER_INSIDE_TRANSACTION_PREVENTS_SWITCH_BINLOG_DIRECT                 = 1685
    val ER_STORED_FUNCTION_PREVENTS_SWITCH_BINLOG_DIRECT                    = 1686
    val ER_SPATIAL_MUST_HAVE_GEOM_COL                                       = 1687
    val ER_TOO_LONG_INDEX_COMMENT                                           = 1688
    val ER_LOCK_ABORTED                                                     = 1689
    val ER_DATA_OUT_OF_RANGE                                                = 1690
    val ER_WRONG_SPVAR_TYPE_IN_LIMIT                                        = 1691
    val ER_BINLOG_UNSAFE_MULTIPLE_ENGINES_AND_SELF_LOGGING_ENGINE           = 1692
    val ER_BINLOG_UNSAFE_MIXED_STATEMENT                                    = 1693
    val ER_INSIDE_TRANSACTION_PREVENTS_SWITCH_SQL_LOG_BIN                   = 1694
    val ER_STORED_FUNCTION_PREVENTS_SWITCH_SQL_LOG_BIN                      = 1695
    val ER_FAILED_READ_FROM_PAR_FILE                                        = 1696
    val ER_VALUES_IS_NOT_INT_TYPE_ERROR                                     = 1697
    val ER_ACCESS_DENIED_NO_PASSWORD_ERROR                                  = 1698
    val ER_SET_PASSWORD_AUTH_PLUGIN                                         = 1699
    val ER_GRANT_PLUGIN_USER_EXISTS                                         = 1700
    val ER_TRUNCATE_ILLEGAL_FK                                              = 1701
    val ER_PLUGIN_IS_PERMANENT                                              = 1702
    val ER_SLAVE_HEARTBEAT_VALUE_OUT_OF_RANGE_MIN                           = 1703
    val ER_SLAVE_HEARTBEAT_VALUE_OUT_OF_RANGE_MAX                           = 1704
    val ER_STMT_CACHE_FULL                                                  = 1705
    val ER_MULTI_UPDATE_KEY_CONFLICT                                        = 1706
    val ER_TABLE_NEEDS_REBUILD                                              = 1707
    val WARN_OPTION_BELOW_LIMIT                                             = 1708
    val ER_INDEX_COLUMN_TOO_LONG                                            = 1709
    val ER_ERROR_IN_TRIGGER_BODY                                            = 1710
    val ER_ERROR_IN_UNKNOWN_TRIGGER_BODY                                    = 1711
    val ER_INDEX_CORRUPT                                                    = 1712
    val ER_UNDO_RECORD_TOO_BIG                                              = 1713
    val ER_BINLOG_UNSAFE_INSERT_IGNORE_SELECT                               = 1714
    val ER_BINLOG_UNSAFE_INSERT_SELECT_UPDATE                               = 1715
    val ER_BINLOG_UNSAFE_REPLACE_SELECT                                     = 1716
    val ER_BINLOG_UNSAFE_CREATE_IGNORE_SELECT                               = 1717
    val ER_BINLOG_UNSAFE_CREATE_REPLACE_SELECT                              = 1718
    val ER_BINLOG_UNSAFE_UPDATE_IGNORE                                      = 1719
    val ER_PLUGIN_NO_UNINSTALL                                              = 1720
    val ER_PLUGIN_NO_INSTALL                                                = 1721
    val ER_BINLOG_UNSAFE_WRITE_AUTOINC_SELECT                               = 1722
    val ER_BINLOG_UNSAFE_CREATE_SELECT_AUTOINC                              = 1723
    val ER_BINLOG_UNSAFE_INSERT_TWO_KEYS                                    = 1724
    val ER_TABLE_IN_FK_CHECK                                                = 1725
    val ER_UNSUPPORTED_ENGINE                                               = 1726
    val ER_BINLOG_UNSAFE_AUTOINC_NOT_FIRST                                  = 1727
    val ER_CANNOT_LOAD_FROM_TABLE_V2                                        = 1728
    val ER_MASTER_DELAY_VALUE_OUT_OF_RANGE                                  = 1729
    val ER_ONLY_FD_AND_RBR_EVENTS_ALLOWED_IN_BINLOG_STATEMENT               = 1730
    val ER_PARTITION_EXCHANGE_DIFFERENT_OPTION                              = 1731
    val ER_PARTITION_EXCHANGE_PART_TABLE                                    = 1732
    val ER_PARTITION_EXCHANGE_TEMP_TABLE                                    = 1733
    val ER_PARTITION_INSTEAD_OF_SUBPARTITION                                = 1734
    val ER_UNKNOWN_PARTITION                                                = 1735
    val ER_TABLES_DIFFERENT_METADATA                                        = 1736
    val ER_ROW_DOES_NOT_MATCH_PARTITION                                     = 1737
    val ER_BINLOG_CACHE_SIZE_GREATER_THAN_MAX                               = 1738
    val ER_WARN_INDEX_NOT_APPLICABLE                                        = 1739
    val ER_PARTITION_EXCHANGE_FOREIGN_KEY                                   = 1740
    val ER_NO_SUCH_KEY_VALUE                                                = 1741
    val ER_RPL_INFO_DATA_TOO_LONG                                           = 1742
    val ER_NETWORK_READ_EVENT_CHECKSUM_FAILURE                              = 1743
    val ER_BINLOG_READ_EVENT_CHECKSUM_FAILURE                               = 1744
    val ER_BINLOG_STMT_CACHE_SIZE_GREATER_THAN_MAX                          = 1745
    val ER_CANT_UPDATE_TABLE_IN_CREATE_TABLE_SELECT                         = 1746
    val ER_PARTITION_CLAUSE_ON_NONPARTITIONED                               = 1747
    val ER_ROW_DOES_NOT_MATCH_GIVEN_PARTITION_SET                           = 1748
    val ER_NO_SUCH_PARTITION                                                = 1749
    val ER_CHANGE_RPL_INFO_REPOSITORY_FAILURE                               = 1750
    val ER_WARNING_NOT_COMPLETE_ROLLBACK_WITH_CREATED_TEMP_TABLE            = 1751
    val ER_WARNING_NOT_COMPLETE_ROLLBACK_WITH_DROPPED_TEMP_TABLE            = 1752
    val ER_MTS_FEATURE_IS_NOT_SUPPORTED                                     = 1753
    val ER_MTS_UPDATED_DBS_GREATER_MAX                                      = 1754
    val ER_MTS_CANT_PARALLEL                                                = 1755
    val ER_MTS_INCONSISTENT_DATA                                            = 1756
    val ER_FULLTEXT_NOT_SUPPORTED_WITH_PARTITIONING                         = 1757
    val ER_DA_INVALID_CONDITION_NUMBER                                      = 1758
    val ER_INSECURE_PLAIN_TEXT                                              = 1759
    val ER_INSECURE_CHANGE_MASTER                                           = 1760
    val ER_FOREIGN_DUPLICATE_KEY_WITH_CHILD_INFO                            = 1761
    val ER_FOREIGN_DUPLICATE_KEY_WITHOUT_CHILD_INFO                         = 1762
    val ER_SQLTHREAD_WITH_SECURE_SLAVE                                      = 1763
    val ER_TABLE_HAS_NO_FT                                                  = 1764
    val ER_VARIABLE_NOT_SETTABLE_IN_SF_OR_TRIGGER                           = 1765
    val ER_VARIABLE_NOT_SETTABLE_IN_TRANSACTION                             = 1766
    val ER_GTID_NEXT_IS_NOT_IN_GTID_NEXT_LIST                               = 1767
    val ER_CANT_CHANGE_GTID_NEXT_IN_TRANSACTION_WHEN_GTID_NEXT_LIST_IS_NULL = 1768
    val ER_SET_STATEMENT_CANNOT_INVOKE_FUNCTION                             = 1769
    val ER_GTID_NEXT_CANT_BE_AUTOMATIC_IF_GTID_NEXT_LIST_IS_NON_NULL        = 1770
    val ER_SKIPPING_LOGGED_TRANSACTION                                      = 1771
    val ER_MALFORMED_GTID_SET_SPECIFICATION                                 = 1772
    val ER_MALFORMED_GTID_SET_ENCODING                                      = 1773
    val ER_MALFORMED_GTID_SPECIFICATION                                     = 1774
    val ER_GNO_EXHAUSTED                                                    = 1775
    val ER_BAD_SLAVE_AUTO_POSITION                                          = 1776
    val ER_AUTO_POSITION_REQUIRES_GTID_MODE_ON                              = 1777
    val ER_CANT_DO_IMPLICIT_COMMIT_IN_TRX_WHEN_GTID_NEXT_IS_SET             = 1778
    val ER_GTID_MODE_2_OR_3_REQUIRES_ENFORCE_GTID_CONSISTENCY_ON            = 1779
    val ER_GTID_MODE_REQUIRES_BINLOG                                        = 1780
    val ER_CANT_SET_GTID_NEXT_TO_GTID_WHEN_GTID_MODE_IS_OFF                 = 1781
    val ER_CANT_SET_GTID_NEXT_TO_ANONYMOUS_WHEN_GTID_MODE_IS_ON             = 1782
    val ER_CANT_SET_GTID_NEXT_LIST_TO_NON_NULL_WHEN_GTID_MODE_IS_OFF        = 1783
    val ER_FOUND_GTID_EVENT_WHEN_GTID_MODE_IS_OFF                           = 1784
    val ER_GTID_UNSAFE_NON_TRANSACTIONAL_TABLE                              = 1785
    val ER_GTID_UNSAFE_CREATE_SELECT                                        = 1786
    val ER_GTID_UNSAFE_CREATE_DROP_TEMPORARY_TABLE_IN_TRANSACTION           = 1787
    val ER_GTID_MODE_CAN_ONLY_CHANGE_ONE_STEP_AT_A_TIME                     = 1788
    val ER_MASTER_HAS_PURGED_REQUIRED_GTIDS                                 = 1789
    val ER_CANT_SET_GTID_NEXT_WHEN_OWNING_GTID                              = 1790
    val ER_UNKNOWN_EXPLAIN_FORMAT                                           = 1791
    val ER_CANT_EXECUTE_IN_READ_ONLY_TRANSACTION                            = 1792
    val ER_TOO_LONG_TABLE_PARTITION_COMMENT                                 = 1793
    val ER_SLAVE_CONFIGURATION                                              = 1794
    val ER_INNODB_FT_LIMIT                                                  = 1795
    val ER_INNODB_NO_FT_TEMP_TABLE                                          = 1796
    val ER_INNODB_FT_WRONG_DOCID_COLUMN                                     = 1797
    val ER_INNODB_FT_WRONG_DOCID_INDEX                                      = 1798
    val ER_INNODB_ONLINE_LOG_TOO_BIG                                        = 1799
    val ER_UNKNOWN_ALTER_ALGORITHM                                          = 1800
    val ER_UNKNOWN_ALTER_LOCK                                               = 1801
    val ER_MTS_CHANGE_MASTER_CANT_RUN_WITH_GAPS                             = 1802
    val ER_MTS_RECOVERY_FAILURE                                             = 1803
    val ER_MTS_RESET_WORKERS                                                = 1804
    val ER_COL_COUNT_DOESNT_MATCH_CORRUPTED_V2                              = 1805
    val ER_SLAVE_SILENT_RETRY_TRANSACTION                                   = 1806
    val ER_DISCARD_FK_CHECKS_RUNNING                                        = 1807
    val ER_TABLE_SCHEMA_MISMATCH                                            = 1808
    val ER_TABLE_IN_SYSTEM_TABLESPACE                                       = 1809
    val ER_IO_READ_ERROR                                                    = 1810
    val ER_IO_WRITE_ERROR                                                   = 1811
    val ER_TABLESPACE_MISSING                                               = 1812
    val ER_TABLESPACE_EXISTS                                                = 1813
    val ER_TABLESPACE_DISCARDED                                             = 1814
    val ER_INTERNAL_ERROR                                                   = 1815
    val ER_INNODB_IMPORT_ERROR                                              = 1816
    val ER_INNODB_INDEX_CORRUPT                                             = 1817
    val ER_INVALID_YEAR_COLUMN_LENGTH                                       = 1818
    val ER_NOT_VALID_PASSWORD                                               = 1819
    val ER_MUST_CHANGE_PASSWORD                                             = 1820
    val ER_FK_NO_INDEX_CHILD                                                = 1821
    val ER_FK_NO_INDEX_PARENT                                               = 1822
    val ER_FK_FAIL_ADD_SYSTEM                                               = 1823
    val ER_FK_CANNOT_OPEN_PARENT                                            = 1824
    val ER_FK_INCORRECT_OPTION                                              = 1825
    val ER_FK_DUP_NAME                                                      = 1826
    val ER_PASSWORD_FORMAT                                                  = 1827
    val ER_FK_COLUMN_CANNOT_DROP                                            = 1828
    val ER_FK_COLUMN_CANNOT_DROP_CHILD                                      = 1829
    val ER_FK_COLUMN_NOT_NULL                                               = 1830
    val ER_DUP_INDEX                                                        = 1831
    val ER_FK_COLUMN_CANNOT_CHANGE                                          = 1832
    val ER_FK_COLUMN_CANNOT_CHANGE_CHILD                                    = 1833
    val ER_FK_CANNOT_DELETE_PARENT                                          = 1834
    val ER_MALFORMED_PACKET                                                 = 1835
    val ER_READ_ONLY_MODE                                                   = 1836
    val ER_GTID_NEXT_TYPE_UNDEFINED_GROUP                                   = 1837
    val ER_VARIABLE_NOT_SETTABLE_IN_SP                                      = 1838
    val ER_CANT_SET_GTID_PURGED_WHEN_GTID_MODE_IS_OFF                       = 1839
    val ER_CANT_SET_GTID_PURGED_WHEN_GTID_EXECUTED_IS_NOT_EMPTY             = 1840
    val ER_CANT_SET_GTID_PURGED_WHEN_OWNED_GTIDS_IS_NOT_EMPTY               = 1841
    val ER_GTID_PURGED_WAS_CHANGED                                          = 1842
    val ER_GTID_EXECUTED_WAS_CHANGED                                        = 1843
    val ER_BINLOG_STMT_MODE_AND_NO_REPL_TABLES                              = 1844
    val ER_ALTER_OPERATION_NOT_SUPPORTED                                    = 1845
    val ER_ALTER_OPERATION_NOT_SUPPORTED_REASON                             = 1846
    val ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_COPY                        = 1847
    val ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_PARTITION                   = 1848
    val ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_FK_RENAME                   = 1849
    val ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_COLUMN_TYPE                 = 1850
    val ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_FK_CHECK                    = 1851
    val ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_IGNORE                      = 1852
    val ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_NOPK                        = 1853
    val ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_AUTOINC                     = 1854
    val ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_HIDDEN_FTS                  = 1855
    val ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_CHANGE_FTS                  = 1856
    val ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_FTS                         = 1857
    val ER_SQL_SLAVE_SKIP_COUNTER_NOT_SETTABLE_IN_GTID_MODE                 = 1858
    val ER_DUP_UNKNOWN_IN_INDEX                                             = 1859
    val ER_IDENT_CAUSES_TOO_LONG_PATH                                       = 1860
    val ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_NOT_NULL                    = 1861
    val ER_MUST_CHANGE_PASSWORD_LOGIN                                       = 1862
    val ER_ROW_IN_WRONG_PARTITION                                           = 1863
    val ER_MTS_EVENT_BIGGER_PENDING_JOBS_SIZE_MAX                           = 1864
    val ER_INNODB_NO_FT_USES_PARSER                                         = 1865
    val ER_BINLOG_LOGICAL_CORRUPTION                                        = 1866
    val ER_WARN_PURGE_LOG_IN_USE                                            = 1867
    val ER_WARN_PURGE_LOG_IS_ACTIVE                                         = 1868
    val ER_AUTO_INCREMENT_CONFLICT                                          = 1869
    val WARN_ON_BLOCKHOLE_IN_RBR                                            = 1870
    val ER_SLAVE_MI_INIT_REPOSITORY                                         = 1871
    val ER_SLAVE_RLI_INIT_REPOSITORY                                        = 1872
    val ER_ACCESS_DENIED_CHANGE_USER_ERROR                                  = 1873
    val ER_INNODB_READ_ONLY                                                 = 1874
    val ER_STOP_SLAVE_SQL_THREAD_TIMEOUT                                    = 1875
    val ER_STOP_SLAVE_IO_THREAD_TIMEOUT                                     = 1876
    val ER_TABLE_CORRUPT                                                    = 1877
    val ER_TEMP_FILE_WRITE_FAILURE                                          = 1878
    val ER_INNODB_FT_AUX_NOT_HEX_ID                                         = 1879
    val ER_OLD_TEMPORALS_UPGRADED                                           = 1880
    val ER_INNODB_FORCED_RECOVERY                                           = 1881
    val ER_AES_INVALID_IV                                                   = 1882

    // Lookup-by-number table
    val names = mapOf(
        1 to  "EE_CANTCREATEFILE",
        2 to  "EE_READ",
        3 to  "EE_WRITE",
        4 to  "EE_BADCLOSE",
        5 to  "EE_OUTOFMEMORY",
        6 to  "EE_DELETE",
        7 to  "EE_LINK",
        9 to  "EE_EOFERR",
        10 to  "EE_CANTLOCK",
        11 to  "EE_CANTUNLOCK",
        12 to  "EE_DIR",
        13 to  "EE_STAT",
        14 to  "EE_CANT_CHSIZE",
        15 to  "EE_CANT_OPEN_STREAM",
        16 to  "EE_GETWD",
        17 to  "EE_SETWD",
        18 to  "EE_LINK_WARNING",
        19 to  "EE_OPEN_WARNING",
        20 to  "EE_DISK_FULL",
        21 to  "EE_CANT_MKDIR",
        22 to  "EE_UNKNOWN_CHARSET",
        23 to  "EE_OUT_OF_FILERESOURCES",
        24 to  "EE_CANT_READLINK",
        25 to  "EE_CANT_SYMLINK",
        26 to  "EE_REALPATH",
        27 to  "EE_SYNC",
        28 to  "EE_UNKNOWN_COLLATION",
        29 to  "EE_FILENOTFOUND",
        30 to  "EE_FILE_NOT_CLOSED",
        31 to  "EE_CHANGE_OWNERSHIP",
        32 to  "EE_CHANGE_PERMISSIONS",
        33 to  "EE_CANT_SEEK",
        120 to  "HA_ERR_KEY_NOT_FOUND",
        121 to  "HA_ERR_FOUND_DUPP_KEY",
        122 to  "HA_ERR_INTERNAL_ERROR",
        123 to  "HA_ERR_RECORD_CHANGED",
        124 to  "HA_ERR_WRONG_INDEX",
        126 to  "HA_ERR_CRASHED",
        127 to  "HA_ERR_WRONG_IN_RECORD",
        128 to  "HA_ERR_OUT_OF_MEM",
        130 to  "HA_ERR_NOT_A_TABLE",
        131 to  "HA_ERR_WRONG_COMMAND",
        132 to  "HA_ERR_OLD_FILE",
        133 to  "HA_ERR_NO_ACTIVE_RECORD",
        134 to  "HA_ERR_RECORD_DELETED",
        135 to  "HA_ERR_RECORD_FILE_FULL",
        136 to  "HA_ERR_INDEX_FILE_FULL",
        137 to  "HA_ERR_END_OF_FILE",
        138 to  "HA_ERR_UNSUPPORTED",
        139 to  "HA_ERR_TO_BIG_ROW",
        140 to  "HA_WRONG_CREATE_OPTION",
        141 to  "HA_ERR_FOUND_DUPP_UNIQUE",
        142 to  "HA_ERR_UNKNOWN_CHARSET",
        143 to  "HA_ERR_WRONG_MRG_TABLE_DEF",
        144 to  "HA_ERR_CRASHED_ON_REPAIR",
        145 to  "HA_ERR_CRASHED_ON_USAGE",
        146 to  "HA_ERR_LOCK_WAIT_TIMEOUT",
        147 to  "HA_ERR_LOCK_TABLE_FULL",
        148 to  "HA_ERR_READ_ONLY_TRANSACTION",
        149 to  "HA_ERR_LOCK_DEADLOCK",
        150 to  "HA_ERR_CANNOT_ADD_FOREIGN",
        151 to  "HA_ERR_NO_REFERENCED_ROW",
        152 to  "HA_ERR_ROW_IS_REFERENCED",
        153 to  "HA_ERR_NO_SAVEPOINT",
        154 to  "HA_ERR_NON_UNIQUE_BLOCK_SIZE",
        155 to  "HA_ERR_NO_SUCH_TABLE",
        156 to  "HA_ERR_TABLE_EXIST",
        157 to  "HA_ERR_NO_CONNECTION",
        158 to  "HA_ERR_NULL_IN_SPATIAL",
        159 to  "HA_ERR_TABLE_DEF_CHANGED",
        160 to  "HA_ERR_NO_PARTITION_FOUND",
        161 to  "HA_ERR_RBR_LOGGING_FAILED",
        162 to  "HA_ERR_DROP_INDEX_FK",
        163 to  "HA_ERR_FOREIGN_DUPLICATE_KEY",
        164 to  "HA_ERR_TABLE_NEEDS_UPGRADE",
        165 to  "HA_ERR_TABLE_READONLY",
        166 to  "HA_ERR_AUTOINC_READ_FAILED",
        167 to  "HA_ERR_AUTOINC_ERANGE",
        168 to  "HA_ERR_GENERIC",
        169 to  "HA_ERR_RECORD_IS_THE_SAME",
        170 to  "HA_ERR_LOGGING_IMPOSSIBLE",
        171 to  "HA_ERR_CORRUPT_EVENT",
        172 to  "HA_ERR_NEW_FILE",
        173 to  "HA_ERR_ROWS_EVENT_APPLY",
        174 to  "HA_ERR_INITIALIZATION",
        175 to  "HA_ERR_FILE_TOO_SHORT",
        176 to  "HA_ERR_WRONG_CRC",
        177 to  "HA_ERR_TOO_MANY_CONCURRENT_TRXS",
        178 to  "HA_ERR_NOT_IN_LOCK_PARTITIONS",
        179 to  "HA_ERR_INDEX_COL_TOO_LONG",
        180 to  "HA_ERR_INDEX_CORRUPT",
        181 to  "HA_ERR_UNDO_REC_TOO_BIG",
        182 to  "HA_FTS_INVALID_DOCID",
        183 to  "HA_ERR_TABLE_IN_FK_CHECK",
        184 to  "HA_ERR_TABLESPACE_EXISTS",
        185 to  "HA_ERR_TOO_MANY_FIELDS",
        186 to  "HA_ERR_ROW_IN_WRONG_PARTITION",
        187 to  "HA_ERR_INNODB_READ_ONLY",
        188 to  "HA_ERR_FTS_EXCEED_RESULT_CACHE_LIMIT",
        189 to  "HA_ERR_TEMP_FILE_WRITE_FAILURE",
        190 to  "HA_ERR_INNODB_FORCED_RECOVERY",
        191 to  "HA_ERR_FTS_TOO_MANY_WORDS_IN_PHRASE",
        1000 to  "ER_HASHCHK",
        1001 to  "ER_NISAMCHK",
        1002 to  "ER_NO",
        1003 to  "ER_YES",
        1004 to  "ER_CANT_CREATE_FILE",
        1005 to  "ER_CANT_CREATE_TABLE",
        1006 to  "ER_CANT_CREATE_DB",
        1007 to  "ER_DB_CREATE_EXISTS",
        1008 to  "ER_DB_DROP_EXISTS",
        1009 to  "ER_DB_DROP_DELETE",
        1010 to  "ER_DB_DROP_RMDIR",
        1011 to  "ER_CANT_DELETE_FILE",
        1012 to  "ER_CANT_FIND_SYSTEM_REC",
        1013 to  "ER_CANT_GET_STAT",
        1014 to  "ER_CANT_GET_WD",
        1015 to  "ER_CANT_LOCK",
        1016 to  "ER_CANT_OPEN_FILE",
        1017 to  "ER_FILE_NOT_FOUND",
        1018 to  "ER_CANT_READ_DIR",
        1019 to  "ER_CANT_SET_WD",
        1020 to  "ER_CHECKREAD",
        1021 to  "ER_DISK_FULL",
        1022 to  "ER_DUP_KEY",
        1023 to  "ER_ERROR_ON_CLOSE",
        1024 to  "ER_ERROR_ON_READ",
        1025 to  "ER_ERROR_ON_RENAME",
        1026 to  "ER_ERROR_ON_WRITE",
        1027 to  "ER_FILE_USED",
        1028 to  "ER_FILSORT_ABORT",
        1029 to  "ER_FORM_NOT_FOUND",
        1030 to  "ER_GET_ERRNO",
        1031 to  "ER_ILLEGAL_HA",
        1032 to  "ER_KEY_NOT_FOUND",
        1033 to  "ER_NOT_FORM_FILE",
        1034 to  "ER_NOT_KEYFILE",
        1035 to  "ER_OLD_KEYFILE",
        1036 to  "ER_OPEN_AS_READONLY",
        1037 to  "ER_OUTOFMEMORY",
        1038 to  "ER_OUT_OF_SORTMEMORY",
        1039 to  "ER_UNEXPECTED_EOF",
        1040 to  "ER_CON_COUNT_ERROR",
        1041 to  "ER_OUT_OF_RESOURCES",
        1042 to  "ER_BAD_HOST_ERROR",
        1043 to  "ER_HANDSHAKE_ERROR",
        1044 to  "ER_DBACCESS_DENIED_ERROR",
        1045 to  "ER_ACCESS_DENIED_ERROR",
        1046 to  "ER_NO_DB_ERROR",
        1047 to  "ER_UNKNOWN_COM_ERROR",
        1048 to  "ER_BAD_NULL_ERROR",
        1049 to  "ER_BAD_DB_ERROR",
        1050 to  "ER_TABLE_EXISTS_ERROR",
        1051 to  "ER_BAD_TABLE_ERROR",
        1052 to  "ER_NON_UNIQ_ERROR",
        1053 to  "ER_SERVER_SHUTDOWN",
        1054 to  "ER_BAD_FIELD_ERROR",
        1055 to  "ER_WRONG_FIELD_WITH_GROUP",
        1056 to  "ER_WRONG_GROUP_FIELD",
        1057 to  "ER_WRONG_SUM_SELECT",
        1058 to  "ER_WRONG_VALUE_COUNT",
        1059 to  "ER_TOO_LONG_IDENT",
        1060 to  "ER_DUP_FIELDNAME",
        1061 to  "ER_DUP_KEYNAME",
        1062 to  "ER_DUP_ENTRY",
        1063 to  "ER_WRONG_FIELD_SPEC",
        1064 to  "ER_PARSE_ERROR",
        1065 to  "ER_EMPTY_QUERY",
        1066 to  "ER_NONUNIQ_TABLE",
        1067 to  "ER_INVALID_DEFAULT",
        1068 to  "ER_MULTIPLE_PRI_KEY",
        1069 to  "ER_TOO_MANY_KEYS",
        1070 to  "ER_TOO_MANY_KEY_PARTS",
        1071 to  "ER_TOO_LONG_KEY",
        1072 to  "ER_KEY_COLUMN_DOES_NOT_EXITS",
        1073 to  "ER_BLOB_USED_AS_KEY",
        1074 to  "ER_TOO_BIG_FIELDLENGTH",
        1075 to  "ER_WRONG_AUTO_KEY",
        1076 to  "ER_READY",
        1077 to  "ER_NORMAL_SHUTDOWN",
        1078 to  "ER_GOT_SIGNAL",
        1079 to  "ER_SHUTDOWN_COMPLETE",
        1080 to  "ER_FORCING_CLOSE",
        1081 to  "ER_IPSOCK_ERROR",
        1082 to  "ER_NO_SUCH_INDEX",
        1083 to  "ER_WRONG_FIELD_TERMINATORS",
        1084 to  "ER_BLOBS_AND_NO_TERMINATED",
        1085 to  "ER_TEXTFILE_NOT_READABLE",
        1086 to  "ER_FILE_EXISTS_ERROR",
        1087 to  "ER_LOAD_INFO",
        1088 to  "ER_ALTER_INFO",
        1089 to  "ER_WRONG_SUB_KEY",
        1090 to  "ER_CANT_REMOVE_ALL_FIELDS",
        1091 to  "ER_CANT_DROP_FIELD_OR_KEY",
        1092 to  "ER_INSERT_INFO",
        1093 to  "ER_UPDATE_TABLE_USED",
        1094 to  "ER_NO_SUCH_THREAD",
        1095 to  "ER_KILL_DENIED_ERROR",
        1096 to  "ER_NO_TABLES_USED",
        1097 to  "ER_TOO_BIG_SET",
        1098 to  "ER_NO_UNIQUE_LOGFILE",
        1099 to  "ER_TABLE_NOT_LOCKED_FOR_WRITE",
        1100 to  "ER_TABLE_NOT_LOCKED",
        1101 to  "ER_BLOB_CANT_HAVE_DEFAULT",
        1102 to  "ER_WRONG_DB_NAME",
        1103 to  "ER_WRONG_TABLE_NAME",
        1104 to  "ER_TOO_BIG_SELECT",
        1105 to  "ER_UNKNOWN_ERROR",
        1106 to  "ER_UNKNOWN_PROCEDURE",
        1107 to  "ER_WRONG_PARAMCOUNT_TO_PROCEDURE",
        1108 to  "ER_WRONG_PARAMETERS_TO_PROCEDURE",
        1109 to  "ER_UNKNOWN_TABLE",
        1110 to  "ER_FIELD_SPECIFIED_TWICE",
        1111 to  "ER_INVALID_GROUP_FUNC_USE",
        1112 to  "ER_UNSUPPORTED_EXTENSION",
        1113 to  "ER_TABLE_MUST_HAVE_COLUMNS",
        1114 to  "ER_RECORD_FILE_FULL",
        1115 to  "ER_UNKNOWN_CHARACTER_SET",
        1116 to  "ER_TOO_MANY_TABLES",
        1117 to  "ER_TOO_MANY_FIELDS",
        1118 to  "ER_TOO_BIG_ROWSIZE",
        1119 to  "ER_STACK_OVERRUN",
        1120 to  "ER_WRONG_OUTER_JOIN",
        1121 to  "ER_NULL_COLUMN_IN_INDEX",
        1122 to  "ER_CANT_FIND_UDF",
        1123 to  "ER_CANT_INITIALIZE_UDF",
        1124 to  "ER_UDF_NO_PATHS",
        1125 to  "ER_UDF_EXISTS",
        1126 to  "ER_CANT_OPEN_LIBRARY",
        1127 to  "ER_CANT_FIND_DL_ENTRY",
        1128 to  "ER_FUNCTION_NOT_DEFINED",
        1129 to  "ER_HOST_IS_BLOCKED",
        1130 to  "ER_HOST_NOT_PRIVILEGED",
        1131 to  "ER_PASSWORD_ANONYMOUS_USER",
        1132 to  "ER_PASSWORD_NOT_ALLOWED",
        1133 to  "ER_PASSWORD_NO_MATCH",
        1134 to  "ER_UPDATE_INFO",
        1135 to  "ER_CANT_CREATE_THREAD",
        1136 to  "ER_WRONG_VALUE_COUNT_ON_ROW",
        1137 to  "ER_CANT_REOPEN_TABLE",
        1138 to  "ER_INVALID_USE_OF_NULL",
        1139 to  "ER_REGEXP_ERROR",
        1140 to  "ER_MIX_OF_GROUP_FUNC_AND_FIELDS",
        1141 to  "ER_NONEXISTING_GRANT",
        1142 to  "ER_TABLEACCESS_DENIED_ERROR",
        1143 to  "ER_COLUMNACCESS_DENIED_ERROR",
        1144 to  "ER_ILLEGAL_GRANT_FOR_TABLE",
        1145 to  "ER_GRANT_WRONG_HOST_OR_USER",
        1146 to  "ER_NO_SUCH_TABLE",
        1147 to  "ER_NONEXISTING_TABLE_GRANT",
        1148 to  "ER_NOT_ALLOWED_COMMAND",
        1149 to  "ER_SYNTAX_ERROR",
        1150 to  "ER_DELAYED_CANT_CHANGE_LOCK",
        1151 to  "ER_TOO_MANY_DELAYED_THREADS",
        1152 to  "ER_ABORTING_CONNECTION",
        1153 to  "ER_NET_PACKET_TOO_LARGE",
        1154 to  "ER_NET_READ_ERROR_FROM_PIPE",
        1155 to  "ER_NET_FCNTL_ERROR",
        1156 to  "ER_NET_PACKETS_OUT_OF_ORDER",
        1157 to  "ER_NET_UNCOMPRESS_ERROR",
        1158 to  "ER_NET_READ_ERROR",
        1159 to  "ER_NET_READ_INTERRUPTED",
        1160 to  "ER_NET_ERROR_ON_WRITE",
        1161 to  "ER_NET_WRITE_INTERRUPTED",
        1162 to  "ER_TOO_LONG_STRING",
        1163 to  "ER_TABLE_CANT_HANDLE_BLOB",
        1164 to  "ER_TABLE_CANT_HANDLE_AUTO_INCREMENT",
        1165 to  "ER_DELAYED_INSERT_TABLE_LOCKED",
        1166 to  "ER_WRONG_COLUMN_NAME",
        1167 to  "ER_WRONG_KEY_COLUMN",
        1168 to  "ER_WRONG_MRG_TABLE",
        1169 to  "ER_DUP_UNIQUE",
        1170 to  "ER_BLOB_KEY_WITHOUT_LENGTH",
        1171 to  "ER_PRIMARY_CANT_HAVE_NULL",
        1172 to  "ER_TOO_MANY_ROWS",
        1173 to  "ER_REQUIRES_PRIMARY_KEY",
        1174 to  "ER_NO_RAID_COMPILED",
        1175 to  "ER_UPDATE_WITHOUT_KEY_IN_SAFE_MODE",
        1176 to  "ER_KEY_DOES_NOT_EXITS",
        1177 to  "ER_CHECK_NO_SUCH_TABLE",
        1178 to  "ER_CHECK_NOT_IMPLEMENTED",
        1179 to  "ER_CANT_DO_THIS_DURING_AN_TRANSACTION",
        1180 to  "ER_ERROR_DURING_COMMIT",
        1181 to  "ER_ERROR_DURING_ROLLBACK",
        1182 to  "ER_ERROR_DURING_FLUSH_LOGS",
        1183 to  "ER_ERROR_DURING_CHECKPOINT",
        1184 to  "ER_NEW_ABORTING_CONNECTION",
        1185 to  "ER_DUMP_NOT_IMPLEMENTED",
        1186 to  "ER_FLUSH_MASTER_BINLOG_CLOSED",
        1187 to  "ER_INDEX_REBUILD",
        1188 to  "ER_MASTER",
        1189 to  "ER_MASTER_NET_READ",
        1190 to  "ER_MASTER_NET_WRITE",
        1191 to  "ER_FT_MATCHING_KEY_NOT_FOUND",
        1192 to  "ER_LOCK_OR_ACTIVE_TRANSACTION",
        1193 to  "ER_UNKNOWN_SYSTEM_VARIABLE",
        1194 to  "ER_CRASHED_ON_USAGE",
        1195 to  "ER_CRASHED_ON_REPAIR",
        1196 to  "ER_WARNING_NOT_COMPLETE_ROLLBACK",
        1197 to  "ER_TRANS_CACHE_FULL",
        1198 to  "ER_SLAVE_MUST_STOP",
        1199 to  "ER_SLAVE_NOT_RUNNING",
        1200 to  "ER_BAD_SLAVE",
        1201 to  "ER_MASTER_INFO",
        1202 to  "ER_SLAVE_THREAD",
        1203 to  "ER_TOO_MANY_USER_CONNECTIONS",
        1204 to  "ER_SET_CONSTANTS_ONLY",
        1205 to  "ER_LOCK_WAIT_TIMEOUT",
        1206 to  "ER_LOCK_TABLE_FULL",
        1207 to  "ER_READ_ONLY_TRANSACTION",
        1208 to  "ER_DROP_DB_WITH_READ_LOCK",
        1209 to  "ER_CREATE_DB_WITH_READ_LOCK",
        1210 to  "ER_WRONG_ARGUMENTS",
        1211 to  "ER_NO_PERMISSION_TO_CREATE_USER",
        1212 to  "ER_UNION_TABLES_IN_DIFFERENT_DIR",
        1213 to  "ER_LOCK_DEADLOCK",
        1214 to  "ER_TABLE_CANT_HANDLE_FT",
        1215 to  "ER_CANNOT_ADD_FOREIGN",
        1216 to  "ER_NO_REFERENCED_ROW",
        1217 to  "ER_ROW_IS_REFERENCED",
        1218 to  "ER_CONNECT_TO_MASTER",
        1219 to  "ER_QUERY_ON_MASTER",
        1220 to  "ER_ERROR_WHEN_EXECUTING_COMMAND",
        1221 to  "ER_WRONG_USAGE",
        1222 to  "ER_WRONG_NUMBER_OF_COLUMNS_IN_SELECT",
        1223 to  "ER_CANT_UPDATE_WITH_READLOCK",
        1224 to  "ER_MIXING_NOT_ALLOWED",
        1225 to  "ER_DUP_ARGUMENT",
        1226 to  "ER_USER_LIMIT_REACHED",
        1227 to  "ER_SPECIFIC_ACCESS_DENIED_ERROR",
        1228 to  "ER_LOCAL_VARIABLE",
        1229 to  "ER_GLOBAL_VARIABLE",
        1230 to  "ER_NO_DEFAULT",
        1231 to  "ER_WRONG_VALUE_FOR_VAR",
        1232 to  "ER_WRONG_TYPE_FOR_VAR",
        1233 to  "ER_VAR_CANT_BE_READ",
        1234 to  "ER_CANT_USE_OPTION_HERE",
        1235 to  "ER_NOT_SUPPORTED_YET",
        1236 to  "ER_MASTER_FATAL_ERROR_READING_BINLOG",
        1237 to  "ER_SLAVE_IGNORED_TABLE",
        1238 to  "ER_INCORRECT_GLOBAL_LOCAL_VAR",
        1239 to  "ER_WRONG_FK_DEF",
        1240 to  "ER_KEY_REF_DO_NOT_MATCH_TABLE_REF",
        1241 to  "ER_OPERAND_COLUMNS",
        1242 to  "ER_SUBQUERY_NO_1_ROW",
        1243 to  "ER_UNKNOWN_STMT_HANDLER",
        1244 to  "ER_CORRUPT_HELP_DB",
        1245 to  "ER_CYCLIC_REFERENCE",
        1246 to  "ER_AUTO_CONVERT",
        1247 to  "ER_ILLEGAL_REFERENCE",
        1248 to  "ER_DERIVED_MUST_HAVE_ALIAS",
        1249 to  "ER_SELECT_REDUCED",
        1250 to  "ER_TABLENAME_NOT_ALLOWED_HERE",
        1251 to  "ER_NOT_SUPPORTED_AUTH_MODE",
        1252 to  "ER_SPATIAL_CANT_HAVE_NULL",
        1253 to  "ER_COLLATION_CHARSET_MISMATCH",
        1254 to  "ER_SLAVE_WAS_RUNNING",
        1255 to  "ER_SLAVE_WAS_NOT_RUNNING",
        1256 to  "ER_TOO_BIG_FOR_UNCOMPRESS",
        1257 to  "ER_ZLIB_Z_MEM_ERROR",
        1258 to  "ER_ZLIB_Z_BUF_ERROR",
        1259 to  "ER_ZLIB_Z_DATA_ERROR",
        1260 to  "ER_CUT_VALUE_GROUP_CONCAT",
        1261 to  "ER_WARN_TOO_FEW_RECORDS",
        1262 to  "ER_WARN_TOO_MANY_RECORDS",
        1263 to  "ER_WARN_NULL_TO_NOTNULL",
        1264 to  "ER_WARN_DATA_OUT_OF_RANGE",
        1265 to  "WARN_DATA_TRUNCATED",
        1266 to  "ER_WARN_USING_OTHER_HANDLER",
        1267 to  "ER_CANT_AGGREGATE_2COLLATIONS",
        1268 to  "ER_DROP_USER",
        1269 to  "ER_REVOKE_GRANTS",
        1270 to  "ER_CANT_AGGREGATE_3COLLATIONS",
        1271 to  "ER_CANT_AGGREGATE_NCOLLATIONS",
        1272 to  "ER_VARIABLE_IS_NOT_STRUCT",
        1273 to  "ER_UNKNOWN_COLLATION",
        1274 to  "ER_SLAVE_IGNORED_SSL_PARAMS",
        1275 to  "ER_SERVER_IS_IN_SECURE_AUTH_MODE",
        1276 to  "ER_WARN_FIELD_RESOLVED",
        1277 to  "ER_BAD_SLAVE_UNTIL_COND",
        1278 to  "ER_MISSING_SKIP_SLAVE",
        1279 to  "ER_UNTIL_COND_IGNORED",
        1280 to  "ER_WRONG_NAME_FOR_INDEX",
        1281 to  "ER_WRONG_NAME_FOR_CATALOG",
        1282 to  "ER_WARN_QC_RESIZE",
        1283 to  "ER_BAD_FT_COLUMN",
        1284 to  "ER_UNKNOWN_KEY_CACHE",
        1285 to  "ER_WARN_HOSTNAME_WONT_WORK",
        1286 to  "ER_UNKNOWN_STORAGE_ENGINE",
        1287 to  "ER_WARN_DEPRECATED_SYNTAX",
        1288 to  "ER_NON_UPDATABLE_TABLE",
        1289 to  "ER_FEATURE_DISABLED",
        1290 to  "ER_OPTION_PREVENTS_STATEMENT",
        1291 to  "ER_DUPLICATED_VALUE_IN_TYPE",
        1292 to  "ER_TRUNCATED_WRONG_VALUE",
        1293 to  "ER_TOO_MUCH_AUTO_TIMESTAMP_COLS",
        1294 to  "ER_INVALID_ON_UPDATE",
        1295 to  "ER_UNSUPPORTED_PS",
        1296 to  "ER_GET_ERRMSG",
        1297 to  "ER_GET_TEMPORARY_ERRMSG",
        1298 to  "ER_UNKNOWN_TIME_ZONE",
        1299 to  "ER_WARN_INVALID_TIMESTAMP",
        1300 to  "ER_INVALID_CHARACTER_STRING",
        1301 to  "ER_WARN_ALLOWED_PACKET_OVERFLOWED",
        1302 to  "ER_CONFLICTING_DECLARATIONS",
        1303 to  "ER_SP_NO_RECURSIVE_CREATE",
        1304 to  "ER_SP_ALREADY_EXISTS",
        1305 to  "ER_SP_DOES_NOT_EXIST",
        1306 to  "ER_SP_DROP_FAILED",
        1307 to  "ER_SP_STORE_FAILED",
        1308 to  "ER_SP_LILABEL_MISMATCH",
        1309 to  "ER_SP_LABEL_REDEFINE",
        1310 to  "ER_SP_LABEL_MISMATCH",
        1311 to  "ER_SP_UNINIT_VAR",
        1312 to  "ER_SP_BADSELECT",
        1313 to  "ER_SP_BADRETURN",
        1314 to  "ER_SP_BADSTATEMENT",
        1315 to  "ER_UPDATE_LOG_DEPRECATED_IGNORED",
        1316 to  "ER_UPDATE_LOG_DEPRECATED_TRANSLATED",
        1317 to  "ER_QUERY_INTERRUPTED",
        1318 to  "ER_SP_WRONG_NO_OF_ARGS",
        1319 to  "ER_SP_COND_MISMATCH",
        1320 to  "ER_SP_NORETURN",
        1321 to  "ER_SP_NORETURNEND",
        1322 to  "ER_SP_BAD_CURSOR_QUERY",
        1323 to  "ER_SP_BAD_CURSOR_SELECT",
        1324 to  "ER_SP_CURSOR_MISMATCH",
        1325 to  "ER_SP_CURSOR_ALREADY_OPEN",
        1326 to  "ER_SP_CURSOR_NOT_OPEN",
        1327 to  "ER_SP_UNDECLARED_VAR",
        1328 to  "ER_SP_WRONG_NO_OF_FETCH_ARGS",
        1329 to  "ER_SP_FETCH_NO_DATA",
        1330 to  "ER_SP_DUP_PARAM",
        1331 to  "ER_SP_DUP_VAR",
        1332 to  "ER_SP_DUP_COND",
        1333 to  "ER_SP_DUP_CURS",
        1334 to  "ER_SP_CANT_ALTER",
        1335 to  "ER_SP_SUBSELECT_NYI",
        1336 to  "ER_STMT_NOT_ALLOWED_IN_SF_OR_TRG",
        1337 to  "ER_SP_VARCOND_AFTER_CURSHNDLR",
        1338 to  "ER_SP_CURSOR_AFTER_HANDLER",
        1339 to  "ER_SP_CASE_NOT_FOUND",
        1340 to  "ER_FPARSER_TOO_BIG_FILE",
        1341 to  "ER_FPARSER_BAD_HEADER",
        1342 to  "ER_FPARSER_EOF_IN_COMMENT",
        1343 to  "ER_FPARSER_ERROR_IN_PARAMETER",
        1344 to  "ER_FPARSER_EOF_IN_UNKNOWN_PARAMETER",
        1345 to  "ER_VIEW_NO_EXPLAIN",
        1346 to  "ER_FRM_UNKNOWN_TYPE",
        1347 to  "ER_WRONG_OBJECT",
        1348 to  "ER_NONUPDATEABLE_COLUMN",
        1349 to  "ER_VIEW_SELECT_DERIVED",
        1350 to  "ER_VIEW_SELECT_CLAUSE",
        1351 to  "ER_VIEW_SELECT_VARIABLE",
        1352 to  "ER_VIEW_SELECT_TMPTABLE",
        1353 to  "ER_VIEW_WRONG_LIST",
        1354 to  "ER_WARN_VIEW_MERGE",
        1355 to  "ER_WARN_VIEW_WITHOUT_KEY",
        1356 to  "ER_VIEW_INVALID",
        1357 to  "ER_SP_NO_DROP_SP",
        1358 to  "ER_SP_GOTO_IN_HNDLR",
        1359 to  "ER_TRG_ALREADY_EXISTS",
        1360 to  "ER_TRG_DOES_NOT_EXIST",
        1361 to  "ER_TRG_ON_VIEW_OR_TEMP_TABLE",
        1362 to  "ER_TRG_CANT_CHANGE_ROW",
        1363 to  "ER_TRG_NO_SUCH_ROW_IN_TRG",
        1364 to  "ER_NO_DEFAULT_FOR_FIELD",
        1365 to  "ER_DIVISION_BY_ZERO",
        1366 to  "ER_TRUNCATED_WRONG_VALUE_FOR_FIELD",
        1367 to  "ER_ILLEGAL_VALUE_FOR_TYPE",
        1368 to  "ER_VIEW_NONUPD_CHECK",
        1369 to  "ER_VIEW_CHECK_FAILED",
        1370 to  "ER_PROCACCESS_DENIED_ERROR",
        1371 to  "ER_RELAY_LOG_FAIL",
        1372 to  "ER_PASSWD_LENGTH",
        1373 to  "ER_UNKNOWN_TARGET_BINLOG",
        1374 to  "ER_IO_ERR_LOG_INDEX_READ",
        1375 to  "ER_BINLOG_PURGE_PROHIBITED",
        1376 to  "ER_FSEEK_FAIL",
        1377 to  "ER_BINLOG_PURGE_FATAL_ERR",
        1378 to  "ER_LOG_IN_USE",
        1379 to  "ER_LOG_PURGE_UNKNOWN_ERR",
        1380 to  "ER_RELAY_LOG_INIT",
        1381 to  "ER_NO_BINARY_LOGGING",
        1382 to  "ER_RESERVED_SYNTAX",
        1383 to  "ER_WSAS_FAILED",
        1384 to  "ER_DIFF_GROUPS_PROC",
        1385 to  "ER_NO_GROUP_FOR_PROC",
        1386 to  "ER_ORDER_WITH_PROC",
        1387 to  "ER_LOGGING_PROHIBIT_CHANGING_OF",
        1388 to  "ER_NO_FILE_MAPPING",
        1389 to  "ER_WRONG_MAGIC",
        1390 to  "ER_PS_MANY_PARAM",
        1391 to  "ER_KEY_PART_0",
        1392 to  "ER_VIEW_CHECKSUM",
        1393 to  "ER_VIEW_MULTIUPDATE",
        1394 to  "ER_VIEW_NO_INSERT_FIELD_LIST",
        1395 to  "ER_VIEW_DELETE_MERGE_VIEW",
        1396 to  "ER_CANNOT_USER",
        1397 to  "ER_XAER_NOTA",
        1398 to  "ER_XAER_INVAL",
        1399 to  "ER_XAER_RMFAIL",
        1400 to  "ER_XAER_OUTSIDE",
        1401 to  "ER_XAER_RMERR",
        1402 to  "ER_XA_RBROLLBACK",
        1403 to  "ER_NONEXISTING_PROC_GRANT",
        1404 to  "ER_PROC_AUTO_GRANT_FAIL",
        1405 to  "ER_PROC_AUTO_REVOKE_FAIL",
        1406 to  "ER_DATA_TOO_LONG",
        1407 to  "ER_SP_BAD_SQLSTATE",
        1408 to  "ER_STARTUP",
        1409 to  "ER_LOAD_FROM_FIXED_SIZE_ROWS_TO_VAR",
        1410 to  "ER_CANT_CREATE_USER_WITH_GRANT",
        1411 to  "ER_WRONG_VALUE_FOR_TYPE",
        1412 to  "ER_TABLE_DEF_CHANGED",
        1413 to  "ER_SP_DUP_HANDLER",
        1414 to  "ER_SP_NOT_VAR_ARG",
        1415 to  "ER_SP_NO_RETSET",
        1416 to  "ER_CANT_CREATE_GEOMETRY_OBJECT",
        1417 to  "ER_FAILED_ROUTINE_BREAK_BINLOG",
        1418 to  "ER_BINLOG_UNSAFE_ROUTINE",
        1419 to  "ER_BINLOG_CREATE_ROUTINE_NEED_SUPER",
        1420 to  "ER_EXEC_STMT_WITH_OPEN_CURSOR",
        1421 to  "ER_STMT_HAS_NO_OPEN_CURSOR",
        1422 to  "ER_COMMIT_NOT_ALLOWED_IN_SF_OR_TRG",
        1423 to  "ER_NO_DEFAULT_FOR_VIEW_FIELD",
        1424 to  "ER_SP_NO_RECURSION",
        1425 to  "ER_TOO_BIG_SCALE",
        1426 to  "ER_TOO_BIG_PRECISION",
        1427 to  "ER_M_BIGGER_THAN_D",
        1428 to  "ER_WRONG_LOCK_OF_SYSTEM_TABLE",
        1429 to  "ER_CONNECT_TO_FOREIGN_DATA_SOURCE",
        1430 to  "ER_QUERY_ON_FOREIGN_DATA_SOURCE",
        1431 to  "ER_FOREIGN_DATA_SOURCE_DOESNT_EXIST",
        1432 to  "ER_FOREIGN_DATA_STRING_INVALID_CANT_CREATE",
        1433 to  "ER_FOREIGN_DATA_STRING_INVALID",
        1434 to  "ER_CANT_CREATE_FEDERATED_TABLE",
        1435 to  "ER_TRG_IN_WRONG_SCHEMA",
        1436 to  "ER_STACK_OVERRUN_NEED_MORE",
        1437 to  "ER_TOO_LONG_BODY",
        1438 to  "ER_WARN_CANT_DROP_DEFAULT_KEYCACHE",
        1439 to  "ER_TOO_BIG_DISPLAYWIDTH",
        1440 to  "ER_XAER_DUPID",
        1441 to  "ER_DATETIME_FUNCTION_OVERFLOW",
        1442 to  "ER_CANT_UPDATE_USED_TABLE_IN_SF_OR_TRG",
        1443 to  "ER_VIEW_PREVENT_UPDATE",
        1444 to  "ER_PS_NO_RECURSION",
        1445 to  "ER_SP_CANT_SET_AUTOCOMMIT",
        1446 to  "ER_MALFORMED_DEFINER",
        1447 to  "ER_VIEW_FRM_NO_USER",
        1448 to  "ER_VIEW_OTHER_USER",
        1449 to  "ER_NO_SUCH_USER",
        1450 to  "ER_FORBID_SCHEMA_CHANGE",
        1451 to  "ER_ROW_IS_REFERENCED_2",
        1452 to  "ER_NO_REFERENCED_ROW_2",
        1453 to  "ER_SP_BAD_VAR_SHADOW",
        1454 to  "ER_TRG_NO_DEFINER",
        1455 to  "ER_OLD_FILE_FORMAT",
        1456 to  "ER_SP_RECURSION_LIMIT",
        1457 to  "ER_SP_PROC_TABLE_CORRUPT",
        1458 to  "ER_SP_WRONG_NAME",
        1459 to  "ER_TABLE_NEEDS_UPGRADE",
        1460 to  "ER_SP_NO_AGGREGATE",
        1461 to  "ER_MAX_PREPARED_STMT_COUNT_REACHED",
        1462 to  "ER_VIEW_RECURSIVE",
        1463 to  "ER_NON_GROUPING_FIELD_USED",
        1464 to  "ER_TABLE_CANT_HANDLE_SPKEYS",
        1465 to  "ER_NO_TRIGGERS_ON_SYSTEM_SCHEMA",
        1466 to  "ER_REMOVED_SPACES",
        1467 to  "ER_AUTOINC_READ_FAILED",
        1468 to  "ER_USERNAME",
        1469 to  "ER_HOSTNAME",
        1470 to  "ER_WRONG_STRING_LENGTH",
        1471 to  "ER_NON_INSERTABLE_TABLE",
        1472 to  "ER_ADMIN_WRONG_MRG_TABLE",
        1473 to  "ER_TOO_HIGH_LEVEL_OF_NESTING_FOR_SELECT",
        1474 to  "ER_NAME_BECOMES_EMPTY",
        1475 to  "ER_AMBIGUOUS_FIELD_TERM",
        1476 to  "ER_FOREIGN_SERVER_EXISTS",
        1477 to  "ER_FOREIGN_SERVER_DOESNT_EXIST",
        1478 to  "ER_ILLEGAL_HA_CREATE_OPTION",
        1479 to  "ER_PARTITION_REQUIRES_VALUES_ERROR",
        1480 to  "ER_PARTITION_WRONG_VALUES_ERROR",
        1481 to  "ER_PARTITION_MAXVALUE_ERROR",
        1482 to  "ER_PARTITION_SUBPARTITION_ERROR",
        1483 to  "ER_PARTITION_SUBPART_MIX_ERROR",
        1484 to  "ER_PARTITION_WRONG_NO_PART_ERROR",
        1485 to  "ER_PARTITION_WRONG_NO_SUBPART_ERROR",
        1486 to  "ER_WRONG_EXPR_IN_PARTITION_FUNC_ERROR",
        1487 to  "ER_NO_CONST_EXPR_IN_RANGE_OR_LIST_ERROR",
        1488 to  "ER_FIELD_NOT_FOUND_PART_ERROR",
        1489 to  "ER_LIST_OF_FIELDS_ONLY_IN_HASH_ERROR",
        1490 to  "ER_INCONSISTENT_PARTITION_INFO_ERROR",
        1491 to  "ER_PARTITION_FUNC_NOT_ALLOWED_ERROR",
        1492 to  "ER_PARTITIONS_MUST_BE_DEFINED_ERROR",
        1493 to  "ER_RANGE_NOT_INCREASING_ERROR",
        1494 to  "ER_INCONSISTENT_TYPE_OF_FUNCTIONS_ERROR",
        1495 to  "ER_MULTIPLE_DEF_CONST_IN_LIST_PART_ERROR",
        1496 to  "ER_PARTITION_ENTRY_ERROR",
        1497 to  "ER_MIX_HANDLER_ERROR",
        1498 to  "ER_PARTITION_NOT_DEFINED_ERROR",
        1499 to  "ER_TOO_MANY_PARTITIONS_ERROR",
        1500 to  "ER_SUBPARTITION_ERROR",
        1501 to  "ER_CANT_CREATE_HANDLER_FILE",
        1502 to  "ER_BLOB_FIELD_IN_PART_FUNC_ERROR",
        1503 to  "ER_UNIQUE_KEY_NEED_ALL_FIELDS_IN_PF",
        1504 to  "ER_NO_PARTS_ERROR",
        1505 to  "ER_PARTITION_MGMT_ON_NONPARTITIONED",
        1506 to  "ER_FOREIGN_KEY_ON_PARTITIONED",
        1507 to  "ER_DROP_PARTITION_NON_EXISTENT",
        1508 to  "ER_DROP_LAST_PARTITION",
        1509 to  "ER_COALESCE_ONLY_ON_HASH_PARTITION",
        1510 to  "ER_REORG_HASH_ONLY_ON_SAME_NO",
        1511 to  "ER_REORG_NO_PARAM_ERROR",
        1512 to  "ER_ONLY_ON_RANGE_LIST_PARTITION",
        1513 to  "ER_ADD_PARTITION_SUBPART_ERROR",
        1514 to  "ER_ADD_PARTITION_NO_NEW_PARTITION",
        1515 to  "ER_COALESCE_PARTITION_NO_PARTITION",
        1516 to  "ER_REORG_PARTITION_NOT_EXIST",
        1517 to  "ER_SAME_NAME_PARTITION",
        1518 to  "ER_NO_BINLOG_ERROR",
        1519 to  "ER_CONSECUTIVE_REORG_PARTITIONS",
        1520 to  "ER_REORG_OUTSIDE_RANGE",
        1521 to  "ER_PARTITION_FUNCTION_FAILURE",
        1522 to  "ER_PART_STATE_ERROR",
        1523 to  "ER_LIMITED_PART_RANGE",
        1524 to  "ER_PLUGIN_IS_NOT_LOADED",
        1525 to  "ER_WRONG_VALUE",
        1526 to  "ER_NO_PARTITION_FOR_GIVEN_VALUE",
        1527 to  "ER_FILEGROUP_OPTION_ONLY_ONCE",
        1528 to  "ER_CREATE_FILEGROUP_FAILED",
        1529 to  "ER_DROP_FILEGROUP_FAILED",
        1530 to  "ER_TABLESPACE_AUTO_EXTEND_ERROR",
        1531 to  "ER_WRONG_SIZE_NUMBER",
        1532 to  "ER_SIZE_OVERFLOW_ERROR",
        1533 to  "ER_ALTER_FILEGROUP_FAILED",
        1534 to  "ER_BINLOG_ROW_LOGGING_FAILED",
        1535 to  "ER_BINLOG_ROW_WRONG_TABLE_DEF",
        1536 to  "ER_BINLOG_ROW_RBR_TO_SBR",
        1537 to  "ER_EVENT_ALREADY_EXISTS",
        1538 to  "ER_EVENT_STORE_FAILED",
        1539 to  "ER_EVENT_DOES_NOT_EXIST",
        1540 to  "ER_EVENT_CANT_ALTER",
        1541 to  "ER_EVENT_DROP_FAILED",
        1542 to  "ER_EVENT_INTERVAL_NOT_POSITIVE_OR_TOO_BIG",
        1543 to  "ER_EVENT_ENDS_BEFORE_STARTS",
        1544 to  "ER_EVENT_EXEC_TIME_IN_THE_PAST",
        1545 to  "ER_EVENT_OPEN_TABLE_FAILED",
        1546 to  "ER_EVENT_NEITHER_M_EXPR_NOR_M_AT",
        1547 to  "ER_COL_COUNT_DOESNT_MATCH_CORRUPTED",
        1548 to  "ER_CANNOT_LOAD_FROM_TABLE",
        1549 to  "ER_EVENT_CANNOT_DELETE",
        1550 to  "ER_EVENT_COMPILE_ERROR",
        1551 to  "ER_EVENT_SAME_NAME",
        1552 to  "ER_EVENT_DATA_TOO_LONG",
        1553 to  "ER_DROP_INDEX_FK",
        1554 to  "ER_WARN_DEPRECATED_SYNTAX_WITH_VER",
        1555 to  "ER_CANT_WRITE_LOCK_LOG_TABLE",
        1556 to  "ER_CANT_LOCK_LOG_TABLE",
        1557 to  "ER_FOREIGN_DUPLICATE_KEY",
        1558 to  "ER_COL_COUNT_DOESNT_MATCH_PLEASE_UPDATE",
        1559 to  "ER_TEMP_TABLE_PREVENTS_SWITCH_OUT_OF_RBR",
        1560 to  "ER_STORED_FUNCTION_PREVENTS_SWITCH_BINLOG_FORMAT",
        1561 to  "ER_NDB_CANT_SWITCH_BINLOG_FORMAT",
        1562 to  "ER_PARTITION_NO_TEMPORARY",
        1563 to  "ER_PARTITION_CONST_DOMAIN_ERROR",
        1564 to  "ER_PARTITION_FUNCTION_IS_NOT_ALLOWED",
        1565 to  "ER_DDL_LOG_ERROR",
        1566 to  "ER_NULL_IN_VALUES_LESS_THAN",
        1567 to  "ER_WRONG_PARTITION_NAME",
        1568 to  "ER_CANT_CHANGE_TX_CHARACTERISTICS",
        1569 to  "ER_DUP_ENTRY_AUTOINCREMENT_CASE",
        1570 to  "ER_EVENT_MODIFY_QUEUE_ERROR",
        1571 to  "ER_EVENT_SET_VAR_ERROR",
        1572 to  "ER_PARTITION_MERGE_ERROR",
        1573 to  "ER_CANT_ACTIVATE_LOG",
        1574 to  "ER_RBR_NOT_AVAILABLE",
        1575 to  "ER_BASE64_DECODE_ERROR",
        1576 to  "ER_EVENT_RECURSION_FORBIDDEN",
        1577 to  "ER_EVENTS_DB_ERROR",
        1578 to  "ER_ONLY_INTEGERS_ALLOWED",
        1579 to  "ER_UNSUPORTED_LOG_ENGINE",
        1580 to  "ER_BAD_LOG_STATEMENT",
        1581 to  "ER_CANT_RENAME_LOG_TABLE",
        1582 to  "ER_WRONG_PARAMCOUNT_TO_NATIVE_FCT",
        1583 to  "ER_WRONG_PARAMETERS_TO_NATIVE_FCT",
        1584 to  "ER_WRONG_PARAMETERS_TO_STORED_FCT",
        1585 to  "ER_NATIVE_FCT_NAME_COLLISION",
        1586 to  "ER_DUP_ENTRY_WITH_KEY_NAME",
        1587 to  "ER_BINLOG_PURGE_EMFILE",
        1588 to  "ER_EVENT_CANNOT_CREATE_IN_THE_PAST",
        1589 to  "ER_EVENT_CANNOT_ALTER_IN_THE_PAST",
        1590 to  "ER_SLAVE_INCIDENT",
        1591 to  "ER_NO_PARTITION_FOR_GIVEN_VALUE_SILENT",
        1592 to  "ER_BINLOG_UNSAFE_STATEMENT",
        1593 to  "ER_SLAVE_FATAL_ERROR",
        1594 to  "ER_SLAVE_RELAY_LOG_READ_FAILURE",
        1595 to  "ER_SLAVE_RELAY_LOG_WRITE_FAILURE",
        1596 to  "ER_SLAVE_CREATE_EVENT_FAILURE",
        1597 to  "ER_SLAVE_MASTER_COM_FAILURE",
        1598 to  "ER_BINLOG_LOGGING_IMPOSSIBLE",
        1599 to  "ER_VIEW_NO_CREATION_CTX",
        1600 to  "ER_VIEW_INVALID_CREATION_CTX",
        1601 to  "ER_SR_INVALID_CREATION_CTX",
        1602 to  "ER_TRG_CORRUPTED_FILE",
        1603 to  "ER_TRG_NO_CREATION_CTX",
        1604 to  "ER_TRG_INVALID_CREATION_CTX",
        1605 to  "ER_EVENT_INVALID_CREATION_CTX",
        1606 to  "ER_TRG_CANT_OPEN_TABLE",
        1607 to  "ER_CANT_CREATE_SROUTINE",
        1608 to  "ER_NEVER_USED",
        1609 to  "ER_NO_FORMAT_DESCRIPTION_EVENT_BEFORE_BINLOG_STATEMENT",
        1610 to  "ER_SLAVE_CORRUPT_EVENT",
        1611 to  "ER_LOAD_DATA_INVALID_COLUMN",
        1612 to  "ER_LOG_PURGE_NO_FILE",
        1613 to  "ER_XA_RBTIMEOUT",
        1614 to  "ER_XA_RBDEADLOCK",
        1615 to  "ER_NEED_REPREPARE",
        1616 to  "ER_DELAYED_NOT_SUPPORTED",
        1617 to  "WARN_NO_MASTER_INFO",
        1618 to  "WARN_OPTION_IGNORED",
        1619 to  "WARN_PLUGIN_DELETE_BUILTIN",
        1620 to  "WARN_PLUGIN_BUSY",
        1621 to  "ER_VARIABLE_IS_READONLY",
        1622 to  "ER_WARN_ENGINE_TRANSACTION_ROLLBACK",
        1623 to  "ER_SLAVE_HEARTBEAT_FAILURE",
        1624 to  "ER_SLAVE_HEARTBEAT_VALUE_OUT_OF_RANGE",
        1625 to  "ER_NDB_REPLICATION_SCHEMA_ERROR",
        1626 to  "ER_CONFLICT_FN_PARSE_ERROR",
        1627 to  "ER_EXCEPTIONS_WRITE_ERROR",
        1628 to  "ER_TOO_LONG_TABLE_COMMENT",
        1629 to  "ER_TOO_LONG_FIELD_COMMENT",
        1630 to  "ER_FUNC_INEXISTENT_NAME_COLLISION",
        1631 to  "ER_DATABASE_NAME",
        1632 to  "ER_TABLE_NAME",
        1633 to  "ER_PARTITION_NAME",
        1634 to  "ER_SUBPARTITION_NAME",
        1635 to  "ER_TEMPORARY_NAME",
        1636 to  "ER_RENAMED_NAME",
        1637 to  "ER_TOO_MANY_CONCURRENT_TRXS",
        1638 to  "WARN_NON_ASCII_SEPARATOR_NOT_IMPLEMENTED",
        1639 to  "ER_DEBUG_SYNC_TIMEOUT",
        1640 to  "ER_DEBUG_SYNC_HIT_LIMIT",
        1641 to  "ER_DUP_SIGNAL_SET",
        1642 to  "ER_SIGNAL_WARN",
        1643 to  "ER_SIGNAL_NOT_FOUND",
        1644 to  "ER_SIGNAL_EXCEPTION",
        1645 to  "ER_RESIGNAL_WITHOUT_ACTIVE_HANDLER",
        1646 to  "ER_SIGNAL_BAD_CONDITION_TYPE",
        1647 to  "WARN_COND_ITEM_TRUNCATED",
        1648 to  "ER_COND_ITEM_TOO_LONG",
        1649 to  "ER_UNKNOWN_LOCALE",
        1650 to  "ER_SLAVE_IGNORE_SERVER_IDS",
        1651 to  "ER_QUERY_CACHE_DISABLED",
        1652 to  "ER_SAME_NAME_PARTITION_FIELD",
        1653 to  "ER_PARTITION_COLUMN_LIST_ERROR",
        1654 to  "ER_WRONG_TYPE_COLUMN_VALUE_ERROR",
        1655 to  "ER_TOO_MANY_PARTITION_FUNC_FIELDS_ERROR",
        1656 to  "ER_MAXVALUE_IN_VALUES_IN",
        1657 to  "ER_TOO_MANY_VALUES_ERROR",
        1658 to  "ER_ROW_SINGLE_PARTITION_FIELD_ERROR",
        1659 to  "ER_FIELD_TYPE_NOT_ALLOWED_AS_PARTITION_FIELD",
        1660 to  "ER_PARTITION_FIELDS_TOO_LONG",
        1661 to  "ER_BINLOG_ROW_ENGINE_AND_STMT_ENGINE",
        1662 to  "ER_BINLOG_ROW_MODE_AND_STMT_ENGINE",
        1663 to  "ER_BINLOG_UNSAFE_AND_STMT_ENGINE",
        1664 to  "ER_BINLOG_ROW_INJECTION_AND_STMT_ENGINE",
        1665 to  "ER_BINLOG_STMT_MODE_AND_ROW_ENGINE",
        1666 to  "ER_BINLOG_ROW_INJECTION_AND_STMT_MODE",
        1667 to  "ER_BINLOG_MULTIPLE_ENGINES_AND_SELF_LOGGING_ENGINE",
        1668 to  "ER_BINLOG_UNSAFE_LIMIT",
        1669 to  "ER_BINLOG_UNSAFE_INSERT_DELAYED",
        1670 to  "ER_BINLOG_UNSAFE_SYSTEM_TABLE",
        1671 to  "ER_BINLOG_UNSAFE_AUTOINC_COLUMNS",
        1672 to  "ER_BINLOG_UNSAFE_UDF",
        1673 to  "ER_BINLOG_UNSAFE_SYSTEM_VARIABLE",
        1674 to  "ER_BINLOG_UNSAFE_SYSTEM_FUNCTION",
        1675 to  "ER_BINLOG_UNSAFE_NONTRANS_AFTER_TRANS",
        1676 to  "ER_MESSAGE_AND_STATEMENT",
        1677 to  "ER_SLAVE_CONVERSION_FAILED",
        1678 to  "ER_SLAVE_CANT_CREATE_CONVERSION",
        1679 to  "ER_INSIDE_TRANSACTION_PREVENTS_SWITCH_BINLOG_FORMAT",
        1680 to  "ER_PATH_LENGTH",
        1681 to  "ER_WARN_DEPRECATED_SYNTAX_NO_REPLACEMENT",
        1682 to  "ER_WRONG_NATIVE_TABLE_STRUCTURE",
        1683 to  "ER_WRONG_PERFSCHEMA_USAGE",
        1684 to  "ER_WARN_I_S_SKIPPED_TABLE",
        1685 to  "ER_INSIDE_TRANSACTION_PREVENTS_SWITCH_BINLOG_DIRECT",
        1686 to  "ER_STORED_FUNCTION_PREVENTS_SWITCH_BINLOG_DIRECT",
        1687 to  "ER_SPATIAL_MUST_HAVE_GEOM_COL",
        1688 to  "ER_TOO_LONG_INDEX_COMMENT",
        1689 to  "ER_LOCK_ABORTED",
        1690 to  "ER_DATA_OUT_OF_RANGE",
        1691 to  "ER_WRONG_SPVAR_TYPE_IN_LIMIT",
        1692 to  "ER_BINLOG_UNSAFE_MULTIPLE_ENGINES_AND_SELF_LOGGING_ENGINE",
        1693 to  "ER_BINLOG_UNSAFE_MIXED_STATEMENT",
        1694 to  "ER_INSIDE_TRANSACTION_PREVENTS_SWITCH_SQL_LOG_BIN",
        1695 to  "ER_STORED_FUNCTION_PREVENTS_SWITCH_SQL_LOG_BIN",
        1696 to  "ER_FAILED_READ_FROM_PAR_FILE",
        1697 to  "ER_VALUES_IS_NOT_INT_TYPE_ERROR",
        1698 to  "ER_ACCESS_DENIED_NO_PASSWORD_ERROR",
        1699 to  "ER_SET_PASSWORD_AUTH_PLUGIN",
        1700 to  "ER_GRANT_PLUGIN_USER_EXISTS",
        1701 to  "ER_TRUNCATE_ILLEGAL_FK",
        1702 to  "ER_PLUGIN_IS_PERMANENT",
        1703 to  "ER_SLAVE_HEARTBEAT_VALUE_OUT_OF_RANGE_MIN",
        1704 to  "ER_SLAVE_HEARTBEAT_VALUE_OUT_OF_RANGE_MAX",
        1705 to  "ER_STMT_CACHE_FULL",
        1706 to  "ER_MULTI_UPDATE_KEY_CONFLICT",
        1707 to  "ER_TABLE_NEEDS_REBUILD",
        1708 to  "WARN_OPTION_BELOW_LIMIT",
        1709 to  "ER_INDEX_COLUMN_TOO_LONG",
        1710 to  "ER_ERROR_IN_TRIGGER_BODY",
        1711 to  "ER_ERROR_IN_UNKNOWN_TRIGGER_BODY",
        1712 to  "ER_INDEX_CORRUPT",
        1713 to  "ER_UNDO_RECORD_TOO_BIG",
        1714 to  "ER_BINLOG_UNSAFE_INSERT_IGNORE_SELECT",
        1715 to  "ER_BINLOG_UNSAFE_INSERT_SELECT_UPDATE",
        1716 to  "ER_BINLOG_UNSAFE_REPLACE_SELECT",
        1717 to  "ER_BINLOG_UNSAFE_CREATE_IGNORE_SELECT",
        1718 to  "ER_BINLOG_UNSAFE_CREATE_REPLACE_SELECT",
        1719 to  "ER_BINLOG_UNSAFE_UPDATE_IGNORE",
        1720 to  "ER_PLUGIN_NO_UNINSTALL",
        1721 to  "ER_PLUGIN_NO_INSTALL",
        1722 to  "ER_BINLOG_UNSAFE_WRITE_AUTOINC_SELECT",
        1723 to  "ER_BINLOG_UNSAFE_CREATE_SELECT_AUTOINC",
        1724 to  "ER_BINLOG_UNSAFE_INSERT_TWO_KEYS",
        1725 to  "ER_TABLE_IN_FK_CHECK",
        1726 to  "ER_UNSUPPORTED_ENGINE",
        1727 to  "ER_BINLOG_UNSAFE_AUTOINC_NOT_FIRST",
        1728 to  "ER_CANNOT_LOAD_FROM_TABLE_V2",
        1729 to  "ER_MASTER_DELAY_VALUE_OUT_OF_RANGE",
        1730 to  "ER_ONLY_FD_AND_RBR_EVENTS_ALLOWED_IN_BINLOG_STATEMENT",
        1731 to  "ER_PARTITION_EXCHANGE_DIFFERENT_OPTION",
        1732 to  "ER_PARTITION_EXCHANGE_PART_TABLE",
        1733 to  "ER_PARTITION_EXCHANGE_TEMP_TABLE",
        1734 to  "ER_PARTITION_INSTEAD_OF_SUBPARTITION",
        1735 to  "ER_UNKNOWN_PARTITION",
        1736 to  "ER_TABLES_DIFFERENT_METADATA",
        1737 to  "ER_ROW_DOES_NOT_MATCH_PARTITION",
        1738 to  "ER_BINLOG_CACHE_SIZE_GREATER_THAN_MAX",
        1739 to  "ER_WARN_INDEX_NOT_APPLICABLE",
        1740 to  "ER_PARTITION_EXCHANGE_FOREIGN_KEY",
        1741 to  "ER_NO_SUCH_KEY_VALUE",
        1742 to  "ER_RPL_INFO_DATA_TOO_LONG",
        1743 to  "ER_NETWORK_READ_EVENT_CHECKSUM_FAILURE",
        1744 to  "ER_BINLOG_READ_EVENT_CHECKSUM_FAILURE",
        1745 to  "ER_BINLOG_STMT_CACHE_SIZE_GREATER_THAN_MAX",
        1746 to  "ER_CANT_UPDATE_TABLE_IN_CREATE_TABLE_SELECT",
        1747 to  "ER_PARTITION_CLAUSE_ON_NONPARTITIONED",
        1748 to  "ER_ROW_DOES_NOT_MATCH_GIVEN_PARTITION_SET",
        1749 to  "ER_NO_SUCH_PARTITION",
        1750 to  "ER_CHANGE_RPL_INFO_REPOSITORY_FAILURE",
        1751 to  "ER_WARNING_NOT_COMPLETE_ROLLBACK_WITH_CREATED_TEMP_TABLE",
        1752 to  "ER_WARNING_NOT_COMPLETE_ROLLBACK_WITH_DROPPED_TEMP_TABLE",
        1753 to  "ER_MTS_FEATURE_IS_NOT_SUPPORTED",
        1754 to  "ER_MTS_UPDATED_DBS_GREATER_MAX",
        1755 to  "ER_MTS_CANT_PARALLEL",
        1756 to  "ER_MTS_INCONSISTENT_DATA",
        1757 to  "ER_FULLTEXT_NOT_SUPPORTED_WITH_PARTITIONING",
        1758 to  "ER_DA_INVALID_CONDITION_NUMBER",
        1759 to  "ER_INSECURE_PLAIN_TEXT",
        1760 to  "ER_INSECURE_CHANGE_MASTER",
        1761 to  "ER_FOREIGN_DUPLICATE_KEY_WITH_CHILD_INFO",
        1762 to  "ER_FOREIGN_DUPLICATE_KEY_WITHOUT_CHILD_INFO",
        1763 to  "ER_SQLTHREAD_WITH_SECURE_SLAVE",
        1764 to  "ER_TABLE_HAS_NO_FT",
        1765 to  "ER_VARIABLE_NOT_SETTABLE_IN_SF_OR_TRIGGER",
        1766 to  "ER_VARIABLE_NOT_SETTABLE_IN_TRANSACTION",
        1767 to  "ER_GTID_NEXT_IS_NOT_IN_GTID_NEXT_LIST",
        1768 to  "ER_CANT_CHANGE_GTID_NEXT_IN_TRANSACTION_WHEN_GTID_NEXT_LIST_IS_NULL",
        1769 to  "ER_SET_STATEMENT_CANNOT_INVOKE_FUNCTION",
        1770 to  "ER_GTID_NEXT_CANT_BE_AUTOMATIC_IF_GTID_NEXT_LIST_IS_NON_NULL",
        1771 to  "ER_SKIPPING_LOGGED_TRANSACTION",
        1772 to  "ER_MALFORMED_GTID_SET_SPECIFICATION",
        1773 to  "ER_MALFORMED_GTID_SET_ENCODING",
        1774 to  "ER_MALFORMED_GTID_SPECIFICATION",
        1775 to  "ER_GNO_EXHAUSTED",
        1776 to  "ER_BAD_SLAVE_AUTO_POSITION",
        1777 to  "ER_AUTO_POSITION_REQUIRES_GTID_MODE_ON",
        1778 to  "ER_CANT_DO_IMPLICIT_COMMIT_IN_TRX_WHEN_GTID_NEXT_IS_SET",
        1779 to  "ER_GTID_MODE_2_OR_3_REQUIRES_ENFORCE_GTID_CONSISTENCY_ON",
        1780 to  "ER_GTID_MODE_REQUIRES_BINLOG",
        1781 to  "ER_CANT_SET_GTID_NEXT_TO_GTID_WHEN_GTID_MODE_IS_OFF",
        1782 to  "ER_CANT_SET_GTID_NEXT_TO_ANONYMOUS_WHEN_GTID_MODE_IS_ON",
        1783 to  "ER_CANT_SET_GTID_NEXT_LIST_TO_NON_NULL_WHEN_GTID_MODE_IS_OFF",
        1784 to  "ER_FOUND_GTID_EVENT_WHEN_GTID_MODE_IS_OFF",
        1785 to  "ER_GTID_UNSAFE_NON_TRANSACTIONAL_TABLE",
        1786 to  "ER_GTID_UNSAFE_CREATE_SELECT",
        1787 to  "ER_GTID_UNSAFE_CREATE_DROP_TEMPORARY_TABLE_IN_TRANSACTION",
        1788 to  "ER_GTID_MODE_CAN_ONLY_CHANGE_ONE_STEP_AT_A_TIME",
        1789 to  "ER_MASTER_HAS_PURGED_REQUIRED_GTIDS",
        1790 to  "ER_CANT_SET_GTID_NEXT_WHEN_OWNING_GTID",
        1791 to  "ER_UNKNOWN_EXPLAIN_FORMAT",
        1792 to  "ER_CANT_EXECUTE_IN_READ_ONLY_TRANSACTION",
        1793 to  "ER_TOO_LONG_TABLE_PARTITION_COMMENT",
        1794 to  "ER_SLAVE_CONFIGURATION",
        1795 to  "ER_INNODB_FT_LIMIT",
        1796 to  "ER_INNODB_NO_FT_TEMP_TABLE",
        1797 to  "ER_INNODB_FT_WRONG_DOCID_COLUMN",
        1798 to  "ER_INNODB_FT_WRONG_DOCID_INDEX",
        1799 to  "ER_INNODB_ONLINE_LOG_TOO_BIG",
        1800 to  "ER_UNKNOWN_ALTER_ALGORITHM",
        1801 to  "ER_UNKNOWN_ALTER_LOCK",
        1802 to  "ER_MTS_CHANGE_MASTER_CANT_RUN_WITH_GAPS",
        1803 to  "ER_MTS_RECOVERY_FAILURE",
        1804 to  "ER_MTS_RESET_WORKERS",
        1805 to  "ER_COL_COUNT_DOESNT_MATCH_CORRUPTED_V2",
        1806 to  "ER_SLAVE_SILENT_RETRY_TRANSACTION",
        1807 to  "ER_DISCARD_FK_CHECKS_RUNNING",
        1808 to  "ER_TABLE_SCHEMA_MISMATCH",
        1809 to  "ER_TABLE_IN_SYSTEM_TABLESPACE",
        1810 to  "ER_IO_READ_ERROR",
        1811 to  "ER_IO_WRITE_ERROR",
        1812 to  "ER_TABLESPACE_MISSING",
        1813 to  "ER_TABLESPACE_EXISTS",
        1814 to  "ER_TABLESPACE_DISCARDED",
        1815 to  "ER_INTERNAL_ERROR",
        1816 to  "ER_INNODB_IMPORT_ERROR",
        1817 to  "ER_INNODB_INDEX_CORRUPT",
        1818 to  "ER_INVALID_YEAR_COLUMN_LENGTH",
        1819 to  "ER_NOT_VALID_PASSWORD",
        1820 to  "ER_MUST_CHANGE_PASSWORD",
        1821 to  "ER_FK_NO_INDEX_CHILD",
        1822 to  "ER_FK_NO_INDEX_PARENT",
        1823 to  "ER_FK_FAIL_ADD_SYSTEM",
        1824 to  "ER_FK_CANNOT_OPEN_PARENT",
        1825 to  "ER_FK_INCORRECT_OPTION",
        1826 to  "ER_FK_DUP_NAME",
        1827 to  "ER_PASSWORD_FORMAT",
        1828 to  "ER_FK_COLUMN_CANNOT_DROP",
        1829 to  "ER_FK_COLUMN_CANNOT_DROP_CHILD",
        1830 to  "ER_FK_COLUMN_NOT_NULL",
        1831 to  "ER_DUP_INDEX",
        1832 to  "ER_FK_COLUMN_CANNOT_CHANGE",
        1833 to  "ER_FK_COLUMN_CANNOT_CHANGE_CHILD",
        1834 to  "ER_FK_CANNOT_DELETE_PARENT",
        1835 to  "ER_MALFORMED_PACKET",
        1836 to  "ER_READ_ONLY_MODE",
        1837 to  "ER_GTID_NEXT_TYPE_UNDEFINED_GROUP",
        1838 to  "ER_VARIABLE_NOT_SETTABLE_IN_SP",
        1839 to  "ER_CANT_SET_GTID_PURGED_WHEN_GTID_MODE_IS_OFF",
        1840 to  "ER_CANT_SET_GTID_PURGED_WHEN_GTID_EXECUTED_IS_NOT_EMPTY",
        1841 to  "ER_CANT_SET_GTID_PURGED_WHEN_OWNED_GTIDS_IS_NOT_EMPTY",
        1842 to  "ER_GTID_PURGED_WAS_CHANGED",
        1843 to  "ER_GTID_EXECUTED_WAS_CHANGED",
        1844 to  "ER_BINLOG_STMT_MODE_AND_NO_REPL_TABLES",
        1845 to  "ER_ALTER_OPERATION_NOT_SUPPORTED",
        1846 to  "ER_ALTER_OPERATION_NOT_SUPPORTED_REASON",
        1847 to  "ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_COPY",
        1848 to  "ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_PARTITION",
        1849 to  "ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_FK_RENAME",
        1850 to  "ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_COLUMN_TYPE",
        1851 to  "ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_FK_CHECK",
        1852 to  "ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_IGNORE",
        1853 to  "ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_NOPK",
        1854 to  "ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_AUTOINC",
        1855 to  "ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_HIDDEN_FTS",
        1856 to  "ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_CHANGE_FTS",
        1857 to  "ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_FTS",
        1858 to  "ER_SQL_SLAVE_SKIP_COUNTER_NOT_SETTABLE_IN_GTID_MODE",
        1859 to  "ER_DUP_UNKNOWN_IN_INDEX",
        1860 to  "ER_IDENT_CAUSES_TOO_LONG_PATH",
        1861 to  "ER_ALTER_OPERATION_NOT_SUPPORTED_REASON_NOT_NULL",
        1862 to  "ER_MUST_CHANGE_PASSWORD_LOGIN",
        1863 to  "ER_ROW_IN_WRONG_PARTITION",
        1864 to  "ER_MTS_EVENT_BIGGER_PENDING_JOBS_SIZE_MAX",
        1865 to  "ER_INNODB_NO_FT_USES_PARSER",
        1866 to  "ER_BINLOG_LOGICAL_CORRUPTION",
        1867 to  "ER_WARN_PURGE_LOG_IN_USE",
        1868 to  "ER_WARN_PURGE_LOG_IS_ACTIVE",
        1869 to  "ER_AUTO_INCREMENT_CONFLICT",
        1870 to  "WARN_ON_BLOCKHOLE_IN_RBR",
        1871 to  "ER_SLAVE_MI_INIT_REPOSITORY",
        1872 to  "ER_SLAVE_RLI_INIT_REPOSITORY",
        1873 to  "ER_ACCESS_DENIED_CHANGE_USER_ERROR",
        1874 to  "ER_INNODB_READ_ONLY",
        1875 to  "ER_STOP_SLAVE_SQL_THREAD_TIMEOUT",
        1876 to  "ER_STOP_SLAVE_IO_THREAD_TIMEOUT",
        1877 to  "ER_TABLE_CORRUPT",
        1878 to  "ER_TEMP_FILE_WRITE_FAILURE",
        1879 to  "ER_INNODB_FT_AUX_NOT_HEX_ID",
        1880 to  "ER_OLD_TEMPORALS_UPGRADED",
        1881 to  "ER_INNODB_FORCED_RECOVERY",
        1882 to  "ER_AES_INVALID_IV"
    )
}