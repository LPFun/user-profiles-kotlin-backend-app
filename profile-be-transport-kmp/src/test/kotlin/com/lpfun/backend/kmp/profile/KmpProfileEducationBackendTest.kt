package com.lpfun.backend.kmp.profile

import com.lpfun.backend.common.model.profile.ProfileContext
import com.lpfun.backend.common.model.profile.ProfileEducation
import com.lpfun.transport.multiplatform.profile.education.*
import com.lpfun.transport.multiplatform.profile.education.model.KmpAdditionalEducationModel
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

internal class KmpProfileEducationBackendTest {

    val businessLayer = ProfileEducationBusinessLayer()
    val getRequest = KmpProfileEducationGet(profileId = "test-id")
    var create = KmpProfileEducationCreate(
        additionalEducation = mutableListOf(
            KmpAdditionalEducationModel(
                nameOfInstitution = "OTUS",
                courseName = "Kotlin backend",
                yearOfCompletion = "2020"
            )
        )
    )
    val update = KmpProfileEducationUpdate(
        additionalEducation = mutableListOf(
            KmpAdditionalEducationModel(
                nameOfInstitution = "OTUS",
                courseName = "IOS development",
                yearOfCompletion = "2021"
            )
        )
    )
    val delete = KmpProfileEducationDelete(profileId = "delete-id")

    @Test
    fun `call get in controller`() {
        val context = ProfileContext()
        val result = runBlocking {
            businessLayer.get(context.setQuery(getRequest)).resultItem<KmpProfileEducationResponse>()
        }
        assertEquals("test-id", result.data?.id)
    }

    @Test
    fun `call create in controller`() {
        val context = ProfileContext()
        val result = runBlocking {
            businessLayer.create(context.setQuery(create)).resultItem<KmpProfileEducationResponse>()
        }
        assertEquals(result.data?.id, "test-id")
        assertEquals(
            result.data?.additionalEducation!![0],
            KmpAdditionalEducationModel(
                nameOfInstitution = "OTUS",
                courseName = "Kotlin backend",
                yearOfCompletion = "2020"
            )
        )
    }

    @Test
    fun `call update in controller`() {
        val context = ProfileContext()
        val result = runBlocking {
            businessLayer.update(context.setQuery(update)).resultItem<KmpProfileEducationResponse>()
        }
        assertEquals(
            result.data?.additionalEducation!![0],
            KmpAdditionalEducationModel(
                nameOfInstitution = "OTUS",
                courseName = "IOS development",
                yearOfCompletion = "2021"
            )

        )
    }

    @Test
    fun `call delete in controller`() {
        val context = ProfileContext()
        val result = runBlocking {
            businessLayer.delete(context.setQuery(delete)).resultItem<KmpProfileEducationResponse>()
        }
        assertEquals(result.data?.id, "delete-id")
    }

    class ProfileEducationBusinessLayer() {
        fun get(context: ProfileContext) = context.apply {
            responseProfile = ProfileEducation(
                id = "test-id"
            )
        }

        fun delete(context: ProfileContext) = context.apply {
            responseProfile = ProfileEducation(id = requestProfileId)
        }


        fun update(context: ProfileContext) = context.apply {
            responseProfile = (requestProfile as ProfileEducation).copy()
        }


        fun create(context: ProfileContext) = context.apply {
            responseProfile = (requestProfile as ProfileEducation).copy(id = "test-id")
        }
    }
}


