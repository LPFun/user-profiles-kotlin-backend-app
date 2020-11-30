package com.lpfun.backend.profile.db.postgres

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.testcontainers.containers.PostgreSQLContainer

object ProfilePostgresContainer :
    PostgreSQLContainer<ProfilePostgresContainer>("postgres:latest") {

    val container = this

    override fun start() {
        super.start()
        System.setProperty("JDBC_DATABASE_URL", container.jdbcUrl)
        System.setProperty("JDBC_DATABASE_USERNAME", container.username)
        System.setProperty("JDBC_DATABASE_PASSWORD", container.password)

        val config = HikariConfig().apply {
            driverClassName = "org.postgresql.Driver"
            jdbcUrl = container.jdbcUrl
            username = container.username
            password = container.password
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        val dataSource = HikariDataSource(config)
        Flyway.configure().dataSource(dataSource).load().migrate()
        Database.connect(dataSource)
    }

    override fun stop() {
        super.stop()
    }
}