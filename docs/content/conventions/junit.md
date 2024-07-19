---
title: "JUnit Convention"
---
# JUnit Convention Plugin
The JUnit convention plugin configures a Java project to use JUnit 5 for testing, and also adds a JUnit dependency.

To use the plugin, you need to apply it to the project:

```kotlin title="build.gradle.kts"
plugins {
    id("org.metaborg.convention.junit") version "<version>"
}
```

!!! note "JUnit Dependency"
    If a version catalog is configured, this plugin automatically adds the following dependency:

    ```kotlin
    dependencies {
        testImplementation(libs.junit)
    }
    ```

    If the project has no version catalog, it adds the following dependency:

    ```kotlin
    dependencies {
        testImplementation("org.junit.jupiter:junit-jupiter:<version>")
    }
    ```


## Configuration
The plugin can be configured using the `junitConvention` extension:

```kotlin title="build.gradle.kts"
junitConvention {
    // The name of the version catalog to use
    versionCatalogName.set("libs")
    // The alias of the JUnit library in the version catalog
    jUnitAlias.set("junit")
    // The dependency of JUnit to use if it is not found in the version catalog
    jUnitDependency.set("org.junit.jupiter:junit-jupiter:<version>")
}
```



