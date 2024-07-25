package org.metaborg.repoman.templates

import gg.jte.ContentType
import gg.jte.TemplateEngine
import gg.jte.TemplateOutput
import gg.jte.resolve.DirectoryCodeResolver
import gg.jte.resolve.ResourceCodeResolver
import org.metaborg.repoman.Program
import java.nio.file.Path

/** Base class for templates. */
abstract class Template(
    /** The path in the resources templates/ directory to the template file. */
    private val templatePath: String,
) {
    /**
     * Renders the template to the specified output.
     *
     * @param output The output to render to.
     * @param templatePath The path to the template file; or `null` to use the default template in the JAR's resources.
     */
    fun renderTo(output: TemplateOutput, templatePath: Path? = null) {
        val resolver = if (templatePath != null) DirectoryCodeResolver(templatePath)
        else ResourceCodeResolver("templates/${this.templatePath}", Program::class.java.classLoader)
        val engine = TemplateEngine.create(resolver, ContentType.Plain)
        engine.render("", this, output)
    }
}