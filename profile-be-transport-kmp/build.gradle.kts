plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":profile-be-common"))
    implementation(project(":profile-transport-kmp-models"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
