plugins {
    `version-catalog`
    `maven-publish`
    signing
    id("org.metaborg.convention.maven-publish")
}

group = "org.metaborg"
description = "A Spoofax 3 catalog."

// WORKAROUND: Normally the generateCatalogAsToml task is used to generate the TOML file,
//  but this task cannot be run before the configuration phase. Therefore, there is a chicken-and-egg
//  problem, where this project needs to be configured to run the generateCatalogAsToml task,
//  yet the generateCatalogAsToml task must be run before the projects can be configured.
//  To workaround this, I use an explicit TOML file and bypass the generateCatalogAsToml task
//  to just report the existing TOML file as its artifact.
//  From: https://github.com/gradle/gradle/issues/19288#issuecomment-1399277313
configurations.versionCatalogElements {
    outgoing.artifacts.clear()
    outgoing.artifact(layout.projectDirectory.dir("../gradle/libs.versions.toml"))
}

catalog {
    versionCatalog {
        from(files("../gradle/libs.versions.toml"))
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenCatalog") {
            from(components["versionCatalog"])
            pom {
                name.set("Spoofax 3 Catalog")
            }
        }
    }
}

// signing {
//     sign(publishing.publications["mavenCatalog"])
// }
