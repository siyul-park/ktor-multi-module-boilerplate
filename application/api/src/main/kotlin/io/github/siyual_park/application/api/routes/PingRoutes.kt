package io.github.siyual_park.application.api.routes

import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import com.papsign.ktor.openapigen.route.path.normal.get
import com.papsign.ktor.openapigen.route.response.respond
import com.papsign.ktor.openapigen.route.route

fun NormalOpenAPIRoute.pingRoutes() {
    route("/ping") {
        get<Unit, String> {
            respond("pong")
        }
    }
}
