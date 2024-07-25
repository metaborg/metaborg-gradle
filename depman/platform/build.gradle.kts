plugins {
    `java-platform`
    `maven-publish`
    signing
    id("org.metaborg.convention.maven-publish")
}

group = "org.metaborg"
description = "A Spoofax 3 platform."

dependencies {
    constraints {
        // NOTE: Also update part of libs.versions.toml

        // Metaborg Git (https://github.com/metaborg/metaborg-git/)
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
        api(libs.metaborg.pie.lang)
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
        api(libs.spoofax3.cfg.cli)
        api(libs.spoofax3.cfg.eclipse)
        api(libs.spoofax3.cfg.intellij)
        api(libs.spoofax3.cfg.spoofax2)
        api(libs.spoofax3.cli)
        api(libs.spoofax3.compiler)
        api(libs.spoofax3.compiler.eclipsebundle)
        api(libs.spoofax3.compiler.gradle)
        api(libs.spoofax3.compiler.gradle.spoofax2)
        api(libs.spoofax3.compiler.interfaces)
        api(libs.spoofax3.compiler.spoofax2)
        api(libs.spoofax3.compiler.spoofax2.dagger)
        api(libs.spoofax3.constraint.common)
        api(libs.spoofax3.constraint.pie)
        api(libs.spoofax3.core)
        api(libs.spoofax3.dynamix)
        api(libs.spoofax3.dynamix.cli)
        api(libs.spoofax3.dynamix.eclipse)
        api(libs.spoofax3.dynamix.intellij)
        api(libs.spoofax3.dynamix.spoofax2)
        api(libs.spoofax3.eclipse)
        api(libs.spoofax3.esv)
        api(libs.spoofax3.esv.cli)
        api(libs.spoofax3.esv.common)
        api(libs.spoofax3.esv.eclipse)
        api(libs.spoofax3.esv.intellij)
        api(libs.spoofax3.gpp)
        api(libs.spoofax3.gpp.eclipse)
        api(libs.spoofax3.intellij)
        api(libs.spoofax3.jsglr.common)
        api(libs.spoofax3.jsglr.pie)
        api(libs.spoofax3.jsglr1.common)
        api(libs.spoofax3.jsglr2.common)
        api(libs.spoofax3.libspoofax2)
        api(libs.spoofax3.libspoofax2.eclipse)
        api(libs.spoofax3.libstatix)
        api(libs.spoofax3.libstatix.eclipse)
        api(libs.spoofax3.lwb.compiler)
        api(libs.spoofax3.lwb.compiler.gradle)
        api(libs.spoofax3.lwb.dynamicloading)
        api(libs.spoofax3.nabl2.common)
        api(libs.spoofax3.resource)
        api(libs.spoofax3.sdf3)
        api(libs.spoofax3.sdf3.cli)
        api(libs.spoofax3.sdf3.eclipse)
        api(libs.spoofax3.sdf3.extdynamix)
        api(libs.spoofax3.sdf3.extdynamix.eclipse)
        api(libs.spoofax3.sdf3.extdynamix.spoofax2)
        api(libs.spoofax3.sdf3.extstatix)
        api(libs.spoofax3.sdf3.extstatix.eclipse)
        api(libs.spoofax3.sdf3.intellij)
        api(libs.spoofax3.spoofax.common)
        api(libs.spoofax3.spoofax2.common)
        api(libs.spoofax3.spt)
        api(libs.spoofax3.spt.api)
        api(libs.spoofax3.spt.cli)
        api(libs.spoofax3.spt.dynamicloading)
        api(libs.spoofax3.spt.eclipse)
        api(libs.spoofax3.spt.intellij)
        api(libs.spoofax3.statix)
        api(libs.spoofax3.statix.cli)
        api(libs.spoofax3.statix.codecompletion)
        api(libs.spoofax3.statix.codecompletion.pie)
        api(libs.spoofax3.statix.common)
        api(libs.spoofax3.statix.eclipse)
        api(libs.spoofax3.statix.intellij)
        api(libs.spoofax3.statix.multilang)
        api(libs.spoofax3.statix.multilang.eclipse)
        api(libs.spoofax3.statix.pie)
        api(libs.spoofax3.stratego)
        api(libs.spoofax3.stratego.cli)
        api(libs.spoofax3.stratego.common)
        api(libs.spoofax3.stratego.eclipse)
        api(libs.spoofax3.stratego.intellij)
        api(libs.spoofax3.stratego.pie)
        api(libs.spoofax3.strategolib)
        api(libs.spoofax3.strategolib.eclipse)
        api(libs.spoofax3.tego.runtime)
        api(libs.spoofax3.test)
        api(libs.spoofax3.tooling.eclipsebundle)
        api(libs.spoofax3.transform.pie)


        // Dynsem (https://github.com/metaborg/dynsem)
        api(libs.spoofax2.dynsem.lang)

        // ESV (https://github.com/metaborg/esv)
        api(libs.esv.lang)

        api(libs.spoofax2.esv.lang)

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
        api(libs.util.vfs2)

        api(libs.spoofax2.interpreter.core)
        api(libs.spoofax2.interpreter.library.java)
        api(libs.spoofax2.interpreter.library.xml)
        api(libs.spoofax2.metaborg.util)
        api(libs.spoofax2.util.vfs2)

        // MB Rep (https://github.com/metaborg/mb-rep)
        api(libs.interpreter.library.index)
        api(libs.spoofax.terms)

        api(libs.spoofax2.interpreter.library.index)
        api(libs.spoofax2.spoofax.terms)

        // NaBL (https://github.com/metaborg/nabl)
        api(libs.nabl.praffrayi)
        api(libs.nabl.renaming.java)
        api(libs.nabl.scopegraph)
        api(libs.nabl2.lang)
        api(libs.nabl2.runtime)
        api(libs.nabl2.shared)
        api(libs.nabl2.solver)
        api(libs.nabl2.terms)
        api(libs.statix.generator)
        api(libs.statix.lang)
        api(libs.statix.runtime)
        api(libs.statix.solver)

        api(libs.spoofax2.nabl.lang)
        api(libs.spoofax2.nabl.praffrayi)
        api(libs.spoofax2.nabl.renaming.java)
        api(libs.spoofax2.nabl.scopegraph)
        api(libs.spoofax2.nabl2.extdynsem)
        api(libs.spoofax2.nabl2.lang)
        api(libs.spoofax2.nabl2.runtime)
        api(libs.spoofax2.nabl2.shared)
        api(libs.spoofax2.nabl2.solver)
        api(libs.spoofax2.nabl2.terms)
        api(libs.spoofax2.statix.generator)
        api(libs.spoofax2.statix.lang)
        api(libs.spoofax2.statix.runtime)
        api(libs.spoofax2.statix.solver)
        api(libs.spoofax2.ts.lang)

        // Runtime Libraries (https://github.com/metaborg/runtime-libraries)
        api(libs.spoofax2.meta.lib.analysis)
        api(libs.spoofax2.metaborg.runtime.task)

        // SDF (https://github.com/metaborg/sdf)
        api(libs.parsetable)
        api(libs.sdf2parenthesize)
        api(libs.sdf2table)
        api(libs.sdf3.extstatix)
        api(libs.sdf3.lang)

        api(libs.spoofax2.parsetable)
        api(libs.spoofax2.sdf2parenthesize)
        api(libs.spoofax2.sdf2table)
        api(libs.spoofax2.sdf3.extstatix)
        api(libs.spoofax2.sdf3.lang)

        // Spoofax Core (https://github.com/metaborg/spoofax)
        api(libs.meta.lib.spoofax)
        api(libs.metaborg.core)
        api(libs.metaborg.core.test)
        api(libs.metaborg.meta.core)
        api(libs.spoofax.core)
        api(libs.spoofax.meta.core)
        api(libs.spoofax.nativebundle)

        api(libs.spoofax2.meta.lib.spoofax)
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
        api(libs.spt.lang)

        api(libs.spoofax2.mbt.core)
        api(libs.spoofax2.spt.core)
        api(libs.spoofax2.spt.lang)

        // Stratego (https://github.com/metaborg/stratego)
        api(libs.gpp.lang)
        api(libs.stratego.build)
        api(libs.stratego.build.spoofax2)
        api(libs.stratego.lang)
        api(libs.stratego2.lang)
        api(libs.strategolib)

        api(libs.spoofax2.gpp.lang)
        api(libs.spoofax2.stratego.build)
        api(libs.spoofax2.stratego.build.spoofax2)
        api(libs.spoofax2.stratego.lang)
        api(libs.spoofax2.stratego2.lang)
        api(libs.spoofax2.strategolib)

        // Stratego XT (https://github.com/metaborg/strategoxt)
        api(libs.strategoxt.strj)

        api(libs.spoofax2.strategoxt.jar)
        api(libs.spoofax2.strategoxt.minjar)
        api(libs.spoofax2.strategoxt.strj)
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
