import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val compileKotlin: KotlinCompile by tasks

plugins {
    kotlin("jvm")
    id("dependencies")
}

compileKotlin.kotlinOptions.jvmTarget = "1.8"

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    //Logger
    implementation(Deps.logger.slf4jApi)
    implementation(Deps.logger.logbackEncoder)
    //Kodein
    implementation(Deps.kodein.di)
}
