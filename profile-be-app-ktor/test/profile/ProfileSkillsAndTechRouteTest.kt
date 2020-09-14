package com.lpfun.profile

import com.lpfun.module
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechResponse
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.fail

class ProfileSkillsAndTechRouteTest {

    private val uri = "/profile/skills/"

    private fun getResponseObj(response: String?) = Json.decodeFromString(
        KmpProfileSkillsAndTechResponse.serializer(),
        response ?: fail("Null response")
    )

    private fun TestApplicationRequest.addHeaderContentTypeJson() {
        addHeader("Content-Type", "application/json")
    }

    @Test
    fun getProfileSkillsAndTechRouteTest() {

        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "$uri?id=111")
                .apply {
                    val responseObj = getResponseObj(response.content)
                    assertEquals(HttpStatusCode.OK, response.status())
                    assertEquals("111", responseObj.data?.profileId)
                }
        }
    }

    @Test
    fun createProfileSkillsAndTechRouteTest() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, uri) {
                addHeaderContentTypeJson()
                setBody(
                    """
                    {
                      "operatingSystems": [
                        "Test System"
                      ]
                    }
                """.trimIndent()
                )
            }.apply {
                val responseObj = getResponseObj(response.content)
                assertEquals(HttpStatusCode.OK, response.status())
                assertNotEquals("", responseObj.data?.profileId)
                assertEquals("Test System", responseObj.data?.operatingSystems?.first())
            }
        }
    }

    @Test
    fun updateProfileSkillsAndTechRouteTest() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Put, uri) {
                addHeaderContentTypeJson()
                setBody(
                    """
                    {
                      "dataBases": [
                        "Update Data Base"
                      ]
                    }
                """.trimIndent()
                )
            }.apply {
                getResponseObj(response.content).run {
                    assertEquals(HttpStatusCode.OK, response.status())
                    assertEquals("Update Data Base", data?.dataBases?.first())
                }
            }
        }
    }

    @Test
    fun deleteProfileSkillsAndTechRouteTest() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Delete, uri) {
                addHeaderContentTypeJson()
                setBody(
                    """
                        {
                          "profileId": "111"
                        }
                    """.trimIndent()
                )
            }.apply {
                getResponseObj(response.content).run {
                    assertEquals(HttpStatusCode.OK, response.status())
                    assertEquals("", data?.profileId)
                }
            }
        }
    }
}