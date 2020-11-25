package com.lpfun.profile

import com.lpfun.module
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationCreate
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationDelete
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationResponse
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationUpdate
import com.lpfun.transport.multiplatform.profile.education.model.KmpAdditionalEducationModel
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.fail

class ProfileEducationRouteTest {

    @Test
    fun getProfileEducationTest() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/profile/education?id=123") {
                addHeader("test", "test")
            }.apply {
                val getResponse = Json.decodeFromString(
                    KmpProfileEducationResponse.serializer(),
                    response.content ?: fail("Null response")
                )
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("123", getResponse.data?.profileId)
            }
        }
    }

    @Test
    fun createProfileEducationTest() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/profile/education/") {
                addHeader("Content-Type", "application/json")
                val requestBody = KmpProfileEducationCreate(
                    debug = KmpProfileEducationCreate.Debug().apply {
                        stub = KmpProfileEducationCreate.StubCase.RUNNING
                    },
                    additionalEducation = mutableListOf(
                        KmpAdditionalEducationModel(
                            id = "test-id",
                            nameOfInstitution = "OTUS",
                            courseName = "Kotlin",
                            yearOfCompletion = "2020"
                        )
                    )
                )
                val createRequestBody = Json.encodeToString(KmpProfileEducationCreate.serializer(), requestBody)
                setBody(createRequestBody)
            }.apply {
                val responseObj = Json.decodeFromString(
                    KmpProfileEducationResponse.serializer(),
                    response.content ?: fail("Null response")
                )
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(
                    mutableListOf(
                        KmpAdditionalEducationModel(
                            id = "test-id",
                            nameOfInstitution = "OTUS",
                            courseName = "Kotlin",
                            yearOfCompletion = "2020"
                        )
                    ), responseObj.data?.additionalEducation
                )
            }
        }
    }

    @Test
    fun updateProfileEducationTest() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Put, "/profile/education/") {
                addHeader("Content-Type", "application/json")
                val requestBody = KmpProfileEducationUpdate(
                    additionalEducation = mutableListOf(
                        KmpAdditionalEducationModel(
                            id = "test-id",
                            nameOfInstitution = "OTUS",
                            courseName = "Kotlin",
                            yearOfCompletion = "2021"
                        )
                    ),
                    debug = KmpProfileEducationUpdate.Debug().apply {
                        stub = KmpProfileEducationUpdate.StubCase.RUNNING
                    }
                )
                val updateRequestBody = Json.encodeToString(KmpProfileEducationUpdate.serializer(), requestBody)
                setBody(updateRequestBody)
            }.apply {
                val responseObj = Json.decodeFromString(
                    KmpProfileEducationResponse.serializer(),
                    response.content ?: fail("Null response")
                )
                assertEquals(HttpStatusCode.OK, response.status())
                assertNotNull(responseObj.data?.profileId)
                assertEquals(
                    mutableListOf(
                        KmpAdditionalEducationModel(
                            id = "test-id",
                            nameOfInstitution = "OTUS",
                            courseName = "Kotlin",
                            yearOfCompletion = "2021"
                        )
                    ), responseObj.data?.additionalEducation
                )
            }
        }
    }

    @Test
    fun deleteProfileEducationTest() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Delete, "/profile/education/") {
                addHeader("Content-Type", "application/json")
                val requestBody = KmpProfileEducationDelete(
                    profileId = "test-id",
                    debug = KmpProfileEducationDelete.Debug().apply {
                        stub = KmpProfileEducationDelete.StubCase.RUNNING
                    }
                )
                val deleteRequestBody = Json.encodeToString(KmpProfileEducationDelete.serializer(), requestBody)
                setBody(deleteRequestBody)
            }.apply {
                val responseObj = Json.decodeFromString(
                    KmpProfileEducationResponse.serializer(),
                    response.content ?: fail("Null response")
                )
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("", responseObj.data?.profileId)
            }
        }
    }
}