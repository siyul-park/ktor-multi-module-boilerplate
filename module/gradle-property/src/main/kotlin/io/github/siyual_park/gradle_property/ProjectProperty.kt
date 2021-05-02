package io.github.siyual_park.gradle_property

import java.util.Properties

object ProjectProperty {
    private val properties = Properties()
        .apply {
            this.javaClass.getResourceAsStream("/project.properties")?.let { load(it) }
        }

    val version
        get() = properties.getProperty("version") ?: "unspecified"

    val group
        get() = properties.getProperty("group") ?: "unspecified"

    val name
        get() = properties.getProperty("name") ?: "unspecified"

    val description
        get() = properties.getProperty("name") ?: "unspecified"
}
