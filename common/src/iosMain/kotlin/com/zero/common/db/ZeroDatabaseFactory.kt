package com.zero.common.db

import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.zero.db.ZeroDatabase

actual fun createDatabase(): ZeroDatabase? = ZeroDatabase(
    NativeSqliteDriver(ZeroDatabase.Schema, "zero.db")
)
