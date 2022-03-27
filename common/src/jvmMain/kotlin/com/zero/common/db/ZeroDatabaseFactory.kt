package com.zero.common.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import com.zero.db.ZeroDatabase

actual fun createDatabase(): ZeroDatabase? {
    val driver: SqlDriver = JdbcSqliteDriver (JdbcSqliteDriver.IN_MEMORY)
    ZeroDatabase.Schema.create(driver)
    return ZeroDatabase(driver)
}
