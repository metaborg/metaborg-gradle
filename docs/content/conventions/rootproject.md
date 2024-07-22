---
title: "Root Project Convention"
---
# Root Project Convention Plugin
The Root Project convention plugin adds `buildAll`, `assembleAll`, `cleanAll`, and `checkAll` tasks that invoke the corresponding tasks on the sub-builds and subprojects. Optionally, it can add `publishAll` and `publishAllToMavenLocal` tasks as well, but this is generally not recommended as not all included builds may have the `maven-publish` plugin applied.

```kotlin title="build.gradle.kts"
plugins {
    id("org.metaborg.convention.root-project") version "<version>"
}
```

## Configuration
The plugin can be configured using the `rootProjectConvention` extension:

```kotlin title="build.gradle.kts"
rootProjectConvention {
    registerLifecycleTasks.set(true)
    registerPublishTasks.set(false)

    registerStubLifecycleTasks.set(true)
    registerStubPublishTasks.set(false)
}
```
