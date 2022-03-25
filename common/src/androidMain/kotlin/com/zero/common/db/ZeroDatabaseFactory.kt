package com.zero.common.db

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.zero.db.ZeroDatabase

lateinit var appContext: Context

actual fun createDatabase(): ZeroDatabase? = ZeroDatabase(
    AndroidSqliteDriver(ZeroDatabase.Schema, appContext, "zero.db")
)
