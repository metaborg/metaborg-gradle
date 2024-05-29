package org.metaborg.gradle

import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.jvm.toolchain.JavaLanguageVersion

/** Configuration for the Maven Publish convention. */
interface MavenPublishConventionExtension {
    /** The owner of the repository. */
    val repoOwner: Property<String>
    /** The name of the repository. */
    val repoName: Property<String>
    /** The year the project was started. */
    val inceptionYear: Property<String>
    /** The developers of the project. */
    val developers: ListProperty<Developer>
    /** The source control management system. */
    val scm: Property<SCM>
    /** The open source license that the project is published under. */
    val license: Property<OpenSourceLicense>
    /** Whether to publish to GitHub packages. */
    val publishToGitHubPackages: Property<Boolean>
    /** Whether to publish to Metaborg Artifacts. */
    val publishToMetaborgArtifacts: Property<Boolean>
    /** Whether to sign the artifacts. */
    val sign: Property<Boolean>
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
        publishToGitHubPackages.convention(scm.map { it == SCM.GitHub })
        publishToMetaborgArtifacts.convention(true)
        sign.convention(false)  // Not yet supported
        httpUrlFormat.convention(scm.map { it.httpUrlFormat })
        scmUrlFormat.convention(scm.map { it.scmUrlFormat })
    }
}