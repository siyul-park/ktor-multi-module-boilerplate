package io.github.siyual_park.application.api.integration

import io.github.siyual_park.application.api.module
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.Assert.assertEquals
import org.junit.Test

class PingRoutesTests {
    @Test
    fun testGetRoot() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/ping").apply {
                assertEquals(response.content, "pong")
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }
}
