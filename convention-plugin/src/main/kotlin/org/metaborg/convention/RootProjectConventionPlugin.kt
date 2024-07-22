package org.metaborg.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.publish.plugins.PublishingPlugin
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.create
import org.gradle.language.base.plugins.LifecycleBasePlugin

/**
 * Configures a root project of a multi-project/composite build, by adding explicit lifecycle tasks
 * that call tasks in the sub-builds and subprojects.
 *
 * Normally, when you execute a task such as `check` in a multi-project build, you will execute
 * all `:check` tasks in all projects. In contrast, when you specifically execute `:check`
 * (prefixed with a colon), you execute the `:check` task only in the root project.
 * Now, we would like to create a task in this composite build that executes basically
 * the equivalent of `check` in each of the included multi-project builds, which would execute
 * `:check` in each of the projects. However, this seems to be impossible to write down. Instead,
 * we call the root `:check` task in the included build, and in each included build's multi-project
 * root project we'll extend the `check` task to depend on the `:check` tasks of the subprojects.
 */
class RootProjectConventionPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        // Add the configuration extension
        val extension = extensions.create<RootProjectConventionExtension>("rootProjectConvention")

        afterEvaluate {
            // Lifecycle tasks
            registerRootTasks(
                "${LifecycleBasePlugin.ASSEMBLE_TASK_NAME}All",
                LifecycleBasePlugin.ASSEMBLE_TASK_NAME,
                LifecycleBasePlugin.BUILD_GROUP,
                "Assembles the outputs of this project, subprojects, and included builds.",
                extension.registerLifecycleTasks.get(),
                extension.registerStubLifecycleTasks.get(),
            )
            registerRootTasks(
                "${LifecycleBasePlugin.BUILD_TASK_NAME}All",
                LifecycleBasePlugin.BUILD_TASK_NAME,
                LifecycleBasePlugin.BUILD_GROUP,
                "Assembles and tests this project, subprojects, and included builds.",
                extension.registerLifecycleTasks.get(),
                extension.registerStubLifecycleTasks.get(),
            )
            registerRootTasks(
                "${LifecycleBasePlugin.CLEAN_TASK_NAME}All",
                LifecycleBasePlugin.CLEAN_TASK_NAME,
                LifecycleBasePlugin.BUILD_GROUP,
                "Cleans the outputs of this project, subprojects, and included builds.",
                extension.registerLifecycleTasks.get(),
                extension.registerStubLifecycleTasks.get(),
            )
            registerRootTasks(
                "${LifecycleBasePlugin.CHECK_TASK_NAME}All",
                LifecycleBasePlugin.CHECK_TASK_NAME,
                LifecycleBasePlugin.VERIFICATION_GROUP,
                "Runs all checks on this project, subprojects, and included builds.",
                extension.registerLifecycleTasks.get(),
                extension.registerStubLifecycleTasks.get(),
            )

            // Publish tasks
            registerRootTasks(
                "${PublishingPlugin.PUBLISH_LIFECYCLE_TASK_NAME}All",
                PublishingPlugin.PUBLISH_LIFECYCLE_TASK_NAME,
                PublishingPlugin.PUBLISH_TASK_GROUP,
                "Publishes all publications produced by this project, subprojects, and included builds to remote Maven repositories.",
                extension.registerPublishTasks.get(),
                extension.registerStubPublishTasks.get(),
            )
            registerRootTasks(
                "${PublishingPlugin.PUBLISH_LIFECYCLE_TASK_NAME}AllToMavenLocal",
                "${PublishingPlugin.PUBLISH_LIFECYCLE_TASK_NAME}ToMavenLocal",
                PublishingPlugin.PUBLISH_TASK_GROUP,
                "Publishes all Maven publications produced by this project, subprojects, and included builds to the local Maven cache.",
                extension.registerPublishTasks.get(),
                extension.registerStubPublishTasks.get(),
            )
        }
    }

    /**
     * Registers the tasks in the root project.
     *
     * This registers a `buildAll` task (if [addAggregateLifecycleTask] is `true`) that depends on
     * the existing `build` task in the root project, the `build` tasks in the subprojects,
     * and the `build` tasks in the included builds.
     *
     * This also registers a `build` task (if [addStubLifecycleTask] is `true`) that depends on
     * the `buildAll` task (if defined).
     *
     * @param allTaskName The name of the `*All` task, for example `buildAll`.
     * @param taskName The name of the task, for example `build`.
     * @param group The group of the tasks.
     * @param description The description of the tasks.
     * @param addAggregateLifecycleTask Whether to add the aggregate `*All` lifecycle task.
     * @param addStubLifecycleTask Whether to add the stub `*` lifecycle task.
     */
    private fun Project.registerRootTasks(
        allTaskName: String,
        taskName: String,
        group: String,
        description: String,
        addAggregateLifecycleTask: Boolean,
        addStubLifecycleTask: Boolean,
    ) {
        // NOTE: The order is important.
        // First, we check if there is already a `build` lifecycle task.
        // Then we add the `buildAll` lifecycle task, which may depend on the existing `build` task, if any.
        // To prevent cycles, only after that do we add the `build` stub lifecycle task itself,
        // which depends on the `buildAll` task (if defined).

        val existingTask = project.tasks.findByName(taskName)

        val allTask: TaskProvider<Task>? = if (addAggregateLifecycleTask) {
            tasks.register(allTaskName) {
                this.group = group
                this.description = description
                // Depend on the normal lifecycle task, if any
                existingTask?.let { dependsOn(it) }
                // Depend on the normal lifecycle tasks of the subprojects, if present
                dependsOn(project.subprojects.mapNotNull { it.tasks.findByName(taskName) })
                // Depend on the normal lifecycle tasks in included builds
                // NOTE: This may cause an error if the build doesn't define the specified task.
                dependsOn(gradle.includedBuilds.map { it.task(":$taskName") })
            }
        } else null

        if (addStubLifecycleTask && existingTask == null) {
            tasks.register(taskName) {
                this.group = group
                this.description = description
                // Depend on the aggregate lifecycle task, if any
                allTask?.let { dependsOn(it) }
            }
        }
    }
}