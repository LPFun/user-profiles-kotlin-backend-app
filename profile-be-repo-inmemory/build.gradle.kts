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

    implementation(Deps.profileModules.profileBeCommon)
    implementation(Deps.kotlinx.coroutinesCore)
    implementation(Deps.database.cache2k)

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
