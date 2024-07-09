rootProject.name = "convention-plugin-example"

pluginManagement {
    includeBuild("../convention-plugin")
}

plugins {
    id("org.metaborg.convention.settings")
}