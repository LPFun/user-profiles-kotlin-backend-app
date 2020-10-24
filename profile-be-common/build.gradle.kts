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
    implementation(Deps.kotlinx.coroutinesCore)
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
