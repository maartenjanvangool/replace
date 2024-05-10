package nl.craftsmen

import io.micronaut.configuration.picocli.PicocliRunner
import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.Parameters
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths


/**
 * https://micronaut-projects.github.io/micronaut-picocli/latest/guide/
 * https://guides.micronaut.io/latest/micronaut-creating-first-graal-app-maven-java.html
 */
@Command(name = "replace", description = ["An app to find and replace stuff in files or in input text."], mixinStandardHelpOptions = true)
class Replace : Runnable {

    @CommandLine.Option(names = ["-f", "--file"], paramLabel = "ARCHIVE", description = ["The inputfile"])
    private var file: File? = null

    @CommandLine.Option(names = ["-t", "--text"], paramLabel = "ARCHIVE", description = ["The input text"])
    private var input: String? = null

    @Parameters(index = "0", description = ["What to find"])
    private var find: String = ""

    @Parameters(index = "1", description = ["What to replace it by."])
    private var replace: String = ""

    override fun run() {
        if(file==null && input==null){
            println("Input is empty!")
            return
        }
        if (file != null) {
            input = String(Files.readAllBytes(Paths.get(file!!.toURI())))
        }
        println(input!!.replace(find, replace))
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            PicocliRunner.run(Replace::class.java, *args)
        }
    }
}
