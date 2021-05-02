package io.github.siyual_park.application.api

import com.papsign.ktor.openapigen.OpenAPIGen
import com.papsign.ktor.openapigen.route.apiRouting
import io.github.siyual_park.application.api.configuration.init
import io.github.siyual_park.application.api.routes.documentRoutes
import io.github.siyual_park.application.api.routes.pingRoutes
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.routing
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson()
    }

    install(OpenAPIGen) {
        init()
    }

    routing {
        documentRoutes()
    }

    apiRouting {
        pingRoutes()
    }
}
