---
title: "Root Project Convention"
---
# Root Project Convention Plugin
The Root Project convention plugin adds tasks that invoke the corresponding tasks on the sub-builds and subprojects.

```kotlin title="build.gradle.kts"
plugins {
    id("org.metaborg.convention.root-project") version "<version>"
}
```

## Configuration
The plugin can be configured using the `rootProjectConvention` extension:

```kotlin title="build.gradle.kts"
rootProjectConvention {
    // The suffix for tasks that invoke tasks in subprojects and included builds
    taskNameSuffix.set("All")
    assembleTaskName.set("assembleAll")
    buildTaskName.set("buildAll")
    cleanTaskName.set("cleanAll")
    publishTaskName.set("publishAll")
    publishToMavenLocalTaskName.set("publishAllToMavenLocal")
    checkTaskName.set("checkAll")
    testTaskName.set("testAll")
    tasksTaskName.set("allTasks")
}
```