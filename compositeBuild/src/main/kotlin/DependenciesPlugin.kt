import Deps.Versions.coroutinesVersion
import Deps.Versions.kotlinDatetime
import Deps.Versions.kotlinSerializationVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

class DependenciesPlugin : Plugin<Project> {
    override fun apply(target: Project) {
    }
}

object Deps {
    val versions = Versions
    val kotlinx = Kotlinx
    val profileModules = ProfileModules
    val ktor = Ktor
    val kodein = Kodein
    val logging = Logging

    object Versions {
        const val kotlinDatetime = "0.1.0"
        const val kotlinSerializationVersion = "1.0.0-RC"
        const val coroutinesVersion = "1.3.9"
    }

    object ProfileModules {
        const val profileBeCommon = ":profile-be-common"
        const val profileTransportKmpModels = ":profile-transport-kmp-models"
        const val profileBeTransportKmp = ":profile-be-transport-kmp"
    }

    object Kotlinx {
        const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:$kotlinDatetime"
        const val serializationCore = "org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinSerializationVersion"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    }

    object Ktor {
        private const val ktorVersion = "1.4.0"
        const val serverNetty = "io.ktor:ktor-server-netty:$ktorVersion"
        const val clientCore = "io.ktor:ktor-client-core:$ktorVersion"
        const val serialization = "io.ktor:ktor-serialization:$ktorVersion"
        const val serverTests = "io.ktor:ktor-server-tests:$ktorVersion"
    }

    object Kodein {
        private const val kodeinVersion = "7.1.0"
        const val di = "org.kodein.di:kodein-di:$kodeinVersion"
        const val ktorServerJvm = "org.kodein.di:kodein-di-framework-ktor-server-jvm:$kodeinVersion"
    }

    object Logging {
        private const val logbackVersion = "1.2.1"
        const val logbackClassic = "ch.qos.logback:logback-classic:$logbackVersion"
    }

}
