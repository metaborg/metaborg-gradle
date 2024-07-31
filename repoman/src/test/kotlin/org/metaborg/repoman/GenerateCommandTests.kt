package org.metaborg.repoman

import com.github.ajalt.clikt.testing.test
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.withClue
import io.kotest.core.spec.style.FunSpec
import io.kotest.engine.spec.tempdir
import io.kotest.matchers.file.shouldNotBeEmpty
import io.kotest.matchers.shouldBe

/** Tests the [GenerateCommand]. */
class GenerateCommandTests: FunSpec({

    test("should generate the necessary files") {
        // Arrange
        val projectDir = tempdir()
        val metadataFile = projectDir.resolve("repo.yaml")
        // Try to exercise all the features
        metadataFile.writeText(
            """
            ---
            repoOwner: "metaborgcube"
            repoName: "queen"
            mainBranch: "master"
            releaseTagPrefix: "v"
            mavenGroup: "org.metaborgcube"
            
            title: "Metaborgcube Queen"
            description: "Resistance is futile."
            documentationLink: "https://metaborgcube.dev/queen/"
            inceptionYear: "2000"
            currentYear: "2373"
            
            libraries:
              - group: "org.metaborgcube"
                name: "picard"
                description: "Make it so."
              - group: "org.metaborgcube"
                name: "riker"
                description: "Engage."
            
            languages:
              - group: "org.metaborgcube"
                name: "worf"
                description: "Today is a good day to die."
              - group: "org.metaborgcube"
                name: "data"
                description: "I am fully functional."
            
            plugins:
              - id: "org.metaborgcube.enterprise"
                description: "NCC-1701-D"
              - id: "org.metaborgcube.voyager"
                description: "NCC-74656"
            
            developers:
              - id: "picard"
                name: "Jean-Luc Picard"
                email: "jl@enterprise.uss"
              - id: "riker"
                name: "William Riker"
                email: "numberone@enterprise.uss"
            
            contributors:
              - id: "worf"
                name: "Worf"
                email: "moghson@enterprise.uss"
              - id: "data"
                name: "Data"
                email: "404@enterprise.uss"
            
            files:
              readme:
                generate: true
                update: true
                body: |
                    I am the beginning, the end, the one who is many. I am the Borg.
              license:
                generate: true
                update: true
              contributing:
                generate: true
                update: true
              codeOfConduct:
                generate: true
                update: true
              changelog:
                generate: true
                update: true
              gitignore:
                generate: true
                update: true
                extra: |
                  .dna/
              gradleWrapper:
                generate: true
                update: true
                gradleVersion: "latest"
                gradleDistributionType: "all"
              gradleRootProject:
                generate: true
                update: true
                rootProjectName: "queen"
                includedBuilds:
                  - name: "sevenofnine"
                    path: "people/sevenofnine/"
                includedProjects:
                  - name: ":core"
                    path: "core/"
                  - name: ":ui"
                    path: "ui/"
                conventionVersion: "0.16.0"
                createPublishTasks: true
              githubWorkflows:
                generate: true
                update: true
                publishRelease: true
                publishSnapshot: true
                buildTask: ":core:build"
                publishTask: ":core:publish"
                printVersionTask: ":core:printVersion"
                buildDocs: true
              githubIssueTemplates:
                generate: true
                update: true
                assignDevelopers: true
                useDiscussions: true
                bugTypeLabel: "Type-Bug"
                featureRequestTypeLabel: "Type-Enhancement"
                questionTypeLabel: "Type-Question"
                bugStateLabel: "State-Triage"
                featureRequestStateLabel: "State-Triage"
                questionStateLabel: "State-Triage"
            """.trimIndent()
        )

        // Act
        val result = CLI.test(arrayOf(
            "generate",
            "--meta", metadataFile.toString(),
            "--repo", projectDir.toString(),
            "--force-update"
        ))
        
        // Assert
        withClue(result.output) {
            result.statusCode shouldBe 0
        }
        assertSoftly {
            projectDir.resolve("README.md").shouldNotBeEmpty()
            projectDir.resolve("LICENSE.md").shouldNotBeEmpty()
            projectDir.resolve("CONTRIBUTING.md").shouldNotBeEmpty()
            projectDir.resolve("CODE_OF_CONDUCT.md").shouldNotBeEmpty()
            projectDir.resolve("CHANGELOG.md").shouldNotBeEmpty()
            projectDir.resolve(".gitignore").shouldNotBeEmpty()
            projectDir.resolve("gradlew").shouldNotBeEmpty()
            projectDir.resolve("gradlew.bat").shouldNotBeEmpty()
            projectDir.resolve("gradle/wrapper/gradle-wrapper.jar").shouldNotBeEmpty()
            projectDir.resolve("gradle/wrapper/gradle-wrapper.properties").shouldNotBeEmpty()
            projectDir.resolve("settings.gradle.kts").shouldNotBeEmpty()
            projectDir.resolve("build.gradle.kts").shouldNotBeEmpty()
            projectDir.resolve(".github/workflows/build.yaml").shouldNotBeEmpty()
            projectDir.resolve(".github/workflows/documentation.yaml").shouldNotBeEmpty()
            projectDir.resolve(".github/ISSUE_TEMPLATE/config.yml").shouldNotBeEmpty()
            projectDir.resolve(".github/ISSUE_TEMPLATE/20-report-a-bug.yml").shouldNotBeEmpty()
        }
    }

})