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
    // Modules
    implementation(project(Deps.profileModules.profileBeCommon))
    implementation(project(Deps.profileModules.profileTransportKmpModels))
    // Kotlinx
    implementation(Deps.kotlinx.datetime)
    implementation(Deps.kotlinx.coroutinesCore)
    // Test
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
