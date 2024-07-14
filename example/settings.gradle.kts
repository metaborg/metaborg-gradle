import org.metaborg.convention.MavenPublishConventionExtension

rootProject.name = "example-project"

pluginManagement {
    includeBuild("../convention-plugin")
    includeBuild("../depman")
}

plugins {
    id("org.metaborg.convention.settings") version "0.6.3"
}

include(":java-example")