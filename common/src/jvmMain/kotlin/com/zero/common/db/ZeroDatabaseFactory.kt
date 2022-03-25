package com.zero.common.db

import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import com.zero.db.ZeroDatabase

actual fun createDatabase(): ZeroDatabase? = ZeroDatabase(
    JdbcSqliteDriver("zero.db")
)
