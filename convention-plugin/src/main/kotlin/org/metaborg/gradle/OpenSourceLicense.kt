package org.metaborg.gradle

/** Specifies the open source license. */
enum class OpenSourceLicense(
    /** The unique identifier of the license. */
    val id: String,
    /** The license URL. */
    val url: String,
) {
    // From: https://spdx.org/licenses/

    /** Apache 2 */
    Apache2("Apache-2.0", "https://www.apache.org/licenses/LICENSE-2.0.txt"),
}