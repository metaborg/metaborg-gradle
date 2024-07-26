package org.metaborg.repoman

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.decodeFromStream
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.path
import gg.jte.ContentType
import gg.jte.TemplateEngine
import gg.jte.output.FileOutput
import gg.jte.resolve.ResourceCodeResolver
import org.metaborg.repoman.meta.RepoMetadata
import java.io.IOException
import java.nio.file.Path
import kotlin.io.path.*

/**
 * Generates or updates the files in a repository, including:
 * - README.md
 * - LICENSE.md
 * - CONTRIBUTING.md
 * - CODE_OF_CONDUCT.md
 * - CHANGELOG.md
 * - .gitignore
 * - ./gradlew (Gradle wrapper)
 * - docs/ (MkDocs Material)
 * - .github/workflows/ (GitHub CI/CD)
 */
object GenerateCommand: CliktCommand(
    name = "generate",
    help = "Generates a README.md file for a specific repository."
) {
    /** The file with the repository metadata. */
    val metadataFile: Path? by option("-m", "--meta", help = "The file with the repository metadata")
        .path(canBeFile = true, canBeDir = false, mustExist = true)

    /** The directory with the repository. */
    val repoDir: Path? by option("-r", "--repo", help = "The directory with the repository")
        .path(canBeFile = false, canBeDir = true, mustExist = true)

    /** The Gradle binary to invoke. */
    val gradleBin: String? by option("--gradle-bin", help = "The Gradle binary to invoke")
        .default("gradle")

    /** Whether to force updating files even if they exist. Use this to just update all files and manually
     * use version control to sort out what to actually update. */
    val forceUpdate: Boolean by option("-f", "--force-update", help = "Force updating files even if they exist")
        .flag(default = false)


    override fun run() {
        val repoDir = repoDir ?: Path.of(System.getProperty("user.dir"))
        val metadata = readMetadata(repoDir)
        val resolver = ResourceCodeResolver("templates", Program::class.java.classLoader)
        val engine = TemplateEngine.create(resolver, ContentType.Plain)
        engine.setTrimControlStructures(true)
        val generator = Generator(repoDir, engine, metadata)

        generator.generateReadme()
        generator.generateLicense()
        generator.generateContributing()
        generator.generateCodeOfConduct()
        generator.generateChangelog()
        generator.generateGitignore()
        generator.generateGradleWrapper()
        generator.generateGradleRootProject()
        generator.generateGithubWorkflows()
        println("Done!")
    }

    private fun readMetadata(repoDir: Path): RepoMetadata {
        println("Reading metadata...")

        val metadataFile = metadataFile ?: repoDir.resolve("repo.yaml")
        val metadata = Yaml.default.decodeFromStream<RepoMetadata>(metadataFile.inputStream())

        return metadata
    }

    class Generator(
        private val repoDir: Path,
        private val engine: TemplateEngine,
        private val metadata: RepoMetadata,
    ) {
        fun generateReadme() {
            val generate = metadata.files.readme.generate
            val update = metadata.files.readme.update || forceUpdate
            generate("README.md", generate, update)
        }

        fun generateLicense() {
            val generate = metadata.files.license.generate
            val update = metadata.files.license.update || forceUpdate
            generate("LICENSE.md", generate, update)
        }

        fun generateContributing() {
            val generate = metadata.files.contributing.generate
            val update = metadata.files.contributing.update || forceUpdate
            generate("CONTRIBUTING.md", generate, update)
        }

        fun generateCodeOfConduct() {
            val generate = metadata.files.codeOfConduct.generate
            val update = metadata.files.codeOfConduct.update || forceUpdate
            generate("CODE_OF_CONDUCT.md", generate, update)
        }

        fun generateChangelog() {
            val generate = metadata.files.changelog.generate
            val update = metadata.files.changelog.update || forceUpdate
            generate("CHANGELOG.md", generate, update)
        }

        fun generateGitignore() {
            val generate = metadata.files.gitignore.generate
            val update = metadata.files.gitignore.update || forceUpdate
            generate("gitignore", generate, update, path = ".gitignore")
        }

        fun generateGradleWrapper() {
            val generate = metadata.files.gradleWrapper.generate
            val update = metadata.files.gradleWrapper.update || forceUpdate
            generateGradleWrapper(generate, update)
        }

        fun generateGradleRootProject() {
            val generate = metadata.files.gradleRootProject.generate
            val update = metadata.files.gradleRootProject.update || forceUpdate
            generate("settings.gradle.kts", generate, update)
            generate("build.gradle.kts", generate, update)
        }

        fun generateGithubWorkflows() {
            val generate = metadata.files.githubWorkflows.generate
            val update = metadata.files.githubWorkflows.update || forceUpdate
            generate("github/workflows/build.yaml", generate, update, path = ".github/workflows/build.yaml")
            if (metadata.files.githubWorkflows.buildDocs) {
                generate("github/workflows/documentation.yaml", generate, update, path = ".github/workflows/documentation.yaml")
            }
        }

        private fun generate(templateName: String, generate: Boolean, update: Boolean, path: String = templateName) {
            val outputFile = repoDir.resolve(path)
            val outputFileExisted = outputFile.exists()
            if (!outputFileExisted && !generate) {
                println("$path: Not generated")
                return
            } else if (outputFileExisted && !update) {
                println("$path: Not updated")
                return
            }

            FileOutput(outputFile).use { output ->
                engine.render("$templateName.kte", metadata, output)
            }

            if (!outputFileExisted) {
                println("$path: Generated")
            } else {
                println("$path: Updated")
            }
        }

        @OptIn(ExperimentalPathApi::class)
        private fun generateGradleWrapper(generate: Boolean, update: Boolean) {
            // We use the gradle/wrapper/gradle-wrapper.properties file to determine whether a Gradle wrapper
            //  is present and configured, as this is the file that might be customized by the user. The other
            //  files can safely be regenerated.
            val gradleWrapperProperties = repoDir.resolve("gradle/wrapper/gradle-wrapper.properties")
            val gradleWrapperPropertiesExisted = gradleWrapperProperties.exists()
            if (!gradleWrapperPropertiesExisted && !generate) {
                println("Gradle wrapper: Not generated")
                return
            } else if (gradleWrapperPropertiesExisted && !update) {
                println("Gradle wrapper: Not updated")
                return
            }

            // Generate the wrapper in a temporary directory and copy it to the repository
            val tmpDir = createTempDirectory()
            val settingsFile = tmpDir.resolve("settings.gradle.kts")
            settingsFile.createFile()
            val processBuilder = ProcessBuilder().apply {
                command(
                    gradleBin,
                    "wrapper",
                    "--gradle-version=${metadata.files.gradleWrapper.gradleVersion}",
                    "--distribution-type=${metadata.files.gradleWrapper.gradleDistributionType}",
                    "--quiet",
                )
                directory(tmpDir.toFile())
                // Merge STDERR into STDOUT
                redirectErrorStream(true)
            }
            try {
                // THROWS: IOException, SecurityException, UnsupportedOperationException
                val process = processBuilder.start()
                // NOTE: We don't close streams that we didn't open.
                val stdout = process.inputStream.bufferedReader().readText()
                // THROWS: InterruptedException
                val exitCode = process.waitFor()
                if (exitCode != 0) throw IOException(stdout.trim())
            } catch (ex: IOException) {
                println("Gradle wrapper: Failed to generate: ${ex.message}")
                return
            } catch (ex: SecurityException) {
                println("Gradle wrapper: Failed to generate: ${ex.message}")
                return
            } catch (ex: UnsupportedOperationException) {
                println("Gradle wrapper: Failed to generate: ${ex.message}")
                return
            } catch (ex: InterruptedException) {
                println("Gradle wrapper: Failed to generate: ${ex.message}")
                return
            }

            // Remove the temporary Gradle settings file
            settingsFile.deleteExisting()
            // Copy the generated files back to the repository directory, overwriting what's there
            tmpDir.copyToRecursively(repoDir, followLinks = false, overwrite = true)

            if (!gradleWrapperPropertiesExisted) {
                println("Gradle wrapper: Generated")
            } else {
                println("Gradle wrapper: Updated")
            }
        }
    }
}