import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val compileKotlin: KotlinCompile by tasks

plugins {
    kotlin("jvm")
    id("dependencies")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

compileKotlin.kotlinOptions.jvmTarget = "1.8"

dependencies {
    implementation(kotlin("stdlib"))
    implementation(Deps.kodein.di)
    implementation(Deps.kotlinx.datetime)
    implementation(Deps.kotlinx.coroutinesCore)
    implementation(project(Deps.profileModules.profileBeCommon))
    testImplementation(kotlin("test-junit"))
}
