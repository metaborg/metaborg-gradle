package org.metaborg.repoman.meta

import kotlinx.serialization.Serializable
import org.metaborg.repoman.Markdown
import java.time.Year

/** Default values. */
object Defaults {
    /** The default main branch in Gitonium. */
    const val MAIN_BRANCH = "main"
    /** THe default release tag prefix in Gitonium. */
    const val RELEASE_TAG_PREFIX = "release-"
}

/** Repository metadata. */
@Serializable
data class RepoMetadata(
    /** The owner of the repo on GitHub. For example: `"metaborg"` */
    val repoOwner: String = "metaborg",
    /** The name of the repo on GitHub (required). For example: `"resource"` */
    val repoName: String,
    /** The name of the main branch. For example: `"master"` */
    val mainBranch: String = Defaults.MAIN_BRANCH,
    /** The release tag prefix to use. For example: `"devenv-release/"` */
    val releaseTagPrefix: String = Defaults.RELEASE_TAG_PREFIX,
    /** The default Maven group of the artifacts in the build. For example: `"org.metaborg.devenv"` */
    val mavenGroup: String = "org.metaborg",

    /** The title of the repo (required). For example: `"Metaborg Resource"` */
    val title: String,
    /** A short description of the repo; or `null`. For example: `"A utility library for working with resources."` */
    val description: Markdown? = null,
    /** A link to the hosted documentation of the repo; or `null`. For example: `"https://spoofax.dev/gitonium/"` */
    val documentationLink: String? = null,
    /** The inception year of the repository (required). */
    val inceptionYear: String,
    /** The current year in which the repository is still maintained. */
    val currentYear: String = Year.now().toString(),

    /** A list of Maven libraries published by the repo. */
    val libraries: List<MavenArtifact> = emptyList(),
    /** A list of Spoofax languages published by the repo. */
    val languages: List<MavenArtifact> = emptyList(),
    /** A list of Gradle plugins published by the repo. */
    val plugins: List<GradlePlugin> = emptyList(),

    /** An ordered list of developers that may be contacted about the repo. */
    val developers: List<Person> = emptyList(),
    /** An ordered list of (main) contributors that also worked on the repo. */
    val contributors: List<Person> = emptyList(),

    /** The configurations for the generated files. */
    val files: Files = Files(),
)

/** Metadata for the files to generate. */
@Serializable
data class Files(
    /** The metadata for the README.md file. */
    val readme: Readme = Readme(),
    /** The metadata for the LICENSE.md file. */
    val license: License = License(),
    /** The metadata for the CONTRIBUTING.md file. */
    val contributing: Contributing = Contributing(),
    /** The metadata for the CODE_OF_CONDUCT.md file. */
    val codeOfConduct: CodeOfConduct = CodeOfConduct(),
    /** The metadata for the CHANGELOG.md file. */
    val changelog: Changelog = Changelog(),
    /** The metadata for the .gitignore file. */
    val gitignore: Gitignore = Gitignore(),
    /** The metadata for the Gradle wrapper files. */
    val gradleWrapper: GradleWrapper = GradleWrapper(),
    /** The metadata for the Gradle root project files. */
    val gradleRootProject: GradleRootProject = GradleRootProject(),
    /** The metadata for the GitHub workflows. */
    val githubWorkflows: GithubWorkflows = GithubWorkflows(),
    /** The metadata for GitHub issue templates. */
    val githubIssueTemplates: GithubIssueTemplates = GithubIssueTemplates(),
)

/** Metadata for the README.md file. */
@Serializable
data class Readme(
    /** Whether to generate the file. */
    val generate: Boolean = true,
    /** Whether to update the file. */
    val update: Boolean = true,
    /** Content to include in the main body of the readme; or `null`. */
    val body: Markdown? = null,
)

/** Metadata for the LICENSE.md file. */
@Serializable
data class License(
    /** Whether to generate the file. */
    val generate: Boolean = true,
    /** Whether to update the file. */
    val update: Boolean = true,
)

/** Metadata for the CONTRIBUTING.md file. */
@Serializable
data class Contributing(
    /** Whether to generate the file. */
    val generate: Boolean = true,
    /** Whether to update the file. */
    val update: Boolean = true,
)

/** Metadata for the CODE_OF_CONDUCT.md file. */
@Serializable
data class CodeOfConduct(
    /** Whether to generate the file. */
    val generate: Boolean = true,
    /** Whether to update the file. */
    val update: Boolean = true,
)

/** Metadata for the CHANGELOG.md file. */
@Serializable
data class Changelog(
    /** Whether to generate the file. */
    val generate: Boolean = true,
    /** Whether to update the file. */
    val update: Boolean = false,
)

/** Metadata for the .gitignore file. */
@Serializable
data class Gitignore(
    /** Whether to generate the file. */
    val generate: Boolean = true,
    /** Whether to update the file. */
    val update: Boolean = true,
    /** Extra entries to include at the bottom of the .gitignore file; or `null`. */
    val extra: String? = null,
)

/** Metadata for the .gradlew files. */
@Serializable
data class GradleWrapper(
    /** Whether to generate the files. */
    val generate: Boolean = true,
    /** Whether to update the file. */
    val update: Boolean = true,
    /** The version of the Gradle wrapper to generate. */
    val gradleVersion: String = "7.6.4",
    /** The kind of Gradle distribution type to use, either `"bin"` or `"all"`. */
    val gradleDistributionType: String = "bin",
)

/** Metadata for the Gradle root project files. */
@Serializable
data class GradleRootProject(
    /** Whether to generate the files. */
    val generate: Boolean = true,
    /** Whether to update the file. */
    val update: Boolean = false,
    /** The name of the root project. */
    val rootProjectName: String? = null,
    /** Included builds. */
    val includedBuilds: List<IncludedBuild> = emptyList(),
    /** Included projects. */
    val includedProjects: List<IncludedProject> = emptyList(),
    /** The version of the Metaborg Gradle convention to use. */
    val conventionVersion: String = "latest.integration",
    /** Whether to create `publish` tasks that delegate to the included builds and subprojects. */
    val createPublishTasks: Boolean = false,
)

/** Metadata for the GitHub workflows. */
@Serializable
data class GithubWorkflows(
    /** Whether to generate the files. */
    val generate: Boolean = true,
    /** Whether to update the file. */
    val update: Boolean = true,
    /** Whether to publish releases using GitHub CI (instead of Jenkins or something else). */
    val publishRelease: Boolean = false,
    /** Whether to publish snapshots using GitHub CI. */
    val publishSnapshot: Boolean = false,
    /** The Gradle `:build` task to use. */
    val buildTask: String = "build",        // NOTE: No `:` prefix to allow Gradle to figure it out itself.
    /** The Gradle `:publish` task to use. */
    val publishTask: String = "publish",    // NOTE: No `:` prefix to allow Gradle to figure it out itself.
    /** The Gradle `:printVersion` task to use. */
    val printVersionTask: String = ":printVersion",
    /** Whether to build and publish the documentation using GitHub CI. */
    val buildDocs: Boolean = false,
)

/** Metadata for GitHub issue templates. */
@Serializable
data class GithubIssueTemplates(
    /** Whether to generate the files. */
    val generate: Boolean = true,
    /** Whether to update the file. */
    val update: Boolean = true,
    /** Whether to automatically assign developers to issues. */
    val assignDevelopers: Boolean = true,
    /** Whether to use the GitHub Discussions tab. */
    val useDiscussions: Boolean = true,
    /** The type label to apply to bugs; or `null` to not apply a label. */
    val bugTypeLabel: String? = "bug",
    /** The type label to apply to feature requests; or `null` to not apply a label. */
    val featureRequestTypeLabel: String? = "feature-request",
    /** The type label to apply to questions; or `null` to not apply a label. */
    val questionTypeLabel: String? = "question",
    /** The state label to apply to new bugs; or `null` to not apply a label. */
    val bugStateLabel: String? = "needs-triage",
    /** The state label to apply to new feature requests; or `null` to not apply a label. */
    val featureRequestStateLabel: String? = "needs-triage",
    /** The state label to apply to new questions; or `null` to not apply a label. */
    val questionStateLabel: String? = null,
)

/** A Maven artifact. */
@Serializable
data class MavenArtifact(
    /** The group ID of the artifact. For example: `"org.metaborg"` */
    val group: String,
    /** The name of the artifact. For example: `"resource"` */
    val name: String,
    /** A short description. For example: `"Resource management library."` */
    val description: Markdown? = null,
)

/** A Gradle plugin. */
@Serializable
data class GradlePlugin(
    /** The ID of the plugin. For example: `"org.metaborg.convention.maven-publish"`. */
    val id: String,
    /** A short description. For example: `"Resource management library."` */
    val description: Markdown? = null,
)

/** A person. */
@Serializable
data class Person(
    /** The GitHub nickname of the person. */
    val id: String? = null,
    /** The (full) name of the person. */
    val name: String,
    /** The e-mail address of the person. */
    val email: String? = null,
)

/** An included build. */
@Serializable
data class IncludedBuild(
    /** The name of the included build; or `null` to use the default. */
    val name: String? = null,
    /** The path to the included build. */
    val path: String,
)

/** An included project. */
@Serializable
data class IncludedProject(
    /** The name of the included project. */
    val name: String,
    /** The path to the included project; or `null` to use the default. */
    val path: String? = null,
)