package lasta.page

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.resources.Resources
import io.ktor.server.response.respondText
import io.ktor.server.routing.routing
import lasta.page.controller.routeDatabaseSamples
import lasta.page.controller.routeSamples
import lasta.page.plugins.configureDataConversion
import lasta.page.plugins.configureMonitoring
import lasta.page.plugins.configureOpenAPI
import lasta.page.plugins.configureSerialization

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureOpenAPI()
    configureMonitoring()
    configureSerialization()
    configureDataConversion()

    install(Resources)
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }

    routing {
        routeSamples()
        routeDatabaseSamples()
    }
}
