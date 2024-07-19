package org.metaborg.convention

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

/** Configuration for the root project convention. */
open class RootProjectConventionExtension @Inject constructor(
    /** The Gradle object factory. */
    objects: ObjectFactory,
) {
    /** Suffix for all task names. */
    val taskNameSuffix: Property<String> = objects.property(String::class.java)
        .convention("")

    /** The name of the `assemble` task. */
    val assembleTaskName: Property<String> = objects.property(String::class.java)
        .convention(taskNameSuffix.map { "assemble$it"} )
    /** The name of the `build` task. */
    val buildTaskName: Property<String> = objects.property(String::class.java)
        .convention(taskNameSuffix.map { "build$it"} )
    /** The name of the `clean` task. */
    val cleanTaskName: Property<String> = objects.property(String::class.java)
        .convention(taskNameSuffix.map { "clean$it"} )
    /** The name of the `publish` task. */
    val publishTaskName: Property<String> = objects.property(String::class.java)
        .convention(taskNameSuffix.map { "publish$it"} )
    /** The name of the `publishToMavenLocal` task. */
    val publishToMavenLocalTaskName: Property<String> = objects.property(String::class.java)
        .convention(taskNameSuffix.map { "publish${it}ToMavenLocal"} )
    /** The name of the `check` task. */
    val checkTaskName: Property<String> = objects.property(String::class.java)
        .convention(taskNameSuffix.map { "check$it"} )
    /** The name of the `test` task. */
    val testTaskName: Property<String> = objects.property(String::class.java)
        .convention(taskNameSuffix.map { "test$it"} )
    /** The name of the `tasks` task. */
    val tasksTaskName: Property<String> = objects.property(String::class.java)
        .convention("allTasks")
}