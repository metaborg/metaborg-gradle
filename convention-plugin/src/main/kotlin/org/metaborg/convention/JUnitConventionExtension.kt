package org.metaborg.convention

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

/** Configuration for the JUnit convention. */
open class JUnitConventionExtension @Inject constructor(
    /** The Gradle object factory. */
    objects: ObjectFactory,
) {
    /** The name of the version catalog to use for the JUnit dependency. */
    val versionCatalogName: Property<String> = objects.property(String::class.java)
        .convention("libs")
    /** The alias of the JUnit dependency in the version catalog. */
    val jUnitAlias: Property<String> = objects.property(String::class.java)
        .convention("junit")
    /** The default JUnit dependency to use if the version catalog dependency cannot be found. */
    val jUnitDependency: Property<Any> = objects.property(Any::class.java)
        .convention("org.junit.jupiter:junit-jupiter:5.10.3")
}
