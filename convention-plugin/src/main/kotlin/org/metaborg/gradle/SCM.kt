package org.metaborg.gradle

/** Specifies a source control management system. */
enum class SCM(
    /** The HTTP URL for the SCM. */
    val httpUrlFormat: String,
    /** The SCM URL for the SCM. */
    val scmUrlFormat: String,
) {
    /** GitHub */
    GitHub("https://github.com/%s/%s", "scm:git@github.com:%s/%s.git"),
}