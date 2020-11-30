rootProject.name = "user-profile-mp"

pluginManagement{
    plugins {
        val kotlinVersion: String by settings

        kotlin("jvm") version kotlinVersion apply false
        kotlin("multiplatform") version kotlinVersion apply false
        kotlin("plugin.serialization") version kotlinVersion apply false
    }
}

include("profile-be-transport-kmp")
include("profile-be-common")
include("profile-transport-kmp-models")
include("profile-transport-common")
include("profile-be-app-ktor")
include("profile-domain")
include("profile-be-repo-db")
includeBuild("compositeBuild")
include("profile-be-repo-db-inmemory")
include("profile-be-repo-db-base")
