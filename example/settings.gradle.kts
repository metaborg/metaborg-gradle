import org.metaborg.convention.MavenPublishConventionExtension

rootProject.name = "example-project"

pluginManagement {
    includeBuild("../convention-plugin")
    includeBuild("../depman")
}

plugins {
    id("org.metaborg.convention.settings:0.6.3")
}

include(":java-example")