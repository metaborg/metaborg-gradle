package org.metaborg.convention

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

/** Configuration for the root project convention. */
open class RootProjectConventionExtension @Inject constructor(
    /** The Gradle object factory. */
    objects: ObjectFactory,
) {

    /** Whether to add the aggregate `assembleAll`, `buildAll`, `checkAll`, and `cleanAll` lifecycle tasks. */
    val registerLifecycleTasks: Property<Boolean> = objects.property(Boolean::class.java)
        .convention(true)
    /** Whether to add the aggregate `publishAll`, `publishAllToMavenLocal` lifecycle tasks. */
    val registerPublishTasks: Property<Boolean> = objects.property(Boolean::class.java)
        .convention(false)

    /** Whether to register stub `assemble`, `build`, `check`, and `clean` lifecycle tasks that depend on their `*All` counterparts, if not already defined. */
    val registerStubLifecycleTasks: Property<Boolean> = objects.property(Boolean::class.java)
        .convention(registerLifecycleTasks)
    /** Whether to add stub `publish` and `publishToMavenLocal` tasks that depend on their `*All` counterparts, if not already defined. */
    val registerStubPublishTasks: Property<Boolean> = objects.property(Boolean::class.java)
        .convention(registerPublishTasks)
}