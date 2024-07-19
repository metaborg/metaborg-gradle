package org.metaborg.convention

import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.newInstance
import javax.inject.Inject

/** Configuration for the Maven Publish convention. */
open class MavenPublishConventionExtension @Inject constructor(
    /** The Gradle object factory. */
    objects: ObjectFactory,
) {
    /** The owner of the repository. */
    val repoOwner: Property<String> = objects.property(String::class.java)
    /** The name of the repository. */
    val repoName: Property<String> = objects.property(String::class.java)

    /** The publication metadata (dot notation). */
    val metadata: MetadataHandler = objects.newInstance<MetadataHandler>()

    /** The publication metadata (DSL notation). */
    fun metadata(action: Action<MetadataHandler>) {
        action.execute(metadata)
    }

    /** Whether to publish to GitHub packages. */
    val publishToGitHubPackages: Property<Boolean> = objects.property(Boolean::class.java)
        .convention(metadata.scm.map { it == SCM.GitHub })
    /** Whether to publish to Metaborg Artifacts. */
    val publishToMetaborgArtifacts: Property<Boolean> = objects.property(Boolean::class.java)
        .convention(true)

    /** Configuration of publication metadata. */
    open class MetadataHandler @Inject constructor(
        /** The Gradle object factory. */
        objects: ObjectFactory,
    ) {
        /** The year the project was started. */
        val inceptionYear: Property<String> = objects.property(String::class.java)
        /** The developers of the project. */
        val developers: ListProperty<Developer> = objects.listProperty(Developer::class.java)
        /** The source control management system. */
        val scm: Property<SCM> = objects.property(SCM::class.java)
            .convention(SCM.GitHub)
        /** The open source license that the project is published under. */
        val license: Property<OpenSourceLicense> = objects.property(OpenSourceLicense::class.java)
            .convention(OpenSourceLicense.Apache2)
        /** The HTTP SCM URL format. */
        val httpUrlFormat: Property<String> = objects.property(String::class.java)
            .convention(scm.map { it.httpUrlFormat })
        /** The SCM URL format. */
        val scmUrlFormat: Property<String> = objects.property(String::class.java)
            .convention(scm.map { it.scmUrlFormat })
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