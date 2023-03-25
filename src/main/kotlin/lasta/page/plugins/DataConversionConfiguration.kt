package lasta.page.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.dataconversion.DataConversion
import java.util.UUID

fun Application.configureDataConversion() {

    install(DataConversion) {
        convert<UUID> {
            decode {
                UUID.fromString(it.first())
            }
            encode {
                listOf(it.toString())
            }
        }
    }
}
