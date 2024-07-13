import org.metaborg.convention.MavenPublishConventionExtension

plugins {
    id("org.metaborg.convention.root-project")
}

// Required, either here or on each subproject
allprojects {
    pluginManager.withPlugin("org.metaborg.convention.maven-publish") {
        extensions.configure(MavenPublishConventionExtension::class.java) {
            repoOwner.set("metaborg")
            repoName.set("convention-plugin-example")
        }
    }
}

