package org.metaborg.convention

import org.gradle.api.provider.Provider

private fun <A0, A1> zipId(p0: Provider<A0>, p1: Provider<A1>): Provider<Pair<A0, A1>> =
    p0.zip(p1) { a0, a1 -> Pair(a0, a1) }

// Utility functions that map multiple Providers to a single Provider through a transformer function.

@JvmName("alsoWith")
fun <A0, A1, R : Any> Provider<A0>.with(p1: Provider<A1>, transformer: (A0, A1) -> R): Provider<R> =
    with(this, p1, transformer)

@JvmName("alsoWith")
fun <A0, A1, A2, R : Any> Provider<A0>.with(p1: Provider<A1>, p2: Provider<A2>, transformer: (A0, A1, A2) -> R): Provider<R> =
    with(this, p1, p2, transformer)

@JvmName("alsoWith")
fun <A0, A1, A2, A3, R : Any> Provider<A0>.with(p1: Provider<A1>, p2: Provider<A2>, p3: Provider<A3>, transformer: (A0, A1, A2, A3) -> R): Provider<R> =
    with(this, p1, p2, p3, transformer)


fun <A0, R : Any> with(p0: Provider<A0>, transformer: (A0) -> R): Provider<R> =
    p0.map { a0 -> transformer(a0) }

fun <A0, A1, R : Any> with(p0: Provider<A0>, p1: Provider<A1>, transformer: (A0, A1) -> R): Provider<R> =
    p0.zip(p1) { a0, a1 -> transformer(a0, a1) }

fun <A0, A1, A2, R : Any> with(p0: Provider<A0>, p1: Provider<A1>, p2: Provider<A2>, transformer: (A0, A1, A2) -> R): Provider<R> =
    p0.zip(zipId(p1, p2)) { a0, (a1, a2) -> transformer(a0, a1, a2) }

fun <A0, A1, A2, A3, R : Any> with(p0: Provider<A0>, p1: Provider<A1>, p2: Provider<A2>, p3: Provider<A3>, transformer: (A0, A1, A2, A3) -> R): Provider<R> =
    p0.zip(zipId(p1, zipId(p2, p3))) { a0, (a1, a23) -> val (a2, a3) = a23; transformer(a0, a1, a2, a3) }
