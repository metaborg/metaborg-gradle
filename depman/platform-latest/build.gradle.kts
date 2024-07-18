plugins {
    `java-platform`
    `maven-publish`
    signing
    id("org.metaborg.convention.maven-publish")
}

group = "org.metaborg"
description = "Spoofax 3 Platform (Latest Releases)"

dependencies {
    constraints {
        // NOTE: Also update part of libs.versions.toml

        // Metaborg Common (https://github.com/metaborg/common)
        api(libs.metaborg.common) { version { require("latest.release") } }

        // Metaborg Log (https://github.com/metaborg/log)
        api(libs.metaborg.log.api) { version { require("latest.release") } }
        api(libs.metaborg.log.backend.logback) { version { require("latest.release") } }
        api(libs.metaborg.log.backend.slf4j) { version { require("latest.release") } }
        api(libs.metaborg.log.dagger) { version { require("latest.release") } }

        // Metaborg PIE (https://github.com/metaborg/pie)
        api(libs.metaborg.pie.api) { version { require("latest.release") } }
        api(libs.metaborg.pie.api.test) { version { require("latest.release") } }
        api(libs.metaborg.pie.dagger) { version { require("latest.release") } }
        api(libs.metaborg.pie.graph) { version { require("latest.release") } }
        api(libs.metaborg.pie.lang) { version { require("latest.release") } }
        api(libs.metaborg.pie.lang.runtime.java) { version { require("latest.release") } }
        api(libs.metaborg.pie.lang.runtime.kotlin) { version { require("latest.release") } }
        api(libs.metaborg.pie.lang.test) { version { require("latest.release") } }
        api(libs.metaborg.pie.runtime) { version { require("latest.release") } }
        api(libs.metaborg.pie.runtime.test) { version { require("latest.release") } }
        api(libs.metaborg.pie.serde.fst) { version { require("latest.release") } }
        api(libs.metaborg.pie.serde.kryo) { version { require("latest.release") } }
        api(libs.metaborg.pie.share.coroutine) { version { require("latest.release") } }
        api(libs.metaborg.pie.store.lmdb) { version { require("latest.release") } }
        api(libs.metaborg.pie.task.archive) { version { require("latest.release") } }
        api(libs.metaborg.pie.task.java) { version { require("latest.release") } }
        api(libs.metaborg.pie.task.java.ecj) { version { require("latest.release") } }
        api(libs.metaborg.pie.taskdefs.guice) { version { require("latest.release") } }

        // Metaborg Resource (https://github.com/metaborg/resource)
        api(libs.metaborg.resource.api) { version { require("latest.release") } }
        api(libs.metaborg.resource.dagger) { version { require("latest.release") } }

        // Spoofax 3/PIE (https://github.com/metaborg/spoofax-pie)
        api(libs.spoofax3.aterm.common) { version { require("latest.release") } }
        api(libs.spoofax3.cfg) { version { require("latest.release") } }
        api(libs.spoofax3.cfg.cli) { version { require("latest.release") } }
        api(libs.spoofax3.cfg.eclipse) { version { require("latest.release") } }
        api(libs.spoofax3.cfg.intellij) { version { require("latest.release") } }
        api(libs.spoofax3.cfg.spoofax2) { version { require("latest.release") } }
        api(libs.spoofax3.cli) { version { require("latest.release") } }
        api(libs.spoofax3.compiler) { version { require("latest.release") } }
        api(libs.spoofax3.compiler.eclipsebundle) { version { require("latest.release") } }
        api(libs.spoofax3.compiler.gradle) { version { require("latest.release") } }
        api(libs.spoofax3.compiler.gradle.spoofax2) { version { require("latest.release") } }
        api(libs.spoofax3.compiler.interfaces) { version { require("latest.release") } }
        api(libs.spoofax3.compiler.spoofax2) { version { require("latest.release") } }
        api(libs.spoofax3.compiler.spoofax2.dagger) { version { require("latest.release") } }
        api(libs.spoofax3.constraint.common) { version { require("latest.release") } }
        api(libs.spoofax3.constraint.pie) { version { require("latest.release") } }
        api(libs.spoofax3.core) { version { require("latest.release") } }
        api(libs.spoofax3.dynamix) { version { require("latest.release") } }
        api(libs.spoofax3.dynamix.cli) { version { require("latest.release") } }
        api(libs.spoofax3.dynamix.eclipse) { version { require("latest.release") } }
        api(libs.spoofax3.dynamix.intellij) { version { require("latest.release") } }
        api(libs.spoofax3.dynamix.spoofax2) { version { require("latest.release") } }
        api(libs.spoofax3.eclipse) { version { require("latest.release") } }
        api(libs.spoofax3.esv) { version { require("latest.release") } }
        api(libs.spoofax3.esv.cli) { version { require("latest.release") } }
        api(libs.spoofax3.esv.common) { version { require("latest.release") } }
        api(libs.spoofax3.esv.eclipse) { version { require("latest.release") } }
        api(libs.spoofax3.esv.intellij) { version { require("latest.release") } }
        api(libs.spoofax3.gpp) { version { require("latest.release") } }
        api(libs.spoofax3.gpp.eclipse) { version { require("latest.release") } }
        api(libs.spoofax3.intellij) { version { require("latest.release") } }
        api(libs.spoofax3.jsglr.common) { version { require("latest.release") } }
        api(libs.spoofax3.jsglr.pie) { version { require("latest.release") } }
        api(libs.spoofax3.jsglr1.common) { version { require("latest.release") } }
        api(libs.spoofax3.jsglr2.common) { version { require("latest.release") } }
        api(libs.spoofax3.libspoofax2) { version { require("latest.release") } }
        api(libs.spoofax3.libspoofax2.eclipse) { version { require("latest.release") } }
        api(libs.spoofax3.libstatix) { version { require("latest.release") } }
        api(libs.spoofax3.libstatix.eclipse) { version { require("latest.release") } }
        api(libs.spoofax3.lwb.compiler) { version { require("latest.release") } }
        api(libs.spoofax3.lwb.compiler.gradle) { version { require("latest.release") } }
        api(libs.spoofax3.lwb.dynamicloading) { version { require("latest.release") } }
        api(libs.spoofax3.nabl2.common) { version { require("latest.release") } }
        api(libs.spoofax3.resource) { version { require("latest.release") } }
        api(libs.spoofax3.sdf3) { version { require("latest.release") } }
        api(libs.spoofax3.sdf3.cli) { version { require("latest.release") } }
        api(libs.spoofax3.sdf3.eclipse) { version { require("latest.release") } }
        api(libs.spoofax3.sdf3.intellij) { version { require("latest.release") } }
        api(libs.spoofax3.sdf3.extdynamix) { version { require("latest.release") } }
        api(libs.spoofax3.sdf3.extdynamix.eclipse) { version { require("latest.release") } }
        api(libs.spoofax3.sdf3.extdynamix.spoofax2) { version { require("latest.release") } }
        api(libs.spoofax3.sdf3.extstatix) { version { require("latest.release") } }
        api(libs.spoofax3.sdf3.extstatix.eclipse) { version { require("latest.release") } }
        api(libs.spoofax3.spoofax.common) { version { require("latest.release") } }
        api(libs.spoofax3.spoofax2.common) { version { require("latest.release") } }
        api(libs.spoofax3.spt) { version { require("latest.release") } }
        api(libs.spoofax3.spt.api) { version { require("latest.release") } }
        api(libs.spoofax3.spt.cli) { version { require("latest.release") } }
        api(libs.spoofax3.spt.dynamicloading) { version { require("latest.release") } }
        api(libs.spoofax3.spt.eclipse) { version { require("latest.release") } }
        api(libs.spoofax3.spt.intellij) { version { require("latest.release") } }
        api(libs.spoofax3.statix) { version { require("latest.release") } }
        api(libs.spoofax3.statix.cli) { version { require("latest.release") } }
        api(libs.spoofax3.statix.codecompletion) { version { require("latest.release") } }
        api(libs.spoofax3.statix.codecompletion.pie) { version { require("latest.release") } }
        api(libs.spoofax3.statix.common) { version { require("latest.release") } }
        api(libs.spoofax3.statix.eclipse) { version { require("latest.release") } }
        api(libs.spoofax3.statix.intellij) { version { require("latest.release") } }
        api(libs.spoofax3.statix.multilang) { version { require("latest.release") } }
        api(libs.spoofax3.statix.multilang.eclipse) { version { require("latest.release") } }
        api(libs.spoofax3.statix.pie) { version { require("latest.release") } }
        api(libs.spoofax3.stratego) { version { require("latest.release") } }
        api(libs.spoofax3.stratego.cli) { version { require("latest.release") } }
        api(libs.spoofax3.stratego.common) { version { require("latest.release") } }
        api(libs.spoofax3.stratego.eclipse) { version { require("latest.release") } }
        api(libs.spoofax3.stratego.intellij) { version { require("latest.release") } }
        api(libs.spoofax3.stratego.pie) { version { require("latest.release") } }
        api(libs.spoofax3.strategolib) { version { require("latest.release") } }
        api(libs.spoofax3.strategolib.eclipse) { version { require("latest.release") } }
        api(libs.spoofax3.tego.runtime) { version { require("latest.release") } }
        api(libs.spoofax3.test) { version { require("latest.release") } }
        api(libs.spoofax3.tooling.eclipsebundle) { version { require("latest.release") } }
        api(libs.spoofax3.transform.pie) { version { require("latest.release") } }

        // ESV (https://github.com/metaborg/esv)
        api(libs.esv.lang) { version { require("latest.release") } }
        api(libs.spoofax2.esv.lang) { version { require("latest.release") } }

        // Flowspec (https://github.com/metaborg/flowspec)
        api(libs.flowspec.runtime) { version { require("latest.release") } }

        // JSGLR (https://github.com/metaborg/jsglr)
        api(libs.interpreter.library.jsglr) { version { require("latest.release") } }
        api(libs.jsglr) { version { require("latest.release") } }
        api(libs.jsglr.shared) { version { require("latest.release") } }
        api(libs.jsglr2) { version { require("latest.release") } }
        api(libs.makepermissive) { version { require("latest.release") } }

        // MB Exec (https://github.com/metaborg/mb-exec)
        api(libs.interpreter.core) { version { require("latest.release") } }
        api(libs.interpreter.library.java) { version { require("latest.release") } }
        api(libs.interpreter.library.xml) { version { require("latest.release") } }
        api(libs.metaborg.util) { version { require("latest.release") } }
        api(libs.util.vfs2) { version { require("latest.release") } }

        // MB Rep (https://github.com/metaborg/mb-rep)
        api(libs.interpreter.library.index) { version { require("latest.release") } }
        api(libs.spoofax.terms) { version { require("latest.release") } }

        // NaBL (https://github.com/metaborg/nabl)
        api(libs.spoofax2.nabl.lang) { version { require("latest.release") } }
        api(libs.nabl.praffrayi) { version { require("latest.release") } }
        api(libs.nabl.renaming.java) { version { require("latest.release") } }
        api(libs.nabl.scopegraph) { version { require("latest.release") } }
        api(libs.nabl2.lang) { version { require("latest.release") } }
        api(libs.nabl2.runtime) { version { require("latest.release") } }
        api(libs.nabl2.shared) { version { require("latest.release") } }
        api(libs.nabl2.solver) { version { require("latest.release") } }
        api(libs.nabl2.terms) { version { require("latest.release") } }
        api(libs.statix.generator) { version { require("latest.release") } }
        api(libs.statix.lang) { version { require("latest.release") } }
        api(libs.statix.runtime) { version { require("latest.release") } }
        api(libs.statix.solver) { version { require("latest.release") } }
        api(libs.spoofax2.ts.lang) { version { require("latest.release") } }

        // Runtime Libraries (https://github.com/metaborg/runtime-libraries)
        api(libs.meta.lib.analysis) { version { require("latest.release") } }
        api(libs.metaborg.runtime.task) { version { require("latest.release") } }

        // SDF (https://github.com/metaborg/sdf)
        api(libs.parsetable) { version { require("latest.release") } }
        api(libs.sdf2parenthesize) { version { require("latest.release") } }
        api(libs.sdf2table) { version { require("latest.release") } }
        api(libs.sdf3.extstatix) { version { require("latest.release") } }
        api(libs.sdf3.lang) { version { require("latest.release") } }
        api(libs.spoofax2.sdf3.lang) { version { require("latest.release") } }

        // Spoofax Core (https://github.com/metaborg/spoofax)
        api(libs.metaborg.core) { version { require("latest.release") } }
        api(libs.metaborg.core.test) { version { require("latest.release") } }
        api(libs.metaborg.meta.core) { version { require("latest.release") } }
        api(libs.spoofax2.core) { version { require("latest.release") } }
        api(libs.spoofax2.meta.core) { version { require("latest.release") } }
        api(libs.meta.lib.spoofax) { version { require("latest.release") } }
        api(libs.spoofax2.nativebundle) { version { require("latest.release") } }

        // Spoofax Gradle (https://github.com/metaborg/spoofax-gradle)
        api(libs.spoofax3.gradle) { version { require("latest.release") } }

        // SPT (https://github.com/metaborg/spt)
        api(libs.mbt.core) { version { require("latest.release") } }
        api(libs.spt.core) { version { require("latest.release") } }
        api(libs.spt.lang) { version { require("latest.release") } }

        // Stratego (https://github.com/metaborg/stratego)
        api(libs.stratego.build) { version { require("latest.release") } }
        api(libs.stratego.build.spoofax2) { version { require("latest.release") } }
        api(libs.stratego.lang) { version { require("latest.release") } }
        api(libs.stratego2.lang) { version { require("latest.release") } }

        // Stratego XT (https://github.com/metaborg/strategoxt)
        api(libs.strategoxt.jar) { version { require("latest.release") } }
        api(libs.strategoxt.minjar) { version { require("latest.release") } }
        api(libs.strategoxt.strj) { version { require("latest.release") } }

    }
}

publishing {
    publications {
        create<MavenPublication>("mavenPlatform") {
            from(components["javaPlatform"])
        }
    }
}

// signing {
//     sign(publishing.publications["mavenPlatform"])
// }
