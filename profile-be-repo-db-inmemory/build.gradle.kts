import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "1.8"

plugins {
    kotlin("jvm")
    id("dependencies")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(Deps.kotlinx.datetime)
    // Modules
    implementation(project(Deps.profileModules.profileBeCommon))
    implementation(project(Deps.profileModules.profileDBBase))
    // Data base
    implementation(Deps.database.h2)
    implementation(Deps.database.exposedCore)
    implementation(Deps.database.exposedDao)
    implementation(Deps.database.exposedJdbc)
    // Kodein
    implementation(Deps.kodein.di)
    // Test
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
