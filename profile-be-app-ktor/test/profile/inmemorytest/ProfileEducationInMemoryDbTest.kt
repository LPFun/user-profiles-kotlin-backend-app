package com.lpfun.profile.inmemorytest

import com.lpfun.module
import com.lpfun.transport.multiplatform.profile.KmpProfileDbMode
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationCreate
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationDelete
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationResponse
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationUpdate
import com.lpfun.transport.multiplatform.profile.education.model.KmpAdditionalEducationModel
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.fail

internal class ProfileEducationInMemoryDbTest {

    @Test
    fun getProfileEducationTest() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/profile/education?id=test-id") {
                addHeader("test", "inmemory")
            }.apply {
                val getResponse = Json.decodeFromString(
                    KmpProfileEducationResponse.serializer(),
                    response.content ?: fail("Null response")
                )
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("test-id", getResponse.data?.profileId)
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
                        db = KmpProfileDbMode.TEST
                    },
                    additionalEducation = mutableListOf(
                        KmpAdditionalEducationModel(
                            nameOfInstitution = "Created Name Of Institution",
                            courseName = "Created Course Name",
                            yearOfCompletion = "Created Year Of Completion"
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
                val createdId = responseObj.data?.profileId
                assertEquals(
                    mutableListOf(
                        KmpAdditionalEducationModel(
                            id = createdId,
                            nameOfInstitution = "Created Name Of Institution",
                            courseName = "Created Course Name",
                            yearOfCompletion = "Created Year Of Completion"
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
                    profileId = "test-id",
                    additionalEducation = mutableListOf(
                        KmpAdditionalEducationModel(
                            id = "test-id",
                            nameOfInstitution = "Updated Name Of Institution",
                            courseName = "Updated Course Name",
                            yearOfCompletion = "Updated Year Of Completion"
                        )
                    ),
                    debug = KmpProfileEducationUpdate.Debug().apply {
                        db = KmpProfileDbMode.TEST
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
                            nameOfInstitution = "Updated Name Of Institution",
                            courseName = "Updated Course Name",
                            yearOfCompletion = "Updated Year Of Completion"
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
                        db = KmpProfileDbMode.TEST
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
                assertEquals("test-id", responseObj.data?.profileId)
            }
        }
    }
}