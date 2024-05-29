package org.metaborg.gradle

/** Specifies a developer. */
data class Developer(
    /** The developer's ID or username. */
    val id: String,
    /** The developer's full name. */
    val name: String,
    /** The developer's email address. */
    val email: String,
)
