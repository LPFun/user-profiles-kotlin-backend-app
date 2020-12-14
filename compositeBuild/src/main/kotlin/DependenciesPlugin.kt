import Deps.Versions.coroutinesVersion
import Deps.Versions.kotlinDatetime
import Deps.Versions.kotlinSerializationVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

class DependenciesPlugin : Plugin<Project> {
    override fun apply(target: Project) {
    }
}

object Deps {
    val versions = Versions
    val kotlinx = Kotlinx
    val profileModules = ProfileModules
    val ktor = Ktor
    val kodein = Kodein
    val logger = Logger
    val database = Database
    val kafka = Kafka

    object Versions {
        const val kotlinDatetime = "0.1.0"
        const val kotlinSerializationVersion = "1.0.0-RC"
        const val coroutinesVersion = "1.3.9"
    }

    object ProfileModules {
        const val profileBeCommon = ":profile-be-common"
        const val profileTransportKmpModels = ":profile-transport-kmp-models"
        const val profileBeTransportKmp = ":profile-be-transport-kmp"
        const val profileDomain = ":profile-domain"
        const val profileDataBase = ":profile-be-repo-db"
        const val profileInMemoryDB = ":profile-be-repo-db-inmemory"
        const val profileDBBase = ":profile-be-repo-db-base"
        const val profileLogger = ":log-lib"
    }

    object Kotlinx {
        const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:$kotlinDatetime"
        const val serializationCore = "org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinSerializationVersion"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    }

    object Ktor {
        private const val ktorVersion = "1.4.0"
        const val serverNetty = "io.ktor:ktor-server-netty:$ktorVersion"
        const val clientCore = "io.ktor:ktor-client-core:$ktorVersion"
        const val serialization = "io.ktor:ktor-serialization:$ktorVersion"
        const val serverTests = "io.ktor:ktor-server-tests:$ktorVersion"
    }

    object Kodein {
        private const val kodeinVersion = "7.1.0"
        const val di = "org.kodein.di:kodein-di:$kodeinVersion"
        const val ktorServerJvm = "org.kodein.di:kodein-di-framework-ktor-server-jvm:$kodeinVersion"
    }

    object Logger {
        private const val logbackVersion = "1.2.1"
        private const val slf4jVersion = "1.7.30"
        private const val logbackEncoderVersion = "6.4"
        const val logbackClassic = "ch.qos.logback:logback-classic:$logbackVersion"
        const val slf4jApi = "org.slf4j:slf4j-api:$slf4jVersion"
        const val logbackEncoder = "net.logstash.logback:logstash-logback-encoder:$logbackEncoderVersion"
        const val logbackKafkaAppender = "com.github.danielwegener:logback-kafka-appender:0.2.0-RC2"
    }

    object Database {
        private const val exposedVersion = "0.28.1"
        const val h2 = "com.h2database:h2:1.4.200"
        const val exposedCore = "org.jetbrains.exposed:exposed-core:$exposedVersion"
        const val exposedDao = "org.jetbrains.exposed:exposed-dao:$exposedVersion"
        const val exposedJdbc = "org.jetbrains.exposed:exposed-jdbc:$exposedVersion"
        const val postgreSql = "org.postgresql:postgresql:42.2.18"
        const val postgreSqlTestContainers = "org.testcontainers:postgresql:1.14.3"
        const val hikari = "com.zaxxer:HikariCP:3.4.5"
        const val flywayCore = "org.flywaydb:flyway-core:7.1.1"
    }

    object Kafka {
        const val kafkaClient = "org.apache.kafka:kafka-clients:2.5.1"
    }
}
