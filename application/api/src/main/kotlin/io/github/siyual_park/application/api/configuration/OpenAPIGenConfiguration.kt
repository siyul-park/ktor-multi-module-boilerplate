package io.github.siyual_park.application.api.configuration

import com.papsign.ktor.openapigen.OpenAPIGen
import com.papsign.ktor.openapigen.schema.namer.DefaultSchemaNamer
import com.papsign.ktor.openapigen.schema.namer.SchemaNamer
import io.github.siyual_park.gradle_property.ProjectProperty
import kotlin.reflect.KType

fun OpenAPIGen.Configuration.init() {
    info {
        version = ProjectProperty.version
        title = ProjectProperty.name
        description = ProjectProperty.description
    }

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
