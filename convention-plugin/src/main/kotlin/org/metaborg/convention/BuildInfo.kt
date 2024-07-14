package org.metaborg.convention

import java.time.Instant
import java.time.OffsetDateTime
import java.time.format.DateTimeParseException
import java.util.*

/** Information about the build. */
object BuildInfo {

    private val properties = Properties()

    init {
        val versionPropertiesResourcePath = "/org/metaborg/convention/version.properties"
        BuildInfo::class.java.getResourceAsStream(versionPropertiesResourcePath).use { inputStream ->
            properties.load(checkNotNull(inputStream))
        }
    }

    /** The current version. */
    val version: String get() = checkNotNull(properties.getProperty("version"))
    /** The most recent release version. */
    val releaseVersion: String get() = checkNotNull(properties.getProperty("release-version"))
    /** The commit ID. */
    val commit: String get() = checkNotNull(properties.getProperty("short-revision"))

    /** The application build time, as an [Instant]; or `null` when it could not be parsed. */
    val buildTime: Instant? get() = tryParseOffsetDateTime(buildTimeString)
    /** The application build time, as a string. */
    val buildTimeString: String get() = checkNotNull(properties.getProperty("build-time"))


    /**
     * Attempts to parse the given string as an offset date/time.
     *
     * @param s the string to parse
     * @return the resulting [Instant]; or `null` if parsing failed
     */
    private fun tryParseOffsetDateTime(s: String?): Instant? {
        if (s == null) return null
        return try {
            OffsetDateTime.parse(s).toInstant()
        } catch (ex: DateTimeParseException) {
            null
        }
    }
}
