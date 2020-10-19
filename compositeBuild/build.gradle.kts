plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    jcenter()
    google()
    gradlePluginPortal()
}

gradlePlugin {
    plugins.register("dependencies-plugin") {
        id = "dependencies"
        implementationClass = "DependenciesPlugin"
    }
}
