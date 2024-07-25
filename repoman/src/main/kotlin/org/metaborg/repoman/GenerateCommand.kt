package org.metaborg.repoman

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.decodeFromStream
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.path
import gg.jte.ContentType
import gg.jte.TemplateEngine
import gg.jte.output.FileOutput
import gg.jte.resolve.ResourceCodeResolver
import org.metaborg.repoman.meta.RepoMetadata
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.inputStream

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


    override fun run() {
        val repoDir = repoDir ?: Path.of(System.getProperty("user.dir"))
        val metadata = readMetadata(repoDir)
        val resolver = ResourceCodeResolver("templates", Program::class.java.classLoader)
        val engine = TemplateEngine.create(resolver, ContentType.Plain)
        val generator = Generator(repoDir, engine, metadata)

        generator.generateReadme()
        generator.generateLicense()
        generator.generateContributing()
        generator.generateCodeOfConduct()
        generator.generateChangelog()
        generator.generateGitignore()
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
            generate("README.md", metadata.files.readme.generate, metadata.files.readme.update)
        }

        fun generateLicense() {
            generate("LICENSE.md", metadata.files.license.generate, metadata.files.license.update)
        }

        fun generateContributing() {
            generate("CONTRIBUTING.md", metadata.files.contributing.generate, metadata.files.contributing.update)
        }

        fun generateCodeOfConduct() {
            generate("CODE_OF_CONDUCT.md", metadata.files.codeOfConduct.generate, metadata.files.codeOfConduct.update)
        }

        fun generateChangelog() {
            generate("CHANGELOG.md", metadata.files.changelog.generate, metadata.files.changelog.update)
        }

        fun generateGitignore() {
            generate(".gitignore", metadata.files.gitignore.generate, metadata.files.gitignore.update)
        }

        fun generate(filename: String, generate: Boolean, update: Boolean) {
            val outputFile = repoDir.resolve(filename)
            val outputFileExisted = outputFile.exists()
            if (!outputFileExisted && !generate) {
                println("$filename: Not generated")
                return
            } else if (outputFileExisted && !update) {
                println("$filename: Not updated")
                return
            }

            engine.render("$filename.kte", metadata, FileOutput(outputFile))

            if (!outputFileExisted) {
                println("$filename: Generated")
            } else {
                println("$filename: Updated")
            }
        }
    }
}