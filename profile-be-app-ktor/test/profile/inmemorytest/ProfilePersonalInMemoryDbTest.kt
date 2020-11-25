package com.lpfun.profile.inmemorytest

import com.lpfun.module
import com.lpfun.transport.multiplatform.profile.KmpProfileDbMode
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataCreate
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataDelete
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataResponse
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataUpdate
import com.lpfun.transport.multiplatform.profile.personal.model.KmpLocationModel
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.fail

internal class ProfilePersonalInMemoryDbTest {

    @Test
    fun testGet() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "profile/personal?id=test-id") {
                addHeader("test", "inmemory")
            }.apply {
                val responseObj = Json.decodeFromString(
                    KmpProfilePersonalDataResponse.serializer(),
                    response.content ?: fail("Null response")
                )
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("test-id", responseObj.data?.profileId)
            }
        }
    }

    @Test
    fun createSuccessTest() {
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
                        db = KmpProfileDbMode.TEST
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
                assertEquals("Middle", responseObj.data?.middleName)
                assertEquals("Last", responseObj.data?.lastName)
                assertEquals("Country", responseObj.data?.locationModel?.country)
                assertNotEquals("", responseObj.data?.profileId)
            }
        }
    }

    @Test
    fun testUpdate() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Put, "profile/personal/") {
                addHeader("Content-Type", "application/json")
                val body = KmpProfilePersonalDataUpdate(
                    profileId = "test-id",
                    firstName = "Updated First",
                    bday = "2000-01-01",
                    locationModel = KmpLocationModel(
                        country = "Updated Country"
                    ),
                    debug = KmpProfilePersonalDataUpdate.Debug().apply {
                        db = KmpProfileDbMode.TEST
                    }
                )
                val bodyStr = Json.encodeToString(KmpProfilePersonalDataUpdate.serializer(), body)
                setBody(bodyStr)
            }.apply {
                val responseObj = Json.decodeFromString(
                    KmpProfilePersonalDataResponse.serializer(),
                    response.content ?: fail("Null response")
                )
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Updated First", responseObj.data?.firstName)
                assertEquals("2000-01-01", responseObj.data?.bday)
                assertEquals("Updated Country", responseObj.data?.locationModel?.country)
            }
        }
    }

    @Test
    fun testDelete() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Delete, "profile/personal/") {
                addHeader("Content-Type", "application/json")
                val body = KmpProfilePersonalDataDelete(
                    profileId = "test-id",
                    debug = KmpProfilePersonalDataDelete.Debug().apply {
                        db = KmpProfileDbMode.TEST
                    }
                )
                val requestBody = Json.encodeToString(KmpProfilePersonalDataDelete.serializer(), body)
                setBody(requestBody)
            }.apply {
                val responseObj = Json.decodeFromString(
                    KmpProfilePersonalDataResponse.serializer(),
                    response.content ?: fail("Null response")
                )
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("test-id", responseObj.data?.profileId)
            }
        }
    }
}