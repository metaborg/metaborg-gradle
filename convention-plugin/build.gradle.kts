// Workaround for issue: https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    `java-library`
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
//    signing
    alias(libs.plugins.gitonium)
}

group = "org.metaborg"
description = "The Metaborg Gradle convention plugin."

extra["isReleaseVersion"] = !version.toString().endsWith("-SNAPSHOT")
extra["isDirtyVersion"] = version.toString().endsWith("+dirty")
extra["isCI"] = !System.getenv("CI").isNullOrEmpty()

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.kotest)
    testImplementation(libs.kotest.assertions)
    testImplementation(gradleTestKit())
}

gradlePlugin {
    plugins {
        create("convention.java") {
            id = "org.metaborg.convention.java"
            implementationClass = "org.metaborg.convention.JavaConventionPlugin"
        }
        create("convention.maven-publish") {
            id = "org.metaborg.convention.maven-publish"
            implementationClass = "org.metaborg.convention.MavenPublishConventionPlugin"
        }
    }
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
            url = if (project.extra["isReleaseVersion"] as Boolean) releasesRepoUrl else snapshotsRepoUrl
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
        if (project.extra["isDirtyVersion"] as Boolean) {
            throw GradleException("Cannot publish a dirty version: ${project.version}")
        }
    }
}

tasks.publish { dependsOn(checkNotDirty) }