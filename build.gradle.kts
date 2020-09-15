plugins {
    kotlin("multiplatform") apply false
    kotlin("plugin.serialization") apply false
}

group = "com.lpfun"
version = "0.1"


allprojects {
    repositories {
        mavenCentral()
        jcenter()
        maven(url = "https://kotlin.bintray.com/kotlinx/")
    }
}
