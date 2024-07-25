package org.metaborg.repoman.templates

/** Template info for the `README.md` file. */
data class ReadmeTemplate(
    /** The human-readable name of the repository. For example: `"Metaborg Resource"` */
    val title: String,
    /** A short one-line description of the repository. For example: `"A utility library for working with resources."` */
    val description: Markdown,
) : Template("README.md.kte")

