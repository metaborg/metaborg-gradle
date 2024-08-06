package org.metaborg.devenv

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.options.Option
import org.gradle.kotlin.dsl.register

/**
 * Special plugin for managing the development environment, such as checking out the correct repositories
 * on their correct branches.
 */
@Suppress("unused")
class RepoRootPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        tasks.register<RepositoryTask>("update") {
            doLast {
                // Clone and checkout each submodule to the expected commit
                //      git submodule update --recursive --init
                // Update each submodule to the tip of the remote branch (detached HEAD)
                //      git submodule update --recursive --remote
                // Update each submodule to the tip of the remote branch, and rebase our changes onto it
                //      git submodule update --recursive --remote --rebase
                // Update each submodule to the tip of the remote branch, and merge our changes into it
                //      git submodule update --recursive --remote --merge
            }
            description = "For each repository (with update=true): check out the repository to the correct branch " +
                    "and pull from origin, or clone the repository if it has not been cloned yet."
        }
    }
}


open class RepositoryTask : DefaultTask() {
    @Input
    @Option(option = "repo", description = "Repository to perform the task on. Defaults to all repositories.")
    var repos: List<String> = emptyList()

    init {
        group = "Repo management"
    }
}