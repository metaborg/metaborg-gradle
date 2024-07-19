---
title: "Root Project Convention"
---
# Settings Convention Plugin
The Settings convention applies the Gradle Develocity and Foojay plugins, and installs the Metaborg version catalog.

```kotlin title="settings.gradle.kts"
pluginManagement {
    repositories {
        maven("https://artifacts.metaborg.org/content/groups/public/")
    }
}

plugins {
    id("org.metaborg.convention.settings") version "0.0.11"
}
```

!!! warning ""
    In contrast to the other plugin, the Settings convention plugin must be applied in the project's `settings.gradle.kts`.

!!! note ""
    After applying the Settings convention plugin, other plugins applied in projects' `build.gradle.kts` files no longer need a version number.

!!! tip ""
    After configuring the plugin, you can use the `libs` version catalog to add dependencies to your project.
    See the [Version Catalog](https://github.com/metaborg/metaborg-gradle/blob/main/depman/gradle/libs.versions.toml) to see the defined libraries.


## Configuration
The plugin can be configured using the `settingsConvention` extension:

```kotlin title="settings.gradle.kts"
settingsConvention {
    // No options.
}
```
