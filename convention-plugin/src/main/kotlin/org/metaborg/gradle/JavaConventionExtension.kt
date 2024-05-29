package org.metaborg.gradle

import org.gradle.api.provider.Property
import org.gradle.jvm.toolchain.JavaLanguageVersion

/** Configuration for the Java convention. */
interface JavaConventionExtension {
    /** The Java version to compile to. */
    val javaVersion: Property<JavaLanguageVersion>

    /**
     * Sets the convention (default values) for the configuration extension.
     */
    fun setConvention() {
        javaVersion.convention(JavaLanguageVersion.of(11))
    }
}