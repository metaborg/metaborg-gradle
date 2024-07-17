import org.metaborg.convention.MavenPublishConventionExtension

plugins {
    id("org.metaborg.convention.root-project")
    // We don't use the version catalog here, to avoid a bootstrapping problem.
    id("org.metaborg.gitonium") version "1.7.1"
}

allprojects {
    apply(plugin = "org.metaborg.gitonium")

    version = gitonium.version
    group = "org.metaborg"

    pluginManager.withPlugin("org.metaborg.convention.maven-publish") {
        extensions.configure(MavenPublishConventionExtension::class.java) {
            repoOwner.set("metaborg")
            repoName.set("metaborg-gradle")
        }
    }

    repositories {
        maven(url = "https://artifacts.metaborg.org/content/groups/public/")
        mavenCentral()
    }
}
