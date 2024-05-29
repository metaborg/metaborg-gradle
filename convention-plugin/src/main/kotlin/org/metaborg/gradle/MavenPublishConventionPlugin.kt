package org.metaborg.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class MavenPublishConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        // maven-publish plugin
        // - Configure maven-publish
        //   configure SCM (GitHub by default, etc)
        //   configure owner
        //   configure name
        //   configure license (Apache 2.0 by default)
        //   configure developers
        //   configure inception year
        //   configure publish to GitHub packages (by default if SCM = GitHub)
        //   configure publish to Metaborg Artifacts (by default)
        //   configure publish to Maven Central (optional)
        // - Configure signing (optional)

        TODO("Not yet implemented")
    }
}