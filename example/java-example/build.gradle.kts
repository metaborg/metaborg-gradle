plugins {
    `java-library`
    id("org.metaborg.convention.java")
    id("org.metaborg.convention.maven-publish")
}

group = "org.metaborg"
description = "A Metaborg Gradle convention plugin example project."

javaConvention {
    javaVersion.set(JavaLanguageVersion.of(17))     // Optional
}

// This is required, either here or on the root project
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