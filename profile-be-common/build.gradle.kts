plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {
    val kotlinDatetime: String by project
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:$kotlinDatetime")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
