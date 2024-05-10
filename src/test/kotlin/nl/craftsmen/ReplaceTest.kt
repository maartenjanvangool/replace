package nl.craftsmen

import io.micronaut.configuration.picocli.PicocliRunner
import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.Environment
import io.micronaut.core.io.ResourceResolver
import io.micronaut.core.io.scan.ClassPathResourceLoader
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ReplaceTest {

    private var ctx = ApplicationContext.run(Environment.CLI, Environment.TEST)
    private var baos = ByteArrayOutputStream()

    @BeforeEach
    fun beforeEach() {
        ctx = ApplicationContext.run(Environment.CLI, Environment.TEST)
        baos = ByteArrayOutputStream()
        System.setOut(PrintStream(baos))
    }

    @AfterEach
    fun afterEach() {
        ctx.close()

    }

    @Test
    fun `input text is replaced`() {
        val args = arrayOf("-t", "blablabla", "bla", "vla")
        PicocliRunner.run(Replace::class.java, ctx, *args)

        baos.toString().trimIndent() `should be equal to` "vlavlavla"
    }

    @Test
    fun `input file text is replaced`() {
        val loader: ClassPathResourceLoader = ResourceResolver().getLoader(ClassPathResourceLoader::class.java).get()
        val resource = loader.getResource("classpath:bla.txt").get()

        val args = arrayOf("-f", resource.file, "bla", "vla")
        PicocliRunner.run(Replace::class.java, ctx, *args)

        baos.toString().trimIndent().trimIndent() `should be equal to` "vlavlavla"
    }
}
