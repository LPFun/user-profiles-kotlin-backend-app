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
    //Logger
    implementation(Deps.logger.slf4jApi)
    implementation(Deps.logger.logbackEncoder)
}
