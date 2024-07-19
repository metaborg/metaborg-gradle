package org.metaborg.convention

import org.gradle.api.Project
import org.gradle.api.UnknownDomainObjectException
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType

private fun <A0, A1> zipId(p0: Provider<A0>, p1: Provider<A1>): Provider<Pair<A0, A1>> =
    p0.zip(p1) { a0, a1 -> Pair(a0, a1) }

// Utility functions that map multiple Providers to a single Provider through a transformer function.

@JvmName("alsoWith")
internal fun <A0, A1, R : Any> Provider<A0>.with(p1: Provider<A1>, transformer: (A0, A1) -> R): Provider<R> =
    with(this, p1, transformer)

@JvmName("alsoWith")
internal fun <A0, A1, A2, R : Any> Provider<A0>.with(p1: Provider<A1>, p2: Provider<A2>, transformer: (A0, A1, A2) -> R): Provider<R> =
    with(this, p1, p2, transformer)

@JvmName("alsoWith")
internal fun <A0, A1, A2, A3, R : Any> Provider<A0>.with(p1: Provider<A1>, p2: Provider<A2>, p3: Provider<A3>, transformer: (A0, A1, A2, A3) -> R): Provider<R> =
    with(this, p1, p2, p3, transformer)


internal fun <A0, R : Any> with(p0: Provider<A0>, transformer: (A0) -> R): Provider<R> =
    p0.map { a0 -> transformer(a0) }

internal fun <A0, A1, R : Any> with(p0: Provider<A0>, p1: Provider<A1>, transformer: (A0, A1) -> R): Provider<R> =
    p0.zip(p1) { a0, a1 -> transformer(a0, a1) }

internal fun <A0, A1, A2, R : Any> with(p0: Provider<A0>, p1: Provider<A1>, p2: Provider<A2>, transformer: (A0, A1, A2) -> R): Provider<R> =
    p0.zip(zipId(p1, p2)) { a0, (a1, a2) -> transformer(a0, a1, a2) }

internal fun <A0, A1, A2, A3, R : Any> with(p0: Provider<A0>, p1: Provider<A1>, p2: Provider<A2>, p3: Provider<A3>, transformer: (A0, A1, A2, A3) -> R): Provider<R> =
    p0.zip(zipId(p1, zipId(p2, p3))) { a0, (a1, a23) -> val (a2, a3) = a23; transformer(a0, a1, a2, a3) }


/**
 * Gets the project's version catalog with the specified name, if it exists.
 *
 * @param catalogName The name of the version catalog.
 * @return The [VersionCatalog]; or `null` if not found.
 */
internal fun Project.versionCatalog(catalogName: String = "libs"): VersionCatalog? = try {
    extensions.getByType<VersionCatalogsExtension>().named(catalogName)
} catch (e: UnknownDomainObjectException) {
    null
}

/**
 * Gets a library with the specified alias from the specified version catalog of the project,
 * or if not found, returns the provided dependency notation instead.
 *
 * For example, to get the JUnit dependency, call this function as:
 *
 * ```kotlin
 * getLibrary("junit", "org.junit.jupiter:junit-jupiter-api:5.7.0")
 * ```
 *
 * Either this finds a dependency with alias `"junit"` in the version catalog,
 * or returns the given dependency notation if not found. Note that a platform or
 * explicit version restriction may still modify the dependency.
 */
internal fun Project.getLibrary(alias: String, dependencyNotation: Any, catalog: String = "libs"): Any {
    return versionCatalog(catalog)?.findLibrary(alias)?.map { it.getOrNull() }?.orElse(null) ?: dependencyNotation
}