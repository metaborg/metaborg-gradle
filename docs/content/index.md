---
title: "Home"
---
# Metaborg Gradle
The Metaborg Gradle convention and development plugins.

- [Gradle convention plugins](./conventions/index.md)



## Catalog
The `org.metaborg:catalog` artifact provides recommended versions for dependencies, and should be used in projects that are part of Spoofax. It is automatically added to the buildscript classpath by the `org.metaborg.convention.settings` plugin.

Spoofax dependencies don't have a version specified in the catalog. Therefore, when using Spoofax dependencies, the `org.metaborg:platform` must be applied as follows:

```kotlin
dependencies {
    implementation(platform(libs.metaborg.platform))
    
    // ... Spoofax dependencies ...
}
```