rootProject.name = "devenv-plugin"

dependencyResolutionManagement {
    repositories {
        maven("https://artifacts.metaborg.org/content/groups/public/")
        mavenCentral()
    }
}

pluginManagement {
    repositories {
        maven("https://artifacts.metaborg.org/content/groups/public/")
        gradlePluginPortal()
    }

    includeBuild("../convention-plugin")
    includeBuild("../depman")
}

plugins {
    id("org.metaborg.convention.settings")
}