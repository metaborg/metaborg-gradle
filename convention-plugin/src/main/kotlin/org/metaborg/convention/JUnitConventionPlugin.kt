package org.metaborg.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

/**
 * Configures a Gradle project to use JUnit 5 for testing.
 *
 * Also applies the `java-base` plugin to the project. Apply the `java` or `java-library` plugin manually.
 */
class JUnitConventionPlugin: Plugin<Project> {

    override fun apply(project: Project): Unit = with(project) {
        // Add the configuration extension
        val extension = extensions.create<JUnitConventionExtension>("junitConvention")

        // Apply the Java base plugin
        plugins.apply("java-base")

        // Add the JUnit 5 dependency (org.junit.jupiter:junit-jupiter)
        // - org.junit.jupiter:junit-jupiter-api
        // - org.junit.jupiter:junit-jupiter-engine
        // - org.junit.jupiter:junit-jupiter-params
        // - org.junit.platform:junit-platform-commons
        // - org.junit.platform:junit-platform-engine
        dependencies {
            versionCatalog()
            add("testImplementation",
                with(extension.versionCatalogName, extension.jUnitAlias, extension.jUnitDependency) { versionCatalogName, junitAlias, jUnitDependency ->
                    getLibrary(junitAlias, jUnitDependency, versionCatalogName)
                }
            )
        }

        plugins.withType<JavaPlugin> {
            tasks.withType<Test> {
                // Support any JUnit 5 compatible test runner
                useJUnitPlatform()

                // Configure the logging
                testLogging {
                    // Default (lifecycle) logging level: only show test failures
                    lifecycle {
                        events(TestLogEvent.FAILED)
                        showExceptions = true
                        showCauses = true
                        showStackTraces = true
                        exceptionFormat = TestExceptionFormat.FULL
                    }
                    // Info logging level: show failed and skipped tests, and standard output/error
                    info {
                        events(
                            TestLogEvent.FAILED,
                            TestLogEvent.SKIPPED,
                            TestLogEvent.STANDARD_OUT,
                            TestLogEvent.STANDARD_ERROR
                        )
                        showExceptions = true
                        showCauses = true
                        showStackTraces = true
                        exceptionFormat = TestExceptionFormat.FULL
                    }
                    // Debug logging level: show all tests, and standard output/error
                    debug {
                        events(
                            TestLogEvent.STARTED,
                            TestLogEvent.PASSED,
                            TestLogEvent.FAILED,
                            TestLogEvent.SKIPPED,
                            TestLogEvent.STANDARD_OUT,
                            TestLogEvent.STANDARD_ERROR
                        )
                        showExceptions = true
                        showCauses = true
                        showStackTraces = true
                        exceptionFormat = TestExceptionFormat.FULL
                    }
                }
            }
        }
    }
}