---
title: "Java Convention"
---
# Java Convention Plugin
The Java convention plugin configures a project to be built using Java 11 by default, but this can be overridden.

To use the plugin, you need to apply both the Java convention plugin and one of the Java plugins (`java-library`, `java`):

```kotlin title="build.gradle.kts"
plugins {
    id("org.metaborg.convention.java") version "<version>"
    `java-library`
}
```

!!! note "Kotlin"
    When using Kotlin, it will automatically use the configured JVM toolchain.



## Configuration
The plugin can be configured using the `javaConvention` extension:

```kotlin title="build.gradle.kts"
javaConvention {
    // The Java version to use
    javaVersion.set(JavaLanguageVersion.of(11))
}
```



