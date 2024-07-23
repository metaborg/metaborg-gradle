package org.metaborg.devenv

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Special plugin for managing the development environment, such as checking out the correct repositories
 * on their correct branches.
 */
@Suppress("unused")
class DevenvRootPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        TODO("Not yet implemented")
    }
}