package io.github.siyual_park.application.api.routes

import com.papsign.ktor.openapigen.openAPIGen
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.routing.application
import io.ktor.routing.get

fun Route.documentRoutes() {
    val route = this

    get("/openapi.json") {
        call.respond(route.application.openAPIGen.api.serialize())
    }
    get("/") {
        call.respondRedirect("/swagger-ui/index.html?url=/openapi.json", true)
    }
}
