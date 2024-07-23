import org.metaborg.convention.Developer

plugins {
    `java-library`
    id("org.metaborg.convention.java")
    id("org.metaborg.convention.junit")
    id("org.metaborg.convention.maven-publish")
    `kotlin-dsl`
    `java-gradle-plugin`
    // We don't use the version catalog here, to avoid a bootstrapping problem.
    id("org.metaborg.gitonium") version "1.7.1"
}

version = gitonium.version
group = "org.metaborg"
description = "The Metaborg Gradle Devenv plugin."

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(gradleApi())
    implementation(gradleKotlinDsl())

    testImplementation("io.kotest:kotest-runner-junit5:5.8.1")
    testImplementation("io.kotest:kotest-assertions-core:5.8.1")
    testImplementation(gradleTestKit())
}

gradlePlugin {
    plugins {
        create("devenv.root") {
            id = "org.metaborg.devenv.root"
            implementationClass = "org.metaborg.devenv.DevenvRootPlugin"
        }
    }
}

// Required, either here or on the root project
mavenPublishConvention {
    repoOwner.set("metaborg")
    repoName.set("metaborg-gradle")

    metadata {
        inceptionYear.set("2018")
        developers.set(listOf(
            Developer("gohla", "Gabriel Konat", "g.d.p.konat@tudelft.nl"),
            Developer("virtlink", "Daniel A. A. Pelsmaeker", "d.a.a.pelsmaeker@tudelft.nl"),
        ))
    }
}
