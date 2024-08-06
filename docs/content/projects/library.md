---
title: "Library"
---
# Library Project
A library project is published as a JAR to Maven and can be used as a dependency for other projects. The `build.gradle.kts` file is constructed as follows:

## Plugins
At the very least, we need the following plugins:

- the `java-library` plugin produces a library JAR;
- the `org.metaborg.convention.java` plugin applies the Metaborg convention for Java projects;
- the `org.metaborg.convention.maven-publish` plugin allows the project to be published in a Maven repository; and

```kotlin title="build.gradle.kts"
// Workaround for issue: https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    `java-library`
    id("org.metaborg.convention.java")
    id("org.metaborg.convention.maven-publish")
}
```

!!! tip ""
    The `@Suppress("DSL_SCOPE_VIOLATION")` [suppresses an error in IntelliJ](https://youtrack.jetbrains.com/issue/KTIJ-19369) when using `libs` and Gradle 7. This is not necessary in Gradle 8 or newer.


## Publications
To be able to publish the application using the `maven-publish` plugin, we need to configure the publications. For a library, this is the recommended publication:

```kotlin title="build.gradle.kts
publishing {
    publications {
        create<MavenPublication>("mavenLibrary") {
            from(components["java"])
        }
    }
```


## Maven Publish Convention
The `org.metaborg.convention.maven-publish` convention [must also be configured](../conventions/mavenpublish.md). This is usually done in the root project.


## Gitonium
The `org.metaborg.gitonium` plugin should be configured, also usually in the root project. 
