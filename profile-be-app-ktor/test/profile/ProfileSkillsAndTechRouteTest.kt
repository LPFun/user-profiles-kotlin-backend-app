package com.lpfun.profile

import com.lpfun.module
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechCreate
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechDelete
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechResponse
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechUpdate
import com.lpfun.transport.multiplatform.profile.skills.model.KmpDataBaseModel
import com.lpfun.transport.multiplatform.profile.skills.model.KmpOperatingSystemModel
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
            handleRequest(HttpMethod.Get, "$uri?id=111") {
                addHeader("stub", "success")
            }.apply {
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
                val requestBody = KmpProfileSkillsAndTechCreate(
                    operatingSystems = mutableSetOf(KmpOperatingSystemModel(operatingSystem = "Test System")),
                    debug = KmpProfileSkillsAndTechCreate.Debug().apply {
                        stub = KmpProfileSkillsAndTechCreate.StubCase.SUCCESS
                    }
                )
                val body = Json.encodeToString(KmpProfileSkillsAndTechCreate.serializer(), requestBody)
                setBody(body)
            }.apply {
                val responseObj = getResponseObj(response.content)
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Test System", responseObj.data?.operatingSystems?.first()?.operatingSystem)
                assertNotEquals("", responseObj.data?.profileId)
            }
        }
    }

    @Test
    fun updateProfileSkillsAndTechRouteTest() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Put, uri) {
                addHeaderContentTypeJson()
                val requestBody = KmpProfileSkillsAndTechUpdate(
                    dataBases = mutableSetOf(KmpDataBaseModel(dataBase = "Update Data Base")),
                    debug = KmpProfileSkillsAndTechUpdate.Debug().apply {
                        stub = KmpProfileSkillsAndTechUpdate.StubCase.SUCCESS
                    }
                )
                val body = Json.encodeToString(KmpProfileSkillsAndTechUpdate.serializer(), requestBody)
                setBody(body)
            }.apply {
                getResponseObj(response.content).run {
                    assertEquals(HttpStatusCode.OK, response.status())
                    assertEquals("Update Data Base", data?.dataBases?.first()?.dataBase)
                }
            }
        }
    }

    @Test
    fun deleteProfileSkillsAndTechRouteTest() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Delete, uri) {
                addHeaderContentTypeJson()
                val requestBody = KmpProfileSkillsAndTechDelete(
                    profileId = "test-id",
                    debug = KmpProfileSkillsAndTechDelete.Debug().apply {
                        stub = KmpProfileSkillsAndTechDelete.StubCase.SUCCESS
                    }
                )
                val body = Json.encodeToString(KmpProfileSkillsAndTechDelete.serializer(), requestBody)
                setBody(body)
            }.apply {
                getResponseObj(response.content).run {
                    assertEquals(HttpStatusCode.OK, response.status())
                    assertEquals("", data?.profileId)
                }
            }
        }
    }
}