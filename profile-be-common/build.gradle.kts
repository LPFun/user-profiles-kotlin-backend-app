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
    val kotlinDatetime: String by project
    val coroutinesVersion: String by project

    implementation(kotlin("stdlib"))
    implementation(Deps.kotlinx.datetime)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
