package com.lpfun.backend.kmp.profile

import com.lpfun.backend.common.profile.model.profile.education.ProfileEducation
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducationContext
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationCreate
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationDelete
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationGet
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationUpdate
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
        val context = ProfileEducationContext()
        val result = runBlocking {
            businessLayer.get(context.setQuery(getRequest)).resultItem()
        }
        assertEquals("test-id", result.data?.profileId)
    }

    @Test
    fun `call create in controller`() {
        val context = ProfileEducationContext()
        val result = runBlocking {
            businessLayer.create(context.setQuery(create)).resultItem()
        }
        assertEquals(result.data?.profileId, "test-id")
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
        val context = ProfileEducationContext()
        val result = runBlocking {
            businessLayer.update(context.setQuery(update)).resultItem()
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
        val context = ProfileEducationContext()
        val result = runBlocking {
            businessLayer.delete(context.setQuery(delete)).resultItem()
        }
        assertEquals(result.data?.profileId, "delete-id")
    }

    class ProfileEducationBusinessLayer {
        fun get(context: ProfileEducationContext) = context.apply {
            responseProfile = ProfileEducation(
                profileId = "test-id"
            )
        }

        fun delete(context: ProfileEducationContext) = context.apply {
            responseProfile = ProfileEducation(profileId = requestProfileId)
        }


        fun update(context: ProfileEducationContext) = context.apply {
            responseProfile = requestProfile.copy()
        }


        fun create(context: ProfileEducationContext) = context.apply {
            responseProfile = requestProfile.copy(profileId = "test-id")
        }
    }
}


