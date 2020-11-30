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
    implementation(kotlin("stdlib-jdk8"))
    implementation(Deps.kotlinx.coroutinesCore)
    implementation(Deps.kotlinx.datetime)
    // Modules
    implementation(project(Deps.profileModules.profileBeCommon))
    // Exposed
    implementation(Deps.database.exposedCore)
    implementation(Deps.database.exposedDao)
    implementation(Deps.database.exposedJdbc)
}
