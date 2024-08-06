package org.metaborg.repoman

import com.github.ajalt.clikt.core.NoOpCliktCommand
import com.github.ajalt.clikt.core.subcommands

object Program {
    @JvmStatic
    fun main(args: Array<String>) {
        CLI.main(args)
    }
}

/** The command-line root base class. */
object CLI: NoOpCliktCommand(name = "repoman") {
    init {
        // TODO: Set versionOption()
        subcommands(
            GenerateCommand,
        )
    }
}


