plugins {
    `java-library`
    id("org.metaborg.convention.java")
    id("org.metaborg.convention.junit")
    id("org.metaborg.convention.maven-publish")
}

group = "org.metaborg.example"
description = "A Metaborg Gradle convention plugin example project."

javaConvention {
    javaVersion.set(JavaLanguageVersion.of(17))     // Optional
}

// Required, either here or on the root project
mavenPublishConvention {
    repoOwner.set("metaborg")
    repoName.set("convention-plugin-example")
}

// Create a publication, if not created implicitly already by a plugin
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

repositories {
    maven("https://artifacts.metaborg.org/content/groups/public/")
    maven("https://repo.gradle.org/gradle/libs-releases") // Required for Gradle tooling API.
}

dependencies {
    implementation(platform(libs.metaborg.platform))

    // Metaborg Git (https://github.com/metaborg/metaborg-git)
    api(libs.metaborg.git)

    // Metaborg Common (https://github.com/metaborg/common)
    api(libs.metaborg.common)

    // Metaborg Log (https://github.com/metaborg/log)
    api(libs.metaborg.log.api)
    api(libs.metaborg.log.backend.logback)
    api(libs.metaborg.log.backend.slf4j)
    api(libs.metaborg.log.dagger)

    // Metaborg PIE (https://github.com/metaborg/pie)
    api(libs.metaborg.pie.api)
    api(libs.metaborg.pie.api.test)
    api(libs.metaborg.pie.dagger)
    api(libs.metaborg.pie.graph)
//    api(libs.metaborg.pie.lang)                   // TODO: spoofax-language component
    api(libs.metaborg.pie.lang.runtime.java)
    api(libs.metaborg.pie.lang.runtime.kotlin)
    api(libs.metaborg.pie.lang.test)
    api(libs.metaborg.pie.runtime)
    api(libs.metaborg.pie.runtime.test)
    api(libs.metaborg.pie.serde.fst)
    api(libs.metaborg.pie.serde.kryo)
    api(libs.metaborg.pie.share.coroutine)
    api(libs.metaborg.pie.store.lmdb)
    api(libs.metaborg.pie.task.archive)
    api(libs.metaborg.pie.task.java)
    api(libs.metaborg.pie.task.java.ecj)
    api(libs.metaborg.pie.taskdefs.guice)

    // Metaborg Resource (https://github.com/metaborg/resource)
    api(libs.metaborg.resource.api)
    api(libs.metaborg.resource.dagger)

    // Spoofax 3/PIE (https://github.com/metaborg/spoofax-pie)
    api(libs.spoofax3.aterm.common)
    api(libs.spoofax3.cfg)
//    api(libs.spoofax3.cfg.cli)                        // Not released
//    api(libs.spoofax3.cfg.eclipse)                    // Not released
//    api(libs.spoofax3.cfg.intellij)                   // Not released
//    api(libs.spoofax3.cfg.spoofax2)                   // TODO: spoofax-language component
    api(libs.spoofax3.cli)
    api(libs.spoofax3.compiler)
//    api(libs.spoofax3.compiler.eclipsebundle) // TODO: Needs org.eclipse.core.runtime
    api(libs.spoofax3.compiler.gradle)
    api(libs.spoofax3.compiler.gradle.spoofax2)
    api(libs.spoofax3.compiler.interfaces)
    api(libs.spoofax3.compiler.spoofax2)
    api(libs.spoofax3.compiler.spoofax2.dagger)
    api(libs.spoofax3.constraint.common)
    api(libs.spoofax3.constraint.pie)
    api(libs.spoofax3.core)
    api(libs.spoofax3.dynamix)
//    api(libs.spoofax3.dynamix.cli)                    // Not released
//    api(libs.spoofax3.dynamix.eclipse)                // Not released
//    api(libs.spoofax3.dynamix.intellij)               // Not released
//    api(libs.spoofax3.dynamix.spoofax2)               // TODO: spoofax-language component
//    api(libs.spoofax3.eclipse)                        // TODO: Needs org.eclipse.core.runtime
    api(libs.spoofax3.esv)
//    api(libs.spoofax3.esv.cli)                        // Not released
    api(libs.spoofax3.esv.common)
//    api(libs.spoofax3.esv.eclipse)                    // Not released
//    api(libs.spoofax3.esv.intellij)                   // Not released
    api(libs.spoofax3.gpp)
//    api(libs.spoofax3.gpp.eclipse)                    // TODO: Needs org.eclipse.core.runtime
    api(libs.spoofax3.intellij)
    api(libs.spoofax3.jsglr.common)
    api(libs.spoofax3.jsglr.pie)
    api(libs.spoofax3.jsglr1.common)
    api(libs.spoofax3.jsglr2.common)
    api(libs.spoofax3.libspoofax2)
//    api(libs.spoofax3.libspoofax2.eclipse)            // Not released
    api(libs.spoofax3.libstatix)
//    api(libs.spoofax3.libstatix.eclipse)              // Not released
    api(libs.spoofax3.lwb.compiler)
    api(libs.spoofax3.lwb.compiler.gradle)
    api(libs.spoofax3.lwb.dynamicloading)
    api(libs.spoofax3.nabl2.common)
    api(libs.spoofax3.resource)
    api(libs.spoofax3.sdf3)
//    api(libs.spoofax3.sdf3.cli)                       // Not released
//    api(libs.spoofax3.sdf3.eclipse)                   // Not released
    api(libs.spoofax3.sdf3.extdynamix)
//    api(libs.spoofax3.sdf3.extdynamix.eclipse)        // Not released
//    api(libs.spoofax3.sdf3.extdynamix.spoofax2)       // TODO: spoofax-language component
    api(libs.spoofax3.sdf3.extstatix)
//    api(libs.spoofax3.sdf3.extstatix.eclipse)         // Not released
//    api(libs.spoofax3.sdf3.intellij)                  // Not released
    api(libs.spoofax3.spoofax.common)
    api(libs.spoofax3.spoofax2.common)
    api(libs.spoofax3.spt)
    api(libs.spoofax3.spt.api)
//    api(libs.spoofax3.spt.cli)                        // Not released
    api(libs.spoofax3.spt.dynamicloading)
//    api(libs.spoofax3.spt.eclipse)                    // Not released
//    api(libs.spoofax3.spt.intellij)                   // Not released
    api(libs.spoofax3.statix)
//    api(libs.spoofax3.statix.cli)                     // Not released
    api(libs.spoofax3.statix.codecompletion)
    api(libs.spoofax3.statix.codecompletion.pie)
    api(libs.spoofax3.statix.common)
//    api(libs.spoofax3.statix.eclipse)                 // Not released
//    api(libs.spoofax3.statix.intellij)                // Not released
    api(libs.spoofax3.statix.multilang)
//    api(libs.spoofax3.statix.multilang.eclipse)       // TODO: Needs org.eclipse.core.runtime
    api(libs.spoofax3.statix.pie)
    api(libs.spoofax3.stratego)
//    api(libs.spoofax3.stratego.cli)                   // Not released
    api(libs.spoofax3.stratego.common)
//    api(libs.spoofax3.stratego.eclipse)               // Not released
//    api(libs.spoofax3.stratego.intellij)              // Not released
    api(libs.spoofax3.stratego.pie)
    api(libs.spoofax3.strategolib)
//    api(libs.spoofax3.strategolib.eclipse)            // TODO: Needs org.eclipse.core.runtime
    api(libs.spoofax3.tego.runtime)
    api(libs.spoofax3.test)
//    api(libs.spoofax3.tooling.eclipsebundle)          // TODO: Needs org.eclipse.core.runtime
    api(libs.spoofax3.transform.pie)


    // Dynsem (https://github.com/metaborg/dynsem)
//    api(libs.spoofax2.dynsem.lang)                    // TODO: spoofax-language component

    // ESV (https://github.com/metaborg/esv)
//    api(libs.esv.lang)                                // TODO: spoofax-language component

//    api(libs.spoofax2.esv.lang)                       // TODO: spoofax-language component

    // Flowspec (https://github.com/metaborg/flowspec)
    api(libs.spoofax2.flowspec.runtime)

    // JSGLR (https://github.com/metaborg/jsglr)
    api(libs.interpreter.library.jsglr)
    api(libs.jsglr)
    api(libs.jsglr.shared)
    api(libs.jsglr2)

    api(libs.spoofax2.interpreter.library.jsglr)
    api(libs.spoofax2.jsglr)
    api(libs.spoofax2.jsglr.shared)
    api(libs.spoofax2.jsglr2)
    api(libs.spoofax2.makepermissive)

    // MB Exec (https://github.com/metaborg/mb-exec)
    api(libs.interpreter.core)
    api(libs.interpreter.library.java)
    api(libs.interpreter.library.xml)
    api(libs.metaborg.util)
//    api(libs.util.vfs2)                               // TODO: Not released yet

    api(libs.spoofax2.interpreter.core)
    api(libs.spoofax2.interpreter.library.java)
    api(libs.spoofax2.interpreter.library.xml)
    api(libs.spoofax2.metaborg.util)
//    api(libs.spoofax2.util.vfs2)                      // TODO: Not released yet

    // MB Rep (https://github.com/metaborg/mb-rep)
    api(libs.interpreter.library.index)
    api(libs.spoofax.terms)
    api(libs.spoofax2.interpreter.library.index)
    api(libs.spoofax2.spoofax.terms)

    // NaBL (https://github.com/metaborg/nabl)
    api(libs.nabl.praffrayi)
    api(libs.nabl.renaming.java)
    api(libs.nabl.scopegraph)
//    api(libs.nabl2.lang)                              // TODO: spoofax-language component
//    api(libs.nabl2.runtime)                           // TODO: spoofax-language component
//    api(libs.nabl2.shared)                            // TODO: spoofax-language component
    api(libs.nabl2.solver)
    api(libs.nabl2.terms)
    api(libs.statix.generator)
//    api(libs.statix.lang)                             // TODO: spoofax-language component
//    api(libs.statix.runtime)                          // TODO: spoofax-language component
    api(libs.statix.solver)

//    api(libs.spoofax2.nabl.lang)                      // TODO: spoofax-language component
    api(libs.spoofax2.nabl.praffrayi)
    api(libs.spoofax2.nabl.renaming.java)
    api(libs.spoofax2.nabl.scopegraph)
    api(libs.spoofax2.nabl2.extdynsem)
//    api(libs.spoofax2.nabl2.lang)                     // TODO: spoofax-language component
//    api(libs.spoofax2.nabl2.runtime)                  // TODO: spoofax-language component
//    api(libs.spoofax2.nabl2.shared)                   // TODO: spoofax-language component
    api(libs.spoofax2.nabl2.solver)
    api(libs.spoofax2.nabl2.terms)
    api(libs.spoofax2.statix.generator)
//    api(libs.spoofax2.statix.lang)                    // TODO: spoofax-language component
//    api(libs.spoofax2.statix.runtime)                 // TODO: spoofax-language component
    api(libs.spoofax2.statix.solver)
//    api(libs.spoofax2.ts.lang)                        // TODO: spoofax-language component

    // Runtime Libraries (https://github.com/metaborg/runtime-libraries)
    api(libs.spoofax2.meta.lib.analysis)
    api(libs.spoofax2.metaborg.runtime.task)

    // SDF (https://github.com/metaborg/sdf)
    api(libs.parsetable)
    api(libs.sdf2parenthesize)
    api(libs.sdf2table)
//    api(libs.sdf3.extstatix)                          // TODO: spoofax-language component
//    api(libs.sdf3.lang)                               // TODO: spoofax-language component

    api(libs.spoofax2.parsetable)
    api(libs.spoofax2.sdf2parenthesize)
    api(libs.spoofax2.sdf2table)
//    api(libs.spoofax2.sdf3.extstatix)                 // TODO: spoofax-language component
//    api(libs.spoofax2.sdf3.lang)                      // TODO: spoofax-language component

    // Spoofax Core (https://github.com/metaborg/spoofax)
//    api(libs.meta.lib.spoofax)                        // TODO: spoofax-language component
    api(libs.metaborg.core)
    api(libs.metaborg.core.test)
    api(libs.metaborg.meta.core)
    api(libs.spoofax.core)
    api(libs.spoofax.meta.core)
    api(libs.spoofax.nativebundle)

//    api(libs.spoofax2.meta.lib.spoofax)               // TODO: spoofax-language component
    api(libs.spoofax2.metaborg.core)
    api(libs.spoofax2.metaborg.core.test)
    api(libs.spoofax2.metaborg.meta.core)
    api(libs.spoofax2.spoofax.core)
    api(libs.spoofax2.spoofax.meta.core)
    api(libs.spoofax2.spoofax.nativebundle)

    // Spoofax Gradle (https://github.com/metaborg/spoofax.gradle)
    api(libs.spoofax3.gradle)

    // SPT (https://github.com/metaborg/spt)
    api(libs.mbt.core)
    api(libs.spt.core)
//    api(libs.spt.lang)                                // TODO: spoofax-language component

    api(libs.spoofax2.mbt.core)
    api(libs.spoofax2.spt.core)
//    api(libs.spoofax2.spt.lang)                       // TODO: spoofax-language component

    // Stratego (https://github.com/metaborg/stratego)
//    api(libs.gpp.lang)                                // TODO: spoofax-language component
    api(libs.stratego.build)
    api(libs.stratego.build.spoofax2)
//    api(libs.stratego.lang)                           // TODO: spoofax-language component
//    api(libs.stratego2.lang)                          // TODO: spoofax-language component
//    api(libs.strategolib)                             // TODO: spoofax-language component

//    api(libs.spoofax2.gpp.lang)                       // TODO: spoofax-language component
    api(libs.spoofax2.stratego.build)
    api(libs.spoofax2.stratego.build.spoofax2)
//    api(libs.spoofax2.stratego.lang)                  // TODO: spoofax-language component
//    api(libs.spoofax2.stratego2.lang)                 // TODO: spoofax-language component
//    api(libs.spoofax2.strategolib)                    // TODO: spoofax-language component

    // Stratego XT (https://github.com/metaborg/strategoxt)
    api(libs.strategoxt.strj)

    api(libs.spoofax2.strategoxt.jar)
    api(libs.spoofax2.strategoxt.minjar)
    api(libs.spoofax2.strategoxt.strj)


    // ---- // ---- // ---- // ---- // ---- // ---- // ---- // ---- // ---- // ---- // ---- // ---- // ---- // ---- // ---- // ---- // ---- // ---- // ---- //

    // Kotlin
    api(libs.kotlinx.coroutines.core)

    // Collections
    api(libs.capsule)

    // Dependency Injection
    api(libs.guice)
    api(libs.dagger)
    api(libs.dagger.compiler)

    // Annotations & Annotation Processing
    api(libs.checkerframework.android)
    api(libs.derive4j)
    api(libs.derive4j.annotation)
    api(libs.immutables.serial)
    api(libs.immutables.value)
    api(libs.immutables.value.annotations)
    api(libs.jakarta.annotation)
    api(libs.jakarta.inject)
    api(libs.javax.inject)
    api(libs.jsr305)

    // Maven
    api(libs.bnd.gradle)
    api(libs.maven.resolver.api)
    api(libs.maven.resolver.connector.basic)
    api(libs.maven.resolver.impl)
    api(libs.maven.resolver.provider)
    api(libs.maven.resolver.transport.file)

    // Gradle
    api(libs.coronium)
    api(libs.gradle.develocityAdapter)
    api(libs.gradle.develocityPlugin)
    api(libs.gradle.foojayPlugin)
    api(libs.gradle.intellijPlugin)
    api(libs.gradle.tooling.api)

    // ECJ
    api(libs.eclipse.jdt.compiler.apt)
    api(libs.eclipse.jdt.compiler.tool)
    api(libs.eclipse.jdt.core)

    // CLI
    api(libs.picocli)
    api(libs.picocli.codegen)

    // Logging
    api(libs.jcl.over.slf4j)
    api(libs.logback)
    api(libs.logback.core)
    api(libs.slf4j.api)
    api(libs.slf4j.nop)
    api(libs.slf4j.simple)

    // Data Formats
    api(libs.commons.configuration2.jackson)
    api(libs.jackson.annotations)
    api(libs.jackson.core)
    api(libs.jackson.databind)
    api(libs.jackson.dataformat.yaml)
    api(libs.snakeyaml)

    // Build
    api(libs.ant)
    api(libs.ant.contrib)
    api(libs.jmustache)
    api(libs.mustache.compiler)
    api(libs.pluto)
    api(libs.pluto.build.java)

    // Utils
    api(libs.classgraph)
    api(libs.commons.compress)
    api(libs.commons.configuration2)
    api(libs.commons.io)
    api(libs.commons.lang3)
    api(libs.commons.math3)
    api(libs.commons.vfs2)
    api(libs.failureaccess)
    api(libs.fst)
    api(libs.guava)
    api(libs.jimfs)
    api(libs.kryo)
    api(libs.lmdbjava)
    api(libs.opencsv)
    api(libs.rxjava)

    // Testing
    api(libs.equalsverifier)
    api(libs.jmh.core)
    api(libs.jmh.generator.annprocess)
    api(libs.junit)
    api(libs.junit.api)
    api(libs.junit.params)
    api(libs.junit.vintage)
    api(libs.junit4)
    api(libs.junit4.benchmarks)
    api(libs.kotest)
    api(libs.kotest.assertions)
    api(libs.kotest.datatest)
    api(libs.kotest.property)
    api(libs.mockito.kotlin)
}

afterEvaluate {
    // Do not try to publish this project
    tasks.withType<AbstractPublishToMaven>().configureEach { enabled = false }
}
