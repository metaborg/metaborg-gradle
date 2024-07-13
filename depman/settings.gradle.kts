rootProject.name = "spoofax3-depman"

// This allows us to use plugins from Metaborg Artifacts
pluginManagement {
    repositories {
        maven("https://artifacts.metaborg.org/content/groups/public/")
    }
}

include(":catalog")
include(":platform")
include(":example")

// The `org.metaborg.convention.settings` plugin would apply the FooJay plugin,
//  but to avoid a bootstrapping issue, we'll do the same manually here:

// This downloads an appropriate JVM if not already available
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
