rootProject.name = "user-profile-mp"

include("profile-common")
include("profile-transport-kmp")
include("profile-transport-common")

pluginManagement{
    plugins {
        val kotlinVersion: String by settings

        kotlin("jvm") version kotlinVersion apply false
        kotlin("multiplatform") version kotlinVersion apply false
        kotlin("plugin.serialization") version kotlinVersion apply false
    }
}
include("profile-be-transport-kmp")
