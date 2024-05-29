package org.metaborg.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.withType
import java.util.*

class MavenPublishConventionPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        // Add the configuration extension
        val extension = extensions.create<MavenPublishConventionExtension>("mavenPublishConvention")
        extension.setConvention()

        // Apply the Maven Publish plugin
        plugins.apply("maven-publish")

        configure<PublishingExtension> {
            afterEvaluate {
                publications {
                    withType<MavenPublication> {
                        pom {
                            description.set(project.description)
                            url.set(extension.httpUrlFormat.with(extension.repoOwner, extension.repoName) { format, owner, name ->
                                String.format(Locale.ROOT, format, owner, name)
                            })
                            inceptionYear.set(extension.inceptionYear)
                            licenses {
                                license {
                                    name.set(extension.license.map { it.name })
                                    url.set(extension.license.map { it.url })
                                    distribution.set("repo")
                                }
                            }
                            developers {
                                extension.developers.map {
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
                                connection.set(extension.scmUrlFormat.with(extension.repoOwner, extension.repoName) { format, owner, name ->
                                    String.format(Locale.ROOT, format, owner, name)
                                })
                                developerConnection.set(extension.scmUrlFormat.with(extension.repoOwner, extension.repoName) { format, owner, name ->
                                    String.format(Locale.ROOT, format, owner, name)
                                })
                                url.set(extension.scmUrlFormat.with(extension.repoOwner, extension.repoName) { format, owner, name ->
                                    String.format(Locale.ROOT, format, owner, name)
                                })
                            }
                        }
                    }
                }
            }
        }

        // maven-publish plugin
        // - Configure maven-publish
        //   configure SCM (GitHub by default, etc)
        //   configure owner
        //   configure name
        //   configure license (Apache 2.0 by default)
        //   configure developers
        //   configure inception year
        //   configure publish to GitHub packages (by default if SCM = GitHub)
        //   configure publish to Metaborg Artifacts (by default)
        //   configure publish to Maven Central (optional)
        // - Configure signing (optional)

        TODO("Not yet implemented")
    }
}