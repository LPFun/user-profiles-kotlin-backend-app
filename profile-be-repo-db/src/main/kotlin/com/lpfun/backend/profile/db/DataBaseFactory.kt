package com.lpfun.backend.profile.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

object DataBaseFactory {

    fun init(): Database {
        val db = Database.connect(hikari())
        val flyway = Flyway.configure().dataSource(hikari()).load()
        flyway.migrate()
        return db
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig().apply {
            driverClassName = "org.postgresql.Driver"
            jdbcUrl = System.getenv("JDBC_DATABASE_URL")
            username = System.getenv("JDBC_DATABASE_USERNAME")
            password = System.getenv("JDBC_DATABASE_PASSWORD")
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        return HikariDataSource(config)
    }
}