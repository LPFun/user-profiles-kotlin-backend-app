import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val compileKotlin: KotlinCompile by tasks

plugins {
    application
    id("dependencies")
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
    implementation(kotlin("stdlib"))
    // Modules
    implementation(project(Deps.profileModules.profileBeCommon))
    implementation(project(Deps.profileModules.profileTransportKmpModels))
    implementation(project(Deps.profileModules.profileBeTransportKmp))
    implementation(project(Deps.profileModules.profileDomain))
    // Ktor
    implementation(Deps.ktor.serverNetty)
    implementation(Deps.ktor.clientCore)
    implementation(Deps.ktor.serialization)
    // Kotlinx
    implementation(Deps.kotlinx.datetime)
    implementation(Deps.kotlinx.serializationCore)
    implementation(Deps.kotlinx.coroutinesCore)
    // Kodein
    implementation(Deps.kodein.di)
    implementation(Deps.kodein.ktorServerJvm)

    implementation(Deps.logging.logbackClassic)

    testImplementation(Deps.ktor.serverTests)
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")
