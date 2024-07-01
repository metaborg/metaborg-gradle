---
title: "Gradle Convention"
---
# Gradle Convention

The Metaborg Gradle convention and development plugins.

## Java Convention
The Java convention plugin configures a project to be built using Java 11 by default, but this can be overridden.

To apply the plugin:

```kotlin title="build.gradle.kts"
plugins {
    id("org.metaborg.convention.java") version <version>
}
```


## Maven Publish Convention
The Maven Publish convention plugin adds metadata to existing publications, and sets up GitHub Packages and Metaborg Artifacts as target publication repositories. A minimal configuration looks like this:

```kotlin title="build.gradle.kts"
plugins {
    id("org.metaborg.convention.maven-publish") version <version>
}

// Group must be set
group = "org.metaborg"

// The repository owner and name must be set
mavenPublishConvention {
    repoOwner.set("metaborg")
    repoName.set("convention-plugin-example")
}

// A publication must have been created
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}
```

