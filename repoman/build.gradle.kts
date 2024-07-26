import org.metaborg.convention.Developer

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

dependencies {
    implementation("com.github.ajalt.clikt:clikt:4.4.0")            // CLI interface
    implementation("gg.jte:jte:3.1.12")                             // Templating engine
    implementation("gg.jte:jte-kotlin:3.1.12")                      // Templating engine (Kotlin support)
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
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

mavenPublishConvention {
    repoOwner.set("metaborg")
    repoName.set("metaborg-gradle")

    metadata {
        inceptionYear.set("2024")
        developers.set(listOf(
            Developer("Virtlink", "Daniel A. A. Pelsmaeker", "developer@pelsmaeker.net"),
        ))
    }
}
