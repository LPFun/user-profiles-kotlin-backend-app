package com.lpfun.profile

import com.lpfun.module
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataResponse
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.fail

class ProfilePersonalDataRouteTest {
    @Test
    fun testGet() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "profile/personal?id=12345").apply {
                val responseObj = Json.decodeFromString(
                    KmpProfilePersonalDataResponse.serializer(),
                    response.content ?: fail("Null response")
                )
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("12345", responseObj.data?.profileId)
            }
        }
    }

    @Test
    fun testCreate() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "profile/personal/") {
                addHeader("Content-Type", "application/json")
                val requestBody = """
                    {
                      "firstName": "Test",
                      "middleName": "Test",
                      "lastName": "Test",
                      "displayName": "Test Test",
                      "phone": "+12345",
                      "email": "Test@test.com",
                      "bday": "2000-01-01",
                      "locationModel": {
                        "country": "Test Country",
                        "city": "Test City"
                      }
                    }
                """.trimIndent()
                setBody(requestBody)
            }.apply {
                val responseObj = Json.decodeFromString(
                    KmpProfilePersonalDataResponse.serializer(),
                    response.content ?: fail("Null response")
                )
                assertEquals(HttpStatusCode.OK, response.status())
                assertNotEquals("", responseObj.data?.profileId)
                assertEquals("Test", responseObj.data?.firstName)
            }
        }
    }

    @Test
    fun testUpdate() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Put, "profile/personal/") {
                addHeader("Content-Type", "application/json")
                val requestBody = """{
                  "firstName": "Test1",
                  "middleName": "Test1",
                  "lastName": "Test1",
                  "displayName": "Test1 Test1",
                  "phone": "+12345",
                  "email": "Test@test.com",
                  "bday": "2000-01-01",
                  "locationModel": {
                    "country": "Test Country",
                    "city": "Test City"
                  }
                }""".trimIndent()
                setBody(requestBody)
            }.apply {
                val responseObj = Json.decodeFromString(
                    KmpProfilePersonalDataResponse.serializer(),
                    response.content ?: fail("Null response")
                )
                assertEquals(HttpStatusCode.OK, response.status())
                assertNotEquals("12345", responseObj.data?.profileId)
                assertEquals("Test1", responseObj.data?.firstName)
            }
        }
    }

    @Test
    fun testDelete() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Delete, "profile/personal/") {
                addHeader("Content-Type", "application/json")
                val requestBody = """{
                  "profileId": "12345"
                }""".trimIndent()
                setBody(requestBody)
            }.apply {
                val responseObj = Json.decodeFromString(
                    KmpProfilePersonalDataResponse.serializer(),
                    response.content ?: fail("Null response")
                )
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("", responseObj.data?.profileId)
                assertEquals("", responseObj.data?.firstName)
            }
        }
    }
}