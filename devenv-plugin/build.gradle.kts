import org.metaborg.convention.Person

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
    maven("https://artifacts.metaborg.org/content/groups/public/")
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(platform("org.metaborg:platform:0.16.1"))
    implementation(gradleApi())
    implementation(gradleKotlinDsl())
    implementation("org.metaborg:git")

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
            Person("gohla", "Gabriel Konat", "g.d.p.konat@tudelft.nl"),
            Person("virtlink", "Daniel A. A. Pelsmaeker", "d.a.a.pelsmaeker@tudelft.nl"),
        ))
    }
}
