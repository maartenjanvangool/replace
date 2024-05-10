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

    @CommandLine.Option(names = ["-f", "--file"], paramLabel = "<filename>", description = ["input is a filelocation"])
    private var isFile: Boolean = false

    @CommandLine.Option(names = ["-t", "--text"], paramLabel = "string", description = ["input is text"])
    private var isLiteral: Boolean = false

    @Parameters(index = "0", description = ["Input, either a file or a literal"])
    private var input: String = ""

    @Parameters(index = "1", description = ["What to find"])
    private var find: String = ""

    @Parameters(index = "2", description = ["What to replace it by."])
    private var replace: String = ""

    override fun run() {
        if (isFile) {
            input = String(Files.readAllBytes(Paths.get(URI("file:///$input"))))
        }
        println(input.replace(find, replace))
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            PicocliRunner.run(Replace::class.java, *args)
        }
    }
}
