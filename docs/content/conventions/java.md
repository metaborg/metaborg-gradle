---
title: "Java Convention"
---
# Java Convention Plugin
The Java convention plugin configures a project to:

- build using a Java 11 toolchain by default;
- silence JavaDoc warnings and errors as much as possible;
- use UTF-8 encoding;
- transfer properties prefixed with `app.` to the `run` task if the Java `application` plugin is applied;
- transfer properties prefixed with `test.` to the `test` task.

To use the plugin, you need to apply both the Java convention plugin and one of the Java plugins (e.g., `java-library`, `java`):

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



