plugins {
    `java-platform`
    `maven-publish`
    signing
    id("org.metaborg.convention.maven-publish")
}

group = "org.metaborg"
description = "A Spoofax 3 platform."

// Here we use the versions defined in gradle.properties (or using -P on the command line)
//  to set the versions of the dependencies that should work together.
val metaborgGitVersion: String = property("metaborg-git.version") as String
val metaborgCommonVersion: String = property("metaborg-common.version") as String
val metaborgLogVersion: String = property("metaborg-log.version") as String
val metaborgPieVersion: String = property("metaborg-pie.version") as String
val metaborgResourceVersion: String = property("metaborg-resource.version") as String
val spoofax3Version: String = property("spoofax3.version") as String
val spoofax2Version: String = property("spoofax2.version") as String
val esvVersion: String = property("esv.version") as String
val jsglrVersion: String = property("jsglr.version") as String
val mbExecVersion: String = property("mb-exec.version") as String
val mbRepVersion: String = property("mb-rep.version") as String
val nablVersion: String = property("nabl.version") as String
val sdfVersion: String = property("sdf.version") as String
val spoofaxCoreVersion: String = property("spoofax-core.version") as String
val spoofaxGradleVersion: String = property("spoofax-gradle.version") as String
val sptVersion: String = property("spt.version") as String
val strategoVersion: String = property("stratego.version") as String
val strategoxtVersion: String = property("strategoxt.version") as String

dependencies {
    constraints {
        // NOTE: Also update part of libs.versions.toml

        // Metaborg Git (https://github.com/metaborg/metaborg-git/)
        api(libs.metaborg.git) { version { require(metaborgGitVersion) } }

        // Metaborg Common (https://github.com/metaborg/common)
        api(libs.metaborg.common) { version { require(metaborgCommonVersion) } }

        // Metaborg Log (https://github.com/metaborg/log)
        api(libs.metaborg.log.api) { version { require(metaborgLogVersion) } }
        api(libs.metaborg.log.backend.logback) { version { require(metaborgLogVersion) } }
        api(libs.metaborg.log.backend.slf4j) { version { require(metaborgLogVersion) } }
        api(libs.metaborg.log.dagger) { version { require(metaborgLogVersion) } }

        // Metaborg PIE (https://github.com/metaborg/pie)
        api(libs.metaborg.pie.api) { version { require(metaborgPieVersion) } }
        api(libs.metaborg.pie.api.test) { version { require(metaborgPieVersion) } }
        api(libs.metaborg.pie.dagger) { version { require(metaborgPieVersion) } }
        api(libs.metaborg.pie.graph) { version { require(metaborgPieVersion) } }
        api(libs.metaborg.pie.lang) { version { require(metaborgPieVersion) } }
        api(libs.metaborg.pie.lang.runtime.java) { version { require(metaborgPieVersion) } }
        api(libs.metaborg.pie.lang.runtime.kotlin) { version { require(metaborgPieVersion) } }
        api(libs.metaborg.pie.lang.test) { version { require(metaborgPieVersion) } }
        api(libs.metaborg.pie.runtime) { version { require(metaborgPieVersion) } }
        api(libs.metaborg.pie.runtime.test) { version { require(metaborgPieVersion) } }
        api(libs.metaborg.pie.serde.fst) { version { require(metaborgPieVersion) } }
        api(libs.metaborg.pie.serde.kryo) { version { require(metaborgPieVersion) } }
        api(libs.metaborg.pie.share.coroutine) { version { require(metaborgPieVersion) } }
        api(libs.metaborg.pie.store.lmdb) { version { require(metaborgPieVersion) } }
        api(libs.metaborg.pie.task.archive) { version { require(metaborgPieVersion) } }
        api(libs.metaborg.pie.task.java) { version { require(metaborgPieVersion) } }
        api(libs.metaborg.pie.task.java.ecj) { version { require(metaborgPieVersion) } }
        api(libs.metaborg.pie.taskdefs.guice) { version { require(metaborgPieVersion) } }

        // Metaborg Resource (https://github.com/metaborg/resource)
        api(libs.metaborg.resource.api) { version { require(metaborgResourceVersion) } }
        api(libs.metaborg.resource.dagger) { version { require(metaborgResourceVersion) } }

        // Spoofax 3/PIE (https://github.com/metaborg/spoofax-pie)
        api(libs.spoofax3.aterm.common) { version { require(spoofax3Version) } }
        api(libs.spoofax3.cfg) { version { require(spoofax3Version) } }
        api(libs.spoofax3.cfg.cli) { version { require(spoofax3Version) } }
        api(libs.spoofax3.cfg.eclipse) { version { require(spoofax3Version) } }
        api(libs.spoofax3.cfg.intellij) { version { require(spoofax3Version) } }
        api(libs.spoofax3.cfg.spoofax2) { version { require(spoofax3Version) } }
        api(libs.spoofax3.cli) { version { require(spoofax3Version) } }
        api(libs.spoofax3.compiler) { version { require(spoofax3Version) } }
        api(libs.spoofax3.compiler.eclipsebundle) { version { require(spoofax3Version) } }
        api(libs.spoofax3.compiler.gradle) { version { require(spoofax3Version) } }
        api(libs.spoofax3.compiler.gradle.spoofax2) { version { require(spoofax3Version) } }
        api(libs.spoofax3.compiler.interfaces) { version { require(spoofax3Version) } }
        api(libs.spoofax3.compiler.spoofax2) { version { require(spoofax3Version) } }
        api(libs.spoofax3.compiler.spoofax2.dagger) { version { require(spoofax3Version) } }
        api(libs.spoofax3.constraint.common) { version { require(spoofax3Version) } }
        api(libs.spoofax3.constraint.pie) { version { require(spoofax3Version) } }
        api(libs.spoofax3.core) { version { require(spoofax3Version) } }
        api(libs.spoofax3.dynamix) { version { require(spoofax3Version) } }
        api(libs.spoofax3.dynamix.cli) { version { require(spoofax3Version) } }
        api(libs.spoofax3.dynamix.eclipse) { version { require(spoofax3Version) } }
        api(libs.spoofax3.dynamix.intellij) { version { require(spoofax3Version) } }
        api(libs.spoofax3.dynamix.spoofax2) { version { require(spoofax3Version) } }
        api(libs.spoofax3.eclipse) { version { require(spoofax3Version) } }
        api(libs.spoofax3.esv) { version { require(spoofax3Version) } }
        api(libs.spoofax3.esv.cli) { version { require(spoofax3Version) } }
        api(libs.spoofax3.esv.common) { version { require(spoofax3Version) } }
        api(libs.spoofax3.esv.eclipse) { version { require(spoofax3Version) } }
        api(libs.spoofax3.esv.intellij) { version { require(spoofax3Version) } }
        api(libs.spoofax3.gpp) { version { require(spoofax3Version) } }
        api(libs.spoofax3.gpp.eclipse) { version { require(spoofax3Version) } }
        api(libs.spoofax3.intellij) { version { require(spoofax3Version) } }
        api(libs.spoofax3.jsglr.common) { version { require(spoofax3Version) } }
        api(libs.spoofax3.jsglr.pie) { version { require(spoofax3Version) } }
        api(libs.spoofax3.jsglr1.common) { version { require(spoofax3Version) } }
        api(libs.spoofax3.jsglr2.common) { version { require(spoofax3Version) } }
        api(libs.spoofax3.libspoofax2) { version { require(spoofax3Version) } }
        api(libs.spoofax3.libspoofax2.eclipse) { version { require(spoofax3Version) } }
        api(libs.spoofax3.libstatix) { version { require(spoofax3Version) } }
        api(libs.spoofax3.libstatix.eclipse) { version { require(spoofax3Version) } }
        api(libs.spoofax3.lwb.compiler) { version { require(spoofax3Version) } }
        api(libs.spoofax3.lwb.compiler.gradle) { version { require(spoofax3Version) } }
        api(libs.spoofax3.lwb.dynamicloading) { version { require(spoofax3Version) } }
        api(libs.spoofax3.nabl2.common) { version { require(spoofax3Version) } }
        api(libs.spoofax3.resource) { version { require(spoofax3Version) } }
        api(libs.spoofax3.sdf3) { version { require(spoofax3Version) } }
        api(libs.spoofax3.sdf3.cli) { version { require(spoofax3Version) } }
        api(libs.spoofax3.sdf3.eclipse) { version { require(spoofax3Version) } }
        api(libs.spoofax3.sdf3.extdynamix) { version { require(spoofax3Version) } }
        api(libs.spoofax3.sdf3.extdynamix.eclipse) { version { require(spoofax3Version) } }
        api(libs.spoofax3.sdf3.extdynamix.spoofax2) { version { require(spoofax3Version) } }
        api(libs.spoofax3.sdf3.extstatix) { version { require(spoofax3Version) } }
        api(libs.spoofax3.sdf3.extstatix.eclipse) { version { require(spoofax3Version) } }
        api(libs.spoofax3.sdf3.intellij) { version { require(spoofax3Version) } }
        api(libs.spoofax3.spoofax.common) { version { require(spoofax3Version) } }
        api(libs.spoofax3.spoofax2.common) { version { require(spoofax3Version) } }
        api(libs.spoofax3.spt) { version { require(spoofax3Version) } }
        api(libs.spoofax3.spt.api) { version { require(spoofax3Version) } }
        api(libs.spoofax3.spt.cli) { version { require(spoofax3Version) } }
        api(libs.spoofax3.spt.dynamicloading) { version { require(spoofax3Version) } }
        api(libs.spoofax3.spt.eclipse) { version { require(spoofax3Version) } }
        api(libs.spoofax3.spt.intellij) { version { require(spoofax3Version) } }
        api(libs.spoofax3.statix) { version { require(spoofax3Version) } }
        api(libs.spoofax3.statix.cli) { version { require(spoofax3Version) } }
        api(libs.spoofax3.statix.codecompletion) { version { require(spoofax3Version) } }
        api(libs.spoofax3.statix.codecompletion.pie) { version { require(spoofax3Version) } }
        api(libs.spoofax3.statix.common) { version { require(spoofax3Version) } }
        api(libs.spoofax3.statix.eclipse) { version { require(spoofax3Version) } }
        api(libs.spoofax3.statix.intellij) { version { require(spoofax3Version) } }
        api(libs.spoofax3.statix.multilang) { version { require(spoofax3Version) } }
        api(libs.spoofax3.statix.multilang.eclipse) { version { require(spoofax3Version) } }
        api(libs.spoofax3.statix.pie) { version { require(spoofax3Version) } }
        api(libs.spoofax3.stratego) { version { require(spoofax3Version) } }
        api(libs.spoofax3.stratego.cli) { version { require(spoofax3Version) } }
        api(libs.spoofax3.stratego.common) { version { require(spoofax3Version) } }
        api(libs.spoofax3.stratego.eclipse) { version { require(spoofax3Version) } }
        api(libs.spoofax3.stratego.intellij) { version { require(spoofax3Version) } }
        api(libs.spoofax3.stratego.pie) { version { require(spoofax3Version) } }
        api(libs.spoofax3.strategolib) { version { require(spoofax3Version) } }
        api(libs.spoofax3.strategolib.eclipse) { version { require(spoofax3Version) } }
        api(libs.spoofax3.tego.runtime) { version { require(spoofax3Version) } }
        api(libs.spoofax3.test) { version { require(spoofax3Version) } }
        api(libs.spoofax3.tooling.eclipsebundle) { version { require(spoofax3Version) } }
        api(libs.spoofax3.transform.pie) { version { require(spoofax3Version) } }


        // Dynsem (https://github.com/metaborg/dynsem)
        api(libs.spoofax2.dynsem.lang) { version { require(spoofax2Version) } }

        // ESV (https://github.com/metaborg/esv)
        api(libs.esv.lang) { version { require(esvVersion) } }

        api(libs.spoofax2.esv.lang) { version { require(spoofax2Version) } }

        // Flowspec (https://github.com/metaborg/flowspec)
        api(libs.spoofax2.flowspec.runtime) { version { require(spoofax2Version) } }

        // JSGLR (https://github.com/metaborg/jsglr)
        api(libs.interpreter.library.jsglr) { version { require(jsglrVersion) } }
        api(libs.jsglr) { version { require(jsglrVersion) } }
        api(libs.jsglr.shared) { version { require(jsglrVersion) } }
        api(libs.jsglr2) { version { require(jsglrVersion) } }

        api(libs.spoofax2.interpreter.library.jsglr) { version { require(spoofax2Version) } }
        api(libs.spoofax2.jsglr) { version { require(spoofax2Version) } }
        api(libs.spoofax2.jsglr.shared) { version { require(spoofax2Version) } }
        api(libs.spoofax2.jsglr2) { version { require(spoofax2Version) } }
        api(libs.spoofax2.makepermissive) { version { require(spoofax2Version) } }

        // MB Exec (https://github.com/metaborg/mb-exec)
        api(libs.interpreter.core) { version { require(mbExecVersion) } }
        api(libs.interpreter.library.java) { version { require(mbExecVersion) } }
        api(libs.interpreter.library.xml) { version { require(mbExecVersion) } }
        api(libs.metaborg.util) { version { require(mbExecVersion) } }
        api(libs.util.vfs2) { version { require(mbExecVersion) } }

        api(libs.spoofax2.interpreter.core) { version { require(spoofax2Version) } }
        api(libs.spoofax2.interpreter.library.java) { version { require(spoofax2Version) } }
        api(libs.spoofax2.interpreter.library.xml) { version { require(spoofax2Version) } }
        api(libs.spoofax2.metaborg.util) { version { require(spoofax2Version) } }
        api(libs.spoofax2.util.vfs2) { version { require(spoofax2Version) } }

        // MB Rep (https://github.com/metaborg/mb-rep)
        api(libs.interpreter.library.index) { version { require(mbRepVersion) } }
        api(libs.spoofax.terms) { version { require(mbRepVersion) } }

        api(libs.spoofax2.interpreter.library.index) { version { require(spoofax2Version) } }
        api(libs.spoofax2.spoofax.terms) { version { require(spoofax2Version) } }

        // NaBL (https://github.com/metaborg/nabl)
        api(libs.nabl.praffrayi) { version { require(nablVersion) } }
        api(libs.nabl.renaming.java) { version { require(nablVersion) } }
        api(libs.nabl.scopegraph) { version { require(nablVersion) } }
        api(libs.nabl2.lang) { version { require(nablVersion) } }
        api(libs.nabl2.runtime) { version { require(nablVersion) } }
        api(libs.nabl2.shared) { version { require(nablVersion) } }
        api(libs.nabl2.solver) { version { require(nablVersion) } }
        api(libs.nabl2.terms) { version { require(nablVersion) } }
        api(libs.statix.generator) { version { require(nablVersion) } }
        api(libs.statix.lang) { version { require(nablVersion) } }
        api(libs.statix.runtime) { version { require(nablVersion) } }
        api(libs.statix.solver) { version { require(nablVersion) } }

        api(libs.spoofax2.nabl.lang) { version { require(spoofax2Version) } }
        api(libs.spoofax2.nabl.praffrayi) { version { require(spoofax2Version) } }
        api(libs.spoofax2.nabl.renaming.java) { version { require(spoofax2Version) } }
        api(libs.spoofax2.nabl.scopegraph) { version { require(spoofax2Version) } }
        api(libs.spoofax2.nabl2.extdynsem) { version { require(spoofax2Version) } }
        api(libs.spoofax2.nabl2.lang) { version { require(spoofax2Version) } }
        api(libs.spoofax2.nabl2.runtime) { version { require(spoofax2Version) } }
        api(libs.spoofax2.nabl2.shared) { version { require(spoofax2Version) } }
        api(libs.spoofax2.nabl2.solver) { version { require(spoofax2Version) } }
        api(libs.spoofax2.nabl2.terms) { version { require(spoofax2Version) } }
        api(libs.spoofax2.statix.generator) { version { require(spoofax2Version) } }
        api(libs.spoofax2.statix.lang) { version { require(spoofax2Version) } }
        api(libs.spoofax2.statix.runtime) { version { require(spoofax2Version) } }
        api(libs.spoofax2.statix.solver) { version { require(spoofax2Version) } }
        api(libs.spoofax2.ts.lang) { version { require(spoofax2Version) } }

        // Runtime Libraries (https://github.com/metaborg/runtime-libraries)
        api(libs.spoofax2.meta.lib.analysis) { version { require(spoofax2Version) } }
        api(libs.spoofax2.metaborg.runtime.task) { version { require(spoofax2Version) } }

        // SDF (https://github.com/metaborg/sdf)
        api(libs.parsetable) { version { require(sdfVersion) } }
        api(libs.sdf2parenthesize) { version { require(sdfVersion) } }
        api(libs.sdf2table) { version { require(sdfVersion) } }
        api(libs.sdf3.extstatix) { version { require(sdfVersion) } }
        api(libs.sdf3.lang) { version { require(sdfVersion) } }

        api(libs.spoofax2.parsetable) { version { require(spoofax2Version) } }
        api(libs.spoofax2.sdf2parenthesize) { version { require(spoofax2Version) } }
        api(libs.spoofax2.sdf2table) { version { require(spoofax2Version) } }
        api(libs.spoofax2.sdf3.extstatix) { version { require(spoofax2Version) } }
        api(libs.spoofax2.sdf3.lang) { version { require(spoofax2Version) } }

        // Spoofax Core (https://github.com/metaborg/spoofax)
        api(libs.meta.lib.spoofax) { version { require(spoofaxCoreVersion) } }
        api(libs.metaborg.core) { version { require(spoofaxCoreVersion) } }
        api(libs.metaborg.core.test) { version { require(spoofaxCoreVersion) } }
        api(libs.metaborg.meta.core) { version { require(spoofaxCoreVersion) } }
        api(libs.spoofax.core) { version { require(spoofaxCoreVersion) } }
        api(libs.spoofax.meta.core) { version { require(spoofaxCoreVersion) } }
        api(libs.spoofax.nativebundle) { version { require(spoofaxCoreVersion) } }

        api(libs.spoofax2.meta.lib.spoofax) { version { require(spoofax2Version) } }
        api(libs.spoofax2.metaborg.core) { version { require(spoofax2Version) } }
        api(libs.spoofax2.metaborg.core.test) { version { require(spoofax2Version) } }
        api(libs.spoofax2.metaborg.meta.core) { version { require(spoofax2Version) } }
        api(libs.spoofax2.spoofax.core) { version { require(spoofax2Version) } }
        api(libs.spoofax2.spoofax.meta.core) { version { require(spoofax2Version) } }
        api(libs.spoofax2.spoofax.nativebundle) { version { require(spoofax2Version) } }

        // Spoofax Gradle (https://github.com/metaborg/spoofax.gradle)
        api(libs.spoofax3.gradle) { version { require(spoofaxGradleVersion) } }

        // SPT (https://github.com/metaborg/spt)
        api(libs.mbt.core) { version { require(sptVersion) } }
        api(libs.spt.core) { version { require(sptVersion) } }
        api(libs.spt.lang) { version { require(sptVersion) } }

        api(libs.spoofax2.mbt.core) { version { require(spoofax2Version) } }
        api(libs.spoofax2.spt.core) { version { require(spoofax2Version) } }
        api(libs.spoofax2.spt.lang) { version { require(spoofax2Version) } }

        // Stratego (https://github.com/metaborg/stratego)
        api(libs.stratego.build) { version { require(strategoVersion) } }
        api(libs.stratego.build.spoofax2) { version { require(strategoVersion) } }
        api(libs.stratego.lang) { version { require(strategoVersion) } }
        api(libs.stratego2.lang) { version { require(strategoVersion) } }

        api(libs.spoofax2.stratego.build) { version { require(spoofax2Version) } }
        api(libs.spoofax2.stratego.build.spoofax2) { version { require(spoofax2Version) } }
        api(libs.spoofax2.stratego.lang) { version { require(spoofax2Version) } }
        api(libs.spoofax2.stratego2.lang) { version { require(spoofax2Version) } }

        // Stratego XT (https://github.com/metaborg/strategoxt)
        api(libs.strategoxt.strj) { version { require(strategoxtVersion) } }

        api(libs.spoofax2.strategoxt.jar) { version { require(spoofax2Version) } }
        api(libs.spoofax2.strategoxt.minjar) { version { require(spoofax2Version) } }
        api(libs.spoofax2.strategoxt.strj) { version { require(spoofax2Version) } }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenPlatform") {
            from(components["javaPlatform"])
            pom {
                name.set("Spoofax 3 Platform")
            }
        }
    }
}

// signing {
//     sign(publishing.publications["mavenPlatform"])
// }
