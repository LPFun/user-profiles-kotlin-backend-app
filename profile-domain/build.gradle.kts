import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions.jvmTarget = "1.8"

dependencies {
    val coroutinesVersion: String by project
    val kotlinDatetime: String by project
    val kodeinVersion: String by project

    implementation("org.kodein.di:kodein-di:$kodeinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:$kotlinDatetime")
    implementation(project(":profile-be-common"))
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test-junit"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
}
