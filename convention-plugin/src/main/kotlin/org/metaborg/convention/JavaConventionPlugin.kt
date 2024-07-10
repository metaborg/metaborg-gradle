package org.metaborg.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.api.tasks.testing.Test
import org.gradle.external.javadoc.CoreJavadocOptions
import org.gradle.kotlin.dsl.*

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
        extension.setConvention()

        // Apply the Java base plugin
        plugins.apply("java-base")

        repositories {
            // Maven Artifacts repository
            maven(url = "https://artifacts.metaborg.org/content/groups/public/")
            // Repository for other packages
            mavenCentral()
        }

        plugins.withType<JavaPlugin> {
            tasks.withType<Test> {
                // Support any JUnit 5 compatible test runner
                useJUnitPlatform()
            }

            configure<JavaPluginExtension> {
                // Compile to a specific Java version
                toolchain.languageVersion.set(extension.javaVersion)
                // Generate a sources JAR
                withSourcesJar()
                // Generate a JavaDoc JAR
                withJavadocJar()
            }

            tasks.withType<JavaCompile> {
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

            tasks.withType<Javadoc> {
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
        }
    }
}
