package com.lpfun.profile

import com.lpfun.module
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataCreate
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataResponse
import com.lpfun.transport.multiplatform.profile.personal.model.KmpLocationModel
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
            handleRequest(HttpMethod.Get, "profile/personal?id=12345") {
                addHeader("test", "test")
            }.apply {
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
                val body = KmpProfilePersonalDataCreate(
                    firstName = "First",
                    middleName = "Middle",
                    lastName = "Last",
                    displayName = "Display Name",
                    phone = "+12345",
                    email = "test@test.com",
                    bday = "2009-01-01",
                    locationModel = KmpLocationModel(
                        country = "Country",
                        city = "City"
                    ),
                    debug = KmpProfilePersonalDataCreate.Debug().apply {
                        stub = KmpProfilePersonalDataCreate.StubCase.RUNNING
                    }
                )
                val bodyStr = Json.encodeToString(KmpProfilePersonalDataCreate.serializer(), body)
                setBody(bodyStr)
            }.apply {
                val responseObj = Json.decodeFromString(
                    KmpProfilePersonalDataResponse.serializer(),
                    response.content ?: fail("Null response")
                )
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("First", responseObj.data?.firstName)
                assertNotEquals("", responseObj.data?.profileId)
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
                  "bday": "2000-01-01",
                  "locationModel": {
                    "country": "Test Country"
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
                assertEquals("2000-01-01", responseObj.data?.bday)
                assertEquals("Test Country", responseObj.data?.locationModel?.country)
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