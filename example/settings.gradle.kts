import org.metaborg.convention.MavenPublishConventionExtension

rootProject.name = "example-project"

pluginManagement {
    includeBuild("../convention-plugin")
    includeBuild("../depman")
}

plugins {
    id("org.metaborg.convention.settings")
}

include(":java-example")