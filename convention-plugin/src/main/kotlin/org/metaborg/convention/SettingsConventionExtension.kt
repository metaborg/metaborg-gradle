package org.metaborg.convention

import org.gradle.api.provider.Property

/** Configuration for the settings build convention. */
interface SettingsConventionExtension {

    /**
     * Sets the convention (default values) for the configuration extension.
     */
    fun setConvention() {
    }
}