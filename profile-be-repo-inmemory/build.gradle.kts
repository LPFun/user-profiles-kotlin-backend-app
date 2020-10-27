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

    implementation(project(Deps.profileModules.profileBeCommon))
    implementation(Deps.kotlinx.coroutinesCore)
    implementation(Deps.kotlinx.datetime)
    implementation("com.h2database:h2:1.4.200")
    implementation("org.jetbrains.exposed:exposed-core:0.28.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.28.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.28.1")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
