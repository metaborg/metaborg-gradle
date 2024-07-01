plugins {
    `java-library`
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}

group = "org.metaborg"
description = "The Metaborg Gradle convention plugin."

dependencies {
    testImplementation(libs.kotest)
    testImplementation(libs.kotest.assertions)
    testImplementation(gradleTestKit())
}

gradlePlugin {
    plugins {
        create("convention.java") {
            id = "org.metaborg.convention.java"
            implementationClass = "org.metaborg.convention.JavaConventionPlugin"
        }
        create("convention.maven-publish") {
            id = "org.metaborg.convention.maven-publish"
            implementationClass = "org.metaborg.convention.MavenPublishConventionPlugin"
        }
    }
}

publishing {
    afterEvaluate {
        publications {
            withType<MavenPublication> {
                pom {
                    name.set("Metaborg Gradle convention plugins")
                }
            }
        }
    }
}
