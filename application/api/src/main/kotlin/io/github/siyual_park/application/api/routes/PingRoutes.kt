package io.github.siyual_park.application.api.routes

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get

fun Route.pingRoutes() {
    get("/ping") {
        call.respond("pong")
    }
}
