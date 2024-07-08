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

mavenPublishConvention {
    // Required
    repoOwner.set("metaborg")
    repoName.set("convention-plugin-example")
}

publishing {
    // Required for the maven-publish convention to publish anything
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}