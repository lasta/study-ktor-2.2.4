package lasta.page.plugins

import io.ktor.server.application.Application
import io.ktor.server.plugins.openapi.openAPI
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.routing.routing

fun Application.configureOpenAPI() {
    routing {
        openAPI(path = "openapi")
    }
    routing {
        swaggerUI(path = "swagger-ui")
    }
}
