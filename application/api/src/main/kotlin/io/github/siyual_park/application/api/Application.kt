package io.github.siyual_park.application.api

import com.papsign.ktor.openapigen.OpenAPIGen
import com.papsign.ktor.openapigen.schema.namer.DefaultSchemaNamer
import com.papsign.ktor.openapigen.schema.namer.SchemaNamer
import io.github.siyual_park.application.api.routes.documentRoutes
import io.github.siyual_park.application.api.routes.pingRoutes
import io.github.siyual_park.gradle_property.ProjectProperty
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.Routing
import io.ktor.server.netty.EngineMain
import kotlin.reflect.KType

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson()
    }

    install(OpenAPIGen) {
        info {
            version = ProjectProperty.version
            title = ProjectProperty.name
            description = ProjectProperty.description
        }

        server("http://localhost:8081/") {
            description = "Local server"
        }

        // rename DTOs from java type name to generator compatible form
        replaceModule(
            DefaultSchemaNamer,
            object : SchemaNamer {
                val regex = Regex("[A-Za-z0-9_.]+")

                override fun get(type: KType): String {
                    return type.toString().replace(regex) { it.value.split(".").last() }.replace(Regex(">|<|, "), "_")
                }
            }
        )
    }

    install(Routing) {
        documentRoutes()
        pingRoutes()
    }
}
