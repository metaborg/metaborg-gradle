plugins {
    `java-library`
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
    signing
}

group = "org.metaborg.spoofax3"
description = "The Spoofax 3 Gradle convention plugin."

dependencies {
    testImplementation(libs.kotest)
    testImplementation(libs.kotest.assertions)
    testImplementation(gradleTestKit())
}

gradlePlugin {
    plugins {
        create("metaborg-java-library") {
            id = "org.metaborg.spoofax3.gradle-convention.java-library"
            implementationClass = "org.metaborg.spoofax3.gradleconvention.JavaLibraryPlugin"
        }
    }
}

publishing {
    afterEvaluate {
        publications {
            withType<MavenPublication> {
                pom {
                    name.set("Spoofax 3 Gradle convention plugin")
                }
            }
        }
    }
}

// signing {
//     sign(publishing.publications["mavenPlatform"])
// }
