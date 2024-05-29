package org.metaborg.gradle

import org.gradle.api.provider.Provider

private fun <A0, A1> zipId(p0: Provider<A0>, p1: Provider<A1>): Provider<Pair<A0, A1>> =
    p0.zip(p1) { a0, a1 -> Pair(a0, a1) }

fun <A0, A1, R> Provider<A0>.with(p1: Provider<A1>, transformer: (A0, A1) -> R): Provider<R> =
    this.zip(p1) { a0, a1 -> transformer(a0, a1) }

fun <A0, A1, A2, R> Provider<A0>.with(p1: Provider<A1>, p2: Provider<A2>, transformer: (A0, A1, A2) -> R): Provider<R> =
    this.zip(zipId(p1, p2)) { a0, (a1, a2) -> transformer(a0, a1, a2) }

fun <A0, A1, A2, A3, R> Provider<A0>.with(p1: Provider<A1>, p2: Provider<A2>, p3: Provider<A3>, transformer: (A0, A1, A2, A3) -> R): Provider<R> =
    this.zip(zipId(p1, zipId(p2, p3))) { a0, (a1, a23) -> val (a2, a3) = a23; transformer(a0, a1, a2, a3) }
