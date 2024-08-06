---
title: "Application"
---
# Application Project
An application project is runnable as a JAR and publishes TAR and ZIP artifacts that make running the application easier. The `build.gradle.kts` file is constructed as follows:

## Plugins
At the very least, we need the following plugins:

- the `application` plugin produces a runnable JAR;
- the `org.metaborg.convention.java` plugin applies the Metaborg convention for Java projects;
- the `org.metaborg.convention.maven-publish` plugin allows the project to be published in a Maven repository; and

```kotlin title="build.gradle.kts"
// Workaround for issue: https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    application
    id("org.metaborg.convention.java")
    id("org.metaborg.convention.maven-publish")
}
```

!!! tip ""
    The `@Suppress("DSL_SCOPE_VIOLATION")` [suppresses an error in IntelliJ](https://youtrack.jetbrains.com/issue/KTIJ-19369) when using `libs` and Gradle 7. This is not necessary in Gradle 8 or newer.



## Main Class
When running the JAR from the command line, by default the main class is executed. This is set using the `application.mainClass` property:

```kotlin title="build.gradle.kts
application {
    mainClass.set("org.metaborg.myapp.Program")
}
```


## Publications
To be able to publish the application using the `maven-publish` plugin, we need to configure the publications. For an application, these are the recommended publications:

```kotlin title="build.gradle.kts
publishing {
    publications {
        create<MavenPublication>("mavenApp") {
            from(components["java"])
            artifact(tasks.distZip)
            artifact(tasks.distTar)
        }
    }
}
```

The `distZip` and `distTar` tasks build a ZIP and TAR, respectively, that include not only the application JAR but also its runtime dependencies and special scripts for running the application from the command-line.


## Maven Publish Convention
The `org.metaborg.convention.maven-publish` convention [must also be configured](../conventions/mavenpublish.md). This is usually done in the root project.


## Gitonium
The `org.metaborg.gitonium` plugin should be configured, also usually in the root project. 
