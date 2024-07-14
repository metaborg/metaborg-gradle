@file:Suppress("DEPRECATION")

package org.metaborg.convention

import com.gradle.develocity.agent.gradle.DevelocityConfiguration
import com.gradle.enterprise.gradleplugin.GradleEnterpriseExtension
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.maven


/**
 * Configures a Gradle build.
 */
@Suppress("unused")
class SettingsConventionPlugin: Plugin<Settings> {

    // Version of org.metaborg.spoofax3:catalog to be used for Metaborg projects
    private val catalogVersion: String? = BuildInfo.releaseVersion

    @Suppress("UnstableApiUsage")
    override fun apply(settings: Settings): Unit = with(settings) {
        // Add the configuration extension
        val extension = extensions.create<SettingsConventionExtension>("settingsConvention")
        extension.setConvention()

        // This allows us to use plugins from Metaborg Artifacts
        pluginManagement {
            repositories {
                maven(url = "https://artifacts.metaborg.org/content/groups/public/")
            }
        }

        // This allows us to use the catalog in dependencies
        dependencyResolutionManagement {
            repositories {
                maven(url = "https://artifacts.metaborg.org/content/groups/public/")
            }
            versionCatalogs {
                create("libs") {
                    from("org.metaborg:catalog" + (catalogVersion?.let { ":$it" } ?: ""))
                }
            }
        }

        // Apply the Foojay plugin
        plugins.apply("org.gradle.toolchains.foojay-resolver-convention")

        val isCI = System.getenv("CI").isNullOrEmpty()
        gradle.settingsEvaluated {
            if (plugins.hasPlugin("com.gradle.build-scan") || plugins.hasPlugin("com.gradle.enterprise")) {
                // Configure the Gradle Enterprise plugin if it's configured
                settings.extensions.getByType(GradleEnterpriseExtension::class.java).apply {
                    buildScan {
                        termsOfServiceUrl = "https://gradle.com/terms-of-service"
                        termsOfServiceAgree = "yes"
                        publishAlwaysIf(isCI)
                        if (isCI) tag("CI")
                    }
                }
            } else {
                // Otherwise, apply and configure the Gradle Develocity plugin and configure it
                plugins.apply("com.gradle.develocity")
                settings.extensions.getByType(DevelocityConfiguration::class.java).apply {
                    buildScan {
                        termsOfUseUrl.set("https://gradle.com/help/legal-terms-of-use")
                        termsOfUseAgree.set("yes")
                        publishing.onlyIf { isCI }
                        if (isCI) tag("CI")
                        capture {
                            fileFingerprints.set(true)
                        }
                    }
                }
            }
        }
    }
}