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


## Publish Tasks
By default, the Root Project convention plugin only creates lifecycle tasks (`build`, `assemble`, `clean`, `check`, and their corresponding versions with the `All` suffix), but no tasks for publishing.  The reasoning is that not all subprojects and included builds will be configured to be published, and as such, delegating `publish` to these included builds can cause errors. However, if you _do_ want root-level `publish` and `publishToMavenLocal` tasks (and `publishAll` and `publishAllToMavenLocal`), then you can achieve this by setting `registerPublishTasks` to `true` in the root project:

```kotlin title="build.gradle.kts"
rootProjectConvention {
    registerPublishTasks.set(true)
}
```

!!! warning ""
    This assumes that all included builds have `publish` and `publishToMavenLocal` tasks. This is generally not the case, as not all included builds may have the `maven-publish` plugin applied. In contrast, for subprojects this is already handled correctly by this plugin.

For included builds that will not be published, we have to add _stub_ `publish` and `publishToMavenLocal` tasks. These tasks will do nothing, but will prevent errors when the root project tries to delegate these tasks to the included builds. To add these stub tasks, set `registerStubPublishTasks` to `true` in the included build's root project:

```kotlin title="build.gradle.kts"
rootProjectConvention {
    registerStubPublishTasks.set(true)
}
```
