rootProject.name = "metaborg-gradle"

dependencyResolutionManagement {
    repositories {
        maven("https://artifacts.metaborg.org/content/groups/public/")
    }
    versionCatalogs {
        create("libs") {
            from("org.metaborg.spoofax3:catalog:0.1.0")
        }
    }
}

pluginManagement {
    repositories {
        maven("https://artifacts.metaborg.org/content/groups/public/")
    }
}

include(":convention-plugin")
