package nl.craftsmen

import io.micronaut.configuration.picocli.PicocliRunner
import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.Parameters
import java.net.URI
import java.nio.file.Files
import java.nio.file.Paths


/**
 * https://micronaut-projects.github.io/micronaut-picocli/latest/guide/
 * https://guides.micronaut.io/latest/micronaut-creating-first-graal-app-maven-java.html
 */
@Command(name = "replace", description = ["An app to find and replace stuff in files or in input text."], mixinStandardHelpOptions = true)
class Replace : Runnable {

    @CommandLine.Option(names = ["-f", "--file"], paramLabel = "<filename>", description = ["input is a file path"])
    private var isFile: Boolean = false

    @CommandLine.Option(names = ["--first"], paramLabel = "Replace the first occurrence (if any exist) only")
    private var firstOnly: Boolean = false

    @Parameters(index = "0", description = ["Input, either a file or a literal"])
    private var input: String = ""

    @Parameters(index = "1", description = ["What to find"])
    private var find: String = ""

    @Parameters(index = "2", description = ["What to replace it by."])
    private var replace: String = ""

    @Parameters(index = "3", description = ["From index"], defaultValue = "0")
    private var index: Int = 0

    override fun run() {
        if (isFile) {
            input = String(Files.readAllBytes(Paths.get(URI("file:///$input"))))
        }
        if(firstOnly) {
            println(input.substring(0, index) + input.substring(index).replaceFirst(find, replace))
        } else {
            println(input.substring(0, index) + input.substring(index).replace (find, replace))
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            PicocliRunner.run(Replace::class.java, *args)
        }
    }
}
