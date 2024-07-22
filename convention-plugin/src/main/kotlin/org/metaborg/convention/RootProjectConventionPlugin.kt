package org.metaborg.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.publish.plugins.PublishingPlugin
import org.gradle.kotlin.dsl.create
import org.gradle.language.base.plugins.LifecycleBasePlugin

/**
 * Configures a root project of a multi-project build, by adding explicit tasks
 * that call tasks in the sub-builds and subprojects.
 *
 * Normally, when you execute a task such as `test` in a multi-project build, you will execute
 * all `:test` tasks in all projects. In contrast, when you specifically execute `:test`
 * (prefixed with a colon), you execute the `:test` task only in the root project.
 * Now, we would like to create a task in this composite build that executes basically
 * the equivalent of `test` in each of the included multi-project builds, which would execute
 * `:test` in each of the projects. However, this seems to be impossible to write down. Instead,
 * we call the root `:test` task in the included build, and in each included build's multi-project
 * root project we'll extend the `test` task to depend on the `:test` tasks of the subprojects.
 */
class RootProjectConventionPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        // Add the configuration extension
        val extension = extensions.create<RootProjectConventionExtension>("rootProjectConvention")

        afterEvaluate {
            // Build tasks
            tasks.register(extension.assembleTaskName.get()) {
                group = LifecycleBasePlugin.BUILD_GROUP
                description = "Assembles the outputs of the subprojects and included builds."
                dependsOn(gradle.includedBuilds.map { it.task(":${LifecycleBasePlugin.ASSEMBLE_TASK_NAME}") })
                dependsOn(project.subprojects.mapNotNull { it.tasks.findByName(LifecycleBasePlugin.ASSEMBLE_TASK_NAME) })
            }
            tasks.register(extension.buildTaskName.get()) {
                group = LifecycleBasePlugin.BUILD_GROUP
                description = "Assembles and tests the subprojects and included builds."
                dependsOn(gradle.includedBuilds.map { it.task(":${LifecycleBasePlugin.BUILD_TASK_NAME}") })
                dependsOn(project.subprojects.mapNotNull { it.tasks.findByName(LifecycleBasePlugin.BUILD_TASK_NAME) })
            }
            tasks.register(extension.cleanTaskName.get()) {
                group = LifecycleBasePlugin.BUILD_GROUP
                description = "Cleans the outputs of the subprojects and included builds."
                dependsOn(gradle.includedBuilds.map { it.task(":${LifecycleBasePlugin.CLEAN_TASK_NAME}") })
                dependsOn(project.subprojects.mapNotNull { it.tasks.findByName(LifecycleBasePlugin.CLEAN_TASK_NAME) })
            }

            // Publishing tasks
            tasks.register(extension.publishTaskName.get()) {
                group = PublishingPlugin.PUBLISH_TASK_GROUP
                description = "Publishes all subprojects and included builds to a remote Maven repository."
                dependsOn(gradle.includedBuilds.map { it.task(":${PublishingPlugin.PUBLISH_LIFECYCLE_TASK_NAME}") })
                dependsOn(project.subprojects.mapNotNull { it.tasks.findByName(PublishingPlugin.PUBLISH_LIFECYCLE_TASK_NAME) })
            }
            tasks.register(extension.publishToMavenLocalTaskName.get()) {
                group = PublishingPlugin.PUBLISH_TASK_GROUP
                description = "Publishes all subprojects and included builds to the local Maven repository."
                dependsOn(gradle.includedBuilds.map { it.task(":${PublishingPlugin.PUBLISH_LIFECYCLE_TASK_NAME}ToMavenLocal") })
                dependsOn(project.subprojects.mapNotNull { it.tasks.findByName("${PublishingPlugin.PUBLISH_LIFECYCLE_TASK_NAME}ToMavenLocal") })
            }

            // Verification tasks
            tasks.register(extension.checkTaskName.get()) {
                group = LifecycleBasePlugin.VERIFICATION_GROUP
                description = "Runs all checks on the subprojects and included builds."
                dependsOn(gradle.includedBuilds.map { it.task(":${LifecycleBasePlugin.CHECK_TASK_NAME}") })
                dependsOn(project.subprojects.mapNotNull { it.tasks.findByName(LifecycleBasePlugin.CHECK_TASK_NAME) })
            }
            tasks.register(extension.testTaskName.get()) {
                group = LifecycleBasePlugin.VERIFICATION_GROUP
                description = "Runs all unit tests on the subprojects and included builds."
                dependsOn(gradle.includedBuilds.map { it.task(":${JavaPlugin.TEST_TASK_NAME}") })
                dependsOn(project.subprojects.mapNotNull { it.tasks.findByName(JavaPlugin.TEST_TASK_NAME) })
            }

            // Help tasks
            tasks.register(extension.tasksTaskName.get()) {
                group = "Help"
                description = "Displays all tasks of subprojects and included builds."
                dependsOn(gradle.includedBuilds.map { it.task(":tasks") })
                dependsOn(project.subprojects.mapNotNull { it.tasks.findByName("tasks") })
            }
        }
    }
}