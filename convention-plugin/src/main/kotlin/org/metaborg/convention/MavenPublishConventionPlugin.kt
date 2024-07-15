package org.metaborg.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.tasks.PublishToMavenRepository
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.withType
import java.util.*

private const val METABORG_ARTIFACTS_PUBLICATION_NAME = "MetaborgArtifacts"
private const val GITHUB_PACKAGES_PUBLICATION_NAME = "GitHubPackages"

/**
 * Configures a Gradle project that publishes artifacts to Maven repositories.
 */
@Suppress("unused")
class MavenPublishConventionPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        // Add the configuration extension
        val extension = extensions.create<MavenPublishConventionExtension>("mavenPublishConvention")
        extension.setConvention()

        // Apply the Maven Publish plugin
        plugins.apply("maven-publish")

        // Set the metadata for (existing) publications
        afterEvaluate {
            configure<PublishingExtension> {
                val metadata = extension.metadata
                publications {
                    withType<MavenPublication> {
                        pom {
                            description.set(project.description)
                            url.set(metadata.httpUrlFormat.with(extension.repoOwner, extension.repoName) { format, owner, name ->
                                String.format(Locale.ROOT, format, owner, name)
                            })
                            inceptionYear.set(metadata.inceptionYear)
                            licenses {
                                license {
                                    name.set(metadata.license.map { it.id })
                                    url.set(metadata.license.map { it.url })
                                    distribution.set("repo")
                                }
                            }
                            developers {
                                metadata.developers.map {
                                    for (dev in it) {
                                        developer {
                                            id.set(dev.id)
                                            name.set(dev.name)
                                            email.set(dev.email)
                                        }
                                    }
                                }
                            }
                            scm {
                                connection.set(metadata.scmUrlFormat.with(extension.repoOwner, extension.repoName) { format, owner, name ->
                                    String.format(Locale.ROOT, format, owner, name)
                                })
                                developerConnection.set(metadata.scmUrlFormat.with(extension.repoOwner, extension.repoName) { format, owner, name ->
                                    String.format(Locale.ROOT, format, owner, name)
                                })
                                url.set(metadata.scmUrlFormat.with(extension.repoOwner, extension.repoName) { format, owner, name ->
                                    String.format(Locale.ROOT, format, owner, name)
                                })
                            }
                        }
                    }
                }

                // Configure the repositories to publish to
                repositories {

                    // Configure publishing to Metaborg Artifacts
                    maven {
                        val releasesRepoUrl = uri("https://artifacts.metaborg.org/content/repositories/releases/")
                        val snapshotsRepoUrl = uri("https://artifacts.metaborg.org/content/repositories/snapshots/")
                        name = METABORG_ARTIFACTS_PUBLICATION_NAME
                        url = if (!version.toString().contains("-SNAPSHOT")) releasesRepoUrl else snapshotsRepoUrl
                        credentials {
                            username = project.findProperty("publish.repository.metaborg.artifacts.username") as String? ?: System.getenv("METABORG_ARTIFACTS_USERNAME")
                            password = project.findProperty("publish.repository.metaborg.artifacts.password") as String? ?: System.getenv("METABORG_ARTIFACTS_PASSWORD")
                        }
                    }

                    // Configure publishing to GitHub Packages
                    maven {
                        name = GITHUB_PACKAGES_PUBLICATION_NAME
                        // NOTE: If repoOwner or repoName are not specified, this will fail. In that case we can't configure GitHub Packages
                        url = with(extension.repoOwner, extension.repoName) { owner, name -> uri("https://maven.pkg.github.com/$owner/$name") }.get()
                        credentials {
                            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                            password = project.findProperty("gpr.publishKey") as String? ?: System.getenv("GITHUB_TOKEN")
                        }
                    }
                }
            }
        }

        gradle.taskGraph.whenReady {
            tasks.withType<PublishToMavenRepository>().configureEach {
                // Conditionally enable the tasks that publish to the respective repositories
                when {
                    name.endsWith("PublicationTo${METABORG_ARTIFACTS_PUBLICATION_NAME}Repository") -> {
                        onlyIf { extension.publishToMetaborgArtifacts.get() }
                    }
                    name.endsWith("PublicationTo${GITHUB_PACKAGES_PUBLICATION_NAME}Repository") -> {
                        onlyIf { extension.publishToGitHubPackages.get() }
                    }
                }
            }
        }
    }
}