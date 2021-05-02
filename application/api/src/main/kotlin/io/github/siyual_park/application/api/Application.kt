package io.github.siyual_park.application.api

import io.github.siyual_park.application.api.routes.rootRoutes
import io.github.siyual_park.application.api.routes.pingRoutes
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.routing.Routing
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(Routing) {
        rootRoutes()
        pingRoutes()
    }
}
