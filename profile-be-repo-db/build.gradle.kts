import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions.jvmTarget = "1.8"

plugins {
    kotlin("jvm")
    id("dependencies")
    id("org.flywaydb.flyway") version "5.2.4"
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

flyway {
    url = System.getenv("JDBC_DATABASE_URL")
    user = System.getenv("JDBC_DATABASE_USERNAME")
    password = System.getenv("JDBC_DATABASE_PASSWORD")
    baselineOnMigrate = true
    locations = arrayOf("filesystem:resources/db/migration")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(Deps.kotlinx.coroutinesCore)
    implementation(Deps.kotlinx.datetime)
    // Modules
    implementation(project(Deps.profileModules.profileBeCommon))
    implementation(project(Deps.profileModules.profileDBBase))
    // Database
    implementation(Deps.database.postgreSql)
    implementation(Deps.database.postgreSqlTestContainers)
    implementation(Deps.database.exposedCore)
    implementation(Deps.database.exposedDao)
    implementation(Deps.database.exposedJdbc)
    implementation(Deps.database.hikari)
    implementation(Deps.database.flywayCore)
    // Kodein
    implementation(Deps.kodein.di)
    // Tests
    testImplementation("org.testcontainers:postgresql:1.15.0-rc2")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
