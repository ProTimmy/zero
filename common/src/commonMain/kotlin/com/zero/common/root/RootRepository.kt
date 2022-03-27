package com.zero.common.root

import com.zero.common.db.createDatabase
import com.zero.db.Screens

class RootRepository {

    private val zeroDb = createDatabase()
    private val zeroDbQueries = zeroDb?.screenQueries

    fun insertScreen(id: String, componentModels: String) {
        zeroDbQueries?.insertNewScreen(id, componentModels)
    }

    fun selectScreenById(id: String): Screens? =
        zeroDbQueries?.selectById(id)?.executeAsOneOrNull()
}
