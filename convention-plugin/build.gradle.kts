plugins {
    `java-library`
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
//    signing
    // We don't use the version catalog here, to avoid a bootstrapping problem.
    id("org.metaborg.gitonium") version "1.6.2"
}

group = "org.metaborg"
description = "The Metaborg Gradle convention plugin."

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(gradleApi())
    implementation(gradleKotlinDsl())
    // We don't use the version catalog here, to avoid a bootstrapping problem.
    // Therefore, we need to keep the number of dependencies low.
    api("com.gradle:develocity-gradle-plugin:3.17.5")
    api("org.gradle.toolchains:foojay-resolver:0.8.0")

    testImplementation("io.kotest:kotest-runner-junit5:5.8.1")
    testImplementation("io.kotest:kotest-assertions-core:5.8.1")
    testImplementation(gradleTestKit())
}

gradlePlugin {
    plugins {
        // Settings plugins
        create("convention.settings") {
            id = "org.metaborg.convention.settings"
            implementationClass = "org.metaborg.convention.SettingsConventionPlugin"
        }

        // Project plugins
        create("convention.java") {
            id = "org.metaborg.convention.java"
            implementationClass = "org.metaborg.convention.JavaConventionPlugin"
        }
        create("convention.maven-publish") {
            id = "org.metaborg.convention.maven-publish"
            implementationClass = "org.metaborg.convention.MavenPublishConventionPlugin"
        }
        create("convention.root-project") {
            id = "org.metaborg.convention.root-project"
            implementationClass = "org.metaborg.convention.RootProjectConventionPlugin"
        }
    }
}

// Get the current version into the program
gitonium {
    buildPropertiesFile.set(layout.buildDirectory.file("resources/main/org/metaborg/convention/version.properties"))
}

publishing {
    afterEvaluate {
        publications {
            withType<MavenPublication> {
                pom {
                    name.set("Metaborg Gradle convention plugins")
                    description.set(project.description)
                    url.set("https://github.com/metaborg/metaborg-gradle")
                    inceptionYear.set("2024")
                    licenses {
                        // From: https://spdx.org/licenses/
                        license {
                            name.set("Apache-2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                            distribution.set("repo")
                        }
                    }
                    developers {
                        developer {
                            id.set("virtlink")
                            name.set("Daniel A. A. Pelsmaeker")
                            email.set("d.a.a.pelsmaeker@tudelft.nl")
                        }
                    }
                    scm {
                        connection.set("scm:git@github.com:metaborg/metaborg-gradle.git")
                        developerConnection.set("scm:git@github.com:metaborg/metaborg-gradle.git")
                        url.set("scm:git@github.com:metaborg/metaborg-gradle.git")
                    }
                }
            }
        }
    }
    repositories {
        maven {
            val releasesRepoUrl = uri("https://artifacts.metaborg.org/content/repositories/releases/")
            val snapshotsRepoUrl = uri("https://artifacts.metaborg.org/content/repositories/snapshots/")
            name = "MetaborgArtifacts"
            url = if (!version.toString().contains("-SNAPSHOT")) releasesRepoUrl else snapshotsRepoUrl
            credentials {
                username = project.findProperty("publish.repository.metaborg.artifacts.username") as String? ?: System.getenv("METABORG_ARTIFACTS_USERNAME")
                password = project.findProperty("publish.repository.metaborg.artifacts.password") as String? ?: System.getenv("METABORG_ARTIFACTS_PASSWORD")
            }
        }
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/metaborg/metaborg-gradle")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.publishKey") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

// signing {
//     if (!project.hasProperty("signing.secretKeyRingFile")) {
//         // If no secretKeyRingFile was set, we assume an in-memory key in the SIGNING_KEY environment variable (used in CI)
//         useInMemoryPgpKeys(
//             project.findProperty("signing.keyId") as String? ?: System.getenv("SIGNING_KEY_ID"),
//             System.getenv("SIGNING_KEY"),
//             project.findProperty("signing.password") as String? ?: System.getenv("SIGNING_KEY_PASSWORD"),
//         )
//     }
// }

val checkNotDirty by tasks.registering {
    doLast {
        if (project.version.toString().endsWith("+dirty")) {
            throw GradleException("Cannot publish a dirty version: ${project.version}")
        }
    }
}

tasks.publish { dependsOn(checkNotDirty) }