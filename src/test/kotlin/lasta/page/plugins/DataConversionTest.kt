package lasta.page.plugins

import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.resources.Resource
import io.ktor.server.application.call
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.testing.testApplication
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.junit.Test
import java.util.UUID
import kotlin.test.assertEquals


class DataConversionTest {

    @Test
    fun testConvertingUUID() = testApplication {
        routing {
            get<TestConvertUUIDController> {
                val uuid = it.uuid
                call.respond(
                    status = HttpStatusCode.OK, message = "uuid" to uuid
                )
            }
        }

        client.get("/test/convert/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(("uuid" to UUID_VALUE.toString()).toString(), bodyAsText())
        }
    }

    companion object {
        private val UUID_VALUE = UUID.fromString("0-0-0-0-0")
    }
}

@Serializable
@Resource("/test/convert/{uuid}")
class TestConvertUUIDController(@Contextual val uuid: UUID)
