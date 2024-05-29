plugins {
    `java-library`
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
    signing
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
        create("gradle-convention.java") {
            id = "org.metaborg.gradle-convention.java"
            implementationClass = "org.metaborg.gradleconvention.JavaPlugin"
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

// signing {
//     sign(publishing.publications["mavenPlatform"])
// }
