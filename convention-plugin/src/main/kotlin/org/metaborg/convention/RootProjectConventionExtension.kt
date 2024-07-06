package org.metaborg.convention

import org.gradle.api.provider.Property

/** Configuration for the root project convention. */
interface RootProjectConventionExtension {

    /** Suffix for all task names. */
    val taskNameSuffix: Property<String>

    /** The name of the `assemble` task. */
    val assembleTaskName: Property<String>
    /** The name of the `build` task. */
    val buildTaskName: Property<String>
    /** The name of the `clean` task. */
    val cleanTaskName: Property<String>
    /** The name of the `publish` task. */
    val publishTaskName: Property<String>
    /** The name of the `publishToMavenLocal` task. */
    val publishToMavenLocalTaskName: Property<String>
    /** The name of the `check` task. */
    val checkTaskName: Property<String>
    /** The name of the `test` task. */
    val testTaskName: Property<String>
    /** The name of the `tasks` task. */
    val tasksTaskName: Property<String>

    /**
     * Sets the convention (default values) for the configuration extension.
     */
    fun setConvention() {
        taskNameSuffix.convention("")
        assembleTaskName.convention(taskNameSuffix.map { "assemble$it"} )
        buildTaskName.convention(taskNameSuffix.map { "build$it"} )
        cleanTaskName.convention(taskNameSuffix.map { "clean$it"} )
        publishTaskName.convention(taskNameSuffix.map { "publish$it"} )
        publishToMavenLocalTaskName.convention(taskNameSuffix.map { "publish${it}ToMavenLocal"} )
        checkTaskName.convention(taskNameSuffix.map { "check$it"} )
        testTaskName.convention(taskNameSuffix.map { "test$it"} )
        tasksTaskName.convention("allTasks")
    }
}