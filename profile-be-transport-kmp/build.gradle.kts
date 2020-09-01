plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {
    val coroutinesVersion: String by project

    implementation(project(":profile-be-common"))
    implementation(project(":profile-transport-kmp-models"))

    implementation(kotlin("stdlib"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
