package lasta.page.controller

import io.ktor.resources.Resource
import io.ktor.server.application.call
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import kotlinx.serialization.Serializable

fun Route.routeSamples() {
    // Sample of ContentNegotiation plugin
    get("/json/kotlinx-serialization") {
        call.respond(mapOf("hello" to "world"))
    }

    // Sample of StatusPages plugin
    get("/") {
        call.respondText("Hello World!")
    }

    // Sample of Resources plugin
    get<Articles> { article ->
        // Get all articles ...
        call.respond("List of articles sorted starting from ${article.sort}")
    }
}

@Serializable
@Resource("/articles")
class Articles(val sort: String? = "new")
