package elegant.example

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {

        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }

    @Test
    fun invalidPriorityProduces400() = testApplication {

        client.get("/tasks/byPriority/Invalid").apply {
            assertEquals(HttpStatusCode.BadRequest, this.status)

        }
    }

    @Test
    fun tasksCanBeFoundByPriority() = testApplication {

        client.get("/tasks/byPriority/Medium").apply {
            val body = this.bodyAsText()

            assertEquals(HttpStatusCode.OK, this.status)
            assertContains(body, "Mow the lawn")
            assertContains(body, "Paint the fence")

        }
    }

    @Test
    fun unusedPriorityProduces404() = testApplication {
        client.get("/tasks/byPriority/Vital").apply {
            assertEquals(HttpStatusCode.NotFound, this.status)
        }
    }
}
