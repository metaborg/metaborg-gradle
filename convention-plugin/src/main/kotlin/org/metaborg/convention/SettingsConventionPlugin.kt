package org.metaborg.convention

import com.gradle.develocity.agent.gradle.DevelocityConfiguration
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
    private val catalogVersion = "0.4.3"

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
                    from("org.metaborg.spoofax3:catalog:$catalogVersion")
                }
            }
        }

        // Apply the Foojay plugin
        plugins.apply("org.gradle.toolchains.foojay-resolver-convention")

        // Apply and configure the Develocity plugin
        plugins.apply("com.gradle.develocity")
        extensions.configure(DevelocityConfiguration::class.java) {
            val isCI = System.getenv("CI").isNullOrEmpty()
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