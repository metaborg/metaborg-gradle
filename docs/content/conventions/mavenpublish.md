---
title: "Maven Publish Convention"
---
# Maven Publish Convention Plugin
The Maven Publish convention plugin adds metadata to existing publications, and sets up GitHub Packages and Metaborg Artifacts as target publication repositories. A minimal configuration looks like this:

```kotlin title="build.gradle.kts"
plugins {
    id("org.metaborg.convention.maven-publish") version "<version>"
}

// Group must be set
group = "org.metaborg"

// The repository owner and name must be set
mavenPublishConvention {
    repoOwner.set("metaborg")
    repoName.set("convention-plugin-example")
}

// A publication must have been created
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}
```

The `repoOwner` and `repoName` fields must be set, but usually it is easier to set it once on the root project's `build.gradle.kts` for all projects that apply the Maven Publish convention plugin, like this:

```kotlin title="build.gradle.kts"
allprojects {
    pluginManager.withPlugin("org.metaborg.convention.maven-publish") {
        extensions.configure(org.metaborg.convention.MavenPublishConventionExtension::class.java) {
            repoOwner.set("metaborg")
            repoName.set("convention-plugin-example")
        }
    }
}
```


## Configuration
The plugin can be configured using the `mavenPublishConvention` extension:

```kotlin title="build.gradle.kts"
mavenPublishConvention {
    // The owner of the GitHub repository
    repoOwner.set("<owner>")
    // The name of the GitHub repository
    repoName.set("<name>")
    // Whether to publish to GitHub packages.
    publishToGitHubPackages.set(true)
    // Whether to publish to Metaborg Artifacts.
    publishToMetaborgArtifacts.set(true)
    
    metadata {
        // The year the project was started
        inceptionYear.set("2021")
        // The developers of the project
        developers.set(
            listOf(
                Developer("<username>", "<full name>", "<email>"),
            )
        )
        // The source control management system
        scm.set(SCM.GitHub)
        // The license of the project
        license.set(OpenSourceLicense.Apache2)
        // The HTTP SCM URL format
        httpUrlFormat.set("https://github.com/%s/%s")
        // The SCM URL format
        scmUrlFormat.set("scm:git@github.com:%s/%s.git")
    }
}
```



