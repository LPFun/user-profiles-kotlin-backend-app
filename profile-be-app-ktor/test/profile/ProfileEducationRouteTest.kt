package com.lpfun.profile

import com.lpfun.module
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationResponse
import com.lpfun.transport.multiplatform.profile.education.model.KmpAdditionalEducationModel
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class ProfileEducationRouteTest {

    @Test
    fun getProfileEducationTest() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/profile/education?id=123")
                .apply {
                    val getResponse = Json.decodeFromString(
                        KmpProfileEducationResponse.serializer(),
                        response.content ?: fail("Null response")
                    )
                    assertEquals(HttpStatusCode.OK, response.status())
                    assertEquals("123", getResponse.data?.id)
                }
        }
    }

    @Test
    fun createProfileEducationTest() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/profile/education/") {
                addHeader("Content-Type", "application/json")
                val createRequestBody = """
                    {
                      "additionalEducation": [
                        {
                          "nameOfInstitution": "OTUS",
                          "courseName": "Kotlin",
                          "yearOfCompletion": "2020"
                        }
                      ]
                    }
                """.trimIndent()
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
                val updateRequestBody = """
                    {
                      "additionalEducation": [
                        {
                          "nameOfInstitution": "OTUS",
                          "courseName": "Kotlin",
                          "yearOfCompletion": "2021"
                        }
                      ]
                    }
                """.trimIndent()
                setBody(updateRequestBody)
            }.apply {
                val responseObj = Json.decodeFromString(
                    KmpProfileEducationResponse.serializer(),
                    response.content ?: fail("Null response")
                )
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("123", responseObj.data?.id)
                assertEquals(
                    mutableListOf(
                        KmpAdditionalEducationModel(
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
                val deleteRequestBody = """
                    {
                        "profileId": "123"
                    }
                """.trimIndent()
                setBody(deleteRequestBody)
            }.apply {
                val responseObj = Json.decodeFromString(
                    KmpProfileEducationResponse.serializer(),
                    response.content ?: fail("Null response")
                )
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("", responseObj.data?.id)
            }
        }
    }
}