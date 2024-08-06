import org.metaborg.convention.Person

// Workaround for issue: https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    application
    id("org.metaborg.convention.java")
    id("org.metaborg.convention.maven-publish")
    id("org.metaborg.convention.junit")
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.gitonium)
}

version = gitonium.version
group = "org.metaborg"
description = "Repository manager for Metaborg/Spoofax projects."

val jteVersion = "3.1.12"
dependencies {
    implementation("com.github.ajalt.clikt:clikt:4.4.0")            // CLI interface
    implementation("gg.jte:jte:$jteVersion")                        // Templating engine
    implementation("gg.jte:jte-kotlin:$jteVersion")                 // Templating engine (Kotlin support)
    implementation("com.charleskorn.kaml:kaml:0.59.0")              // Deserialize YAML files

    testImplementation  (libs.kotest)
    testImplementation  (libs.kotest.assertions)
    testImplementation  (libs.kotest.datatest)
    testImplementation  (libs.kotest.property)
}

application {
    mainClass.set("org.metaborg.repoman.Program")
}

javaConvention {
    javaVersion.set(JavaLanguageVersion.of(17))
}

publishing {
    publications {
        create<MavenPublication>("mavenApp") {
            from(components["java"])
            artifact(tasks.distZip)
            artifact(tasks.distTar)
        }
    }
}

mavenPublishConvention {
    repoOwner.set("metaborg")
    repoName.set("metaborg-gradle")

    metadata {
        inceptionYear.set("2024")
        developers.set(listOf(
            Person("Virtlink", "Daniel A. A. Pelsmaeker", "developer@pelsmaeker.net"),
        ))
    }
}
