package org.metaborg.convention

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.jvm.toolchain.JavaLanguageVersion
import javax.inject.Inject

/** Configuration for the Java convention. */
open class JavaConventionExtension @Inject constructor(
    /** The Gradle object factory. */
    objects: ObjectFactory,
) {
    /** The Java version to compile to. */
    val javaVersion: Property<JavaLanguageVersion> = objects.property(JavaLanguageVersion::class.java)
        .convention(JavaLanguageVersion.of(11))
}
