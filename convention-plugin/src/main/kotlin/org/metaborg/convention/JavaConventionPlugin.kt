package org.metaborg.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.api.tasks.testing.Test
import org.gradle.external.javadoc.CoreJavadocOptions
import org.gradle.kotlin.dsl.*
import org.gradle.process.JavaForkOptions

/**
 * Configures a Gradle project that builds a Java library or application.
 *
 * Also applies the `java-base` plugin to the project. Apply the `java` or `java-library` plugin manually.
 */
@Suppress("unused")
class JavaConventionPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        // Add the configuration extension
        val extension = extensions.create<JavaConventionExtension>("javaConvention")

        // Apply the Java base plugin
        plugins.apply("java-base")

        plugins.withType<JavaPlugin> {
            configure<JavaPluginExtension> {
                // Compile to a specific Java version
                toolchain.languageVersion.set(extension.javaVersion)
                // Generate a sources JAR
                withSourcesJar()
                // Generate a JavaDoc JAR
                withJavadocJar()
            }

            tasks.withType<JavaCompile>().configureEach {
                // Use UTF-8 encoding by default
                options.encoding = "UTF-8"
                // Warn for unchecked casts
                options.compilerArgs.add("-Xlint:unchecked")
                // Show more details when the project uses API that is deprecated
                options.compilerArgs.add("-Xlint:deprecation")
                // Show more details when the project uses API scheduled for removal
                options.compilerArgs.add("-Xlint:removal")
                // Silence doclint warnings
                options.compilerArgs.add("-Xdoclint:none")
            }

            tasks.withType<Javadoc>().configureEach {
                options {
                    this as CoreJavadocOptions
                    // Use UTF-8 encoding by default
                    encoding = "UTF-8"
                    charset("UTF-8")
                    // Reduce doclint warnings
                    addStringOption("Xmaxwarns", "1")
                    // Silence doclint lints
                    addStringOption("Xdoclint:none", "-quiet")
                    // Silence!
                    quiet()
                }
            }

            tasks.named<Test>(JavaPlugin.TEST_TASK_NAME) {
                addPropertiesWithPrefix("test.", project)
            }
        }

        plugins.withType<ApplicationPlugin> {
            tasks.named<JavaExec>(ApplicationPlugin.TASK_RUN_NAME) {
                addPropertiesWithPrefix("run.", project)
            }
        }
    }

    /**
     * Adds all system properties and project properties that have the given [prefix] (case-insensitive)
     * to the properties of the Java process (without the prefix).
     *
     * @param prefix The property prefix to look for.
     * @param project The Gradle project.
     */
    private fun JavaForkOptions.addPropertiesWithPrefix(prefix: String, project: Project) {
        @Suppress("UNCHECKED_CAST")
        for ((k, v) in System.getProperties() as Map<String, *>) {
            if (!k.startsWith(prefix, ignoreCase = true)) continue
            systemProperty(k.substring(prefix.length), v.toString())
        }
        for ((k, v) in project.properties) {
            if (!k.startsWith(prefix, ignoreCase = true)) continue
            systemProperty(k.substring(prefix.length), v.toString())
        }
    }
}
