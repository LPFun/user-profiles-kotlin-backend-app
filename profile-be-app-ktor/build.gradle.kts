import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val serializationVersion: String by project
val coroutinesVersion: String by project
val compileKotlin: KotlinCompile by tasks
val kotlinDatetime: String by project

plugins {
    application
    kotlin("jvm")
    kotlin("plugin.serialization")
}

group = rootProject.group
version = rootProject.version

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}

compileKotlin.kotlinOptions.jvmTarget = "1.8"

dependencies {
    implementation(project(":profile-be-common"))
    implementation(project(":profile-transport-kmp-models"))
    implementation(project(":profile-be-transport-kmp"))
    implementation(kotlin("stdlib", kotlinVersion))
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:$kotlinDatetime")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.kodein.di:kodein-di-generic-jvm:6.5.5")

    implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:6.5.5")

    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")
