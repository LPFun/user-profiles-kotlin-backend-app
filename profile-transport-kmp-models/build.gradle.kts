plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("dependencies")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
    jcenter()
}

kotlin {
    js {
        browser()
        nodejs()
    }
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.kotlinx.serializationCore)
                implementation(Deps.kotlinx.datetime)
            }
        }
        val commonTest by getting

        val jvmMain by getting
        val jvmTest by getting

        val jsMain by getting
        val jsTest by getting
    }
}