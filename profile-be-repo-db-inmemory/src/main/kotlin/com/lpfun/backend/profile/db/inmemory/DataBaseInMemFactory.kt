package com.lpfun.backend.profile.db.inmemory

import org.jetbrains.exposed.sql.Database

object DataBaseInMemFactory {
    // in memory db live time in seconds
    private const val IN_MEMORY_DB_LIVE_TIME = 600

    fun init(): Database = Database.connect(
        "jdbc:h2:mem:test;DB_CLOSE_DELAY=$IN_MEMORY_DB_LIVE_TIME",
        "org.h2.Driver"
    )
}