package org.metaborg.convention

import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.newInstance
import javax.inject.Inject

/**
 * Configuration for the Maven Publish convention.
 *
 * Create an instance like this:
 *
 * ```kotlin
 * extensions.create<MavenPublishConventionExtension>("mavenPublishConvention")
 * extension.setConvention()
 * ```
 */
open class MavenPublishConventionExtension @Inject constructor(
    /** The Gradle object factory. */
    objects: ObjectFactory,
) {
    /** The owner of the repository. */
    val repoOwner: Property<String> = objects.property(String::class.java)
    /** The name of the repository. */
    val repoName: Property<String> = objects.property(String::class.java)

    /** Whether to publish to GitHub packages. */
    val publishToGitHubPackages: Property<Boolean> = objects.property(Boolean::class.java)
    /** Whether to publish to Metaborg Artifacts. */
    val publishToMetaborgArtifacts: Property<Boolean> = objects.property(Boolean::class.java)

    /** The publication metadata (dot notation). */
    val metadata: MetadataHandler = objects.newInstance<MetadataHandler>()

    /** The publication metadata (DSL notation). */
    fun metadata(action: Action<MetadataHandler>) {
        action.execute(metadata)
    }

    /**
     * Sets the convention (default values) for the configuration extension.
     */
    fun setConvention() {
        publishToGitHubPackages.convention(metadata.scm.map { it == SCM.GitHub })
        publishToMetaborgArtifacts.convention(true)
        metadata.setConvention()
    }

    /** Configuration of publication metadata. */
    interface MetadataHandler {
        /** The year the project was started. */
        val inceptionYear: Property<String>
        /** The developers of the project. */
        val developers: ListProperty<Developer>
        /** The source control management system. */
        val scm: Property<SCM>
        /** The open source license that the project is published under. */
        val license: Property<OpenSourceLicense>
        /** The HTTP SCM URL format. */
        val httpUrlFormat: Property<String>
        /** The SCM URL format. */
        val scmUrlFormat: Property<String>

        /**
         * Sets the convention (default values) for the configuration extension.
         */
        fun setConvention() {
            scm.convention(SCM.GitHub)
            license.convention(OpenSourceLicense.Apache2)
            httpUrlFormat.convention(scm.map { it.httpUrlFormat })
            scmUrlFormat.convention(scm.map { it.scmUrlFormat })
        }
    }
}


/** Specifies a developer. */
data class Developer(
    /** The developer's ID or username. */
    val id: String,
    /** The developer's full name. */
    val name: String,
    /** The developer's email address. */
    val email: String,
)


/** Specifies a source control management system. */
enum class SCM(
    /** The HTTP URL for the SCM. */
    val httpUrlFormat: String,
    /** The SCM URL for the SCM. */
    val scmUrlFormat: String,
) {
    /** GitHub */
    GitHub("https://github.com/%s/%s", "scm:git@github.com:%s/%s.git"),
}


/** Specifies the open source license. */
enum class OpenSourceLicense(
    /** The unique identifier of the license. */
    val id: String,
    /** The license URL. */
    val url: String,
) {
    // From: https://spdx.org/licenses/

    /** Apache 2 */
    Apache2("Apache-2.0", "https://www.apache.org/licenses/LICENSE-2.0.txt"),
}