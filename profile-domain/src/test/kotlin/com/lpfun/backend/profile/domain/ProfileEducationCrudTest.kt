package com.lpfun.backend.profile.domain

import com.lpfun.backend.common.model.dsl.education.profileEducation
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubCreate
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubDelete
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubUpdate
import com.lpfun.backend.common.model.profile.education.AdditionalEducationModel
import com.lpfun.backend.common.model.profile.education.EducationModel
import com.lpfun.backend.common.model.profile.education.ProfileEducationContext
import com.lpfun.backend.profile.domain.education.ProfileEducationCrud
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ProfileEducationCrudTest {
    @Test
    fun getProfileEducationTest() {
        val context = ProfileEducationContext().apply {
            requestProfile.profileId = "test-id"
        }

        val crud = ProfileEducationCrud()

        runBlocking {
            crud.get(context)
        }

        assertEquals("test-id", context.responseProfile.profileId)
    }

    @Test
    fun createProfileEducationTest() {
        val context = ProfileEducationContext().apply {
            requestProfile = profileEducation {
                +mainEducation {
                    university = "University"
                    department = "Department"
                    speciality = "Speciality"
                    yearOfCompletion = "2020"
                }
                +additionalEducation {
                    nameOfInstitution = "Name of Institution"
                    courseName = "Course Name"
                    yearOfCompletion = "2020"
                }
            }
            stubCaseCreate = ProfileStubCreate.SUCCESS
        }

        val crud = ProfileEducationCrud()

        runBlocking {
            crud.create(context)
        }

        assertEquals("test-id", context.responseProfile.profileId)
        assertEquals(
            mutableListOf(
                EducationModel(
                    university = "University",
                    department = "Department",
                    specialty = "Speciality",
                    yearOfCompletion = "2020"
                )
            ), context.responseProfile.mainEducation
        )
    }

    @Test
    fun updateProfileEducationTest() {
        val context = ProfileEducationContext().apply {
            requestProfile = profileEducation {
                +additionalEducation {
                    nameOfInstitution = "Name of Institution"
                    courseName = "Course Name"
                    yearOfCompletion = "2020"
                }
            }
            stubCaseUpdate = ProfileStubUpdate.SUCCESS
        }

        val crud = ProfileEducationCrud()

        runBlocking {
            crud.update(context)
        }

        assertEquals(
            mutableListOf(
                AdditionalEducationModel(
                    nameOfInstitution = "Name of Institution",
                    courseName = "Course Name",
                    yearOfCompletion = "2020"
                )
            ), context.responseProfile.additionalEducation
        )
    }

    @Test
    fun deleteProfileEducationTest() {
        val context = ProfileEducationContext().apply {
            requestProfile.profileId = "test-id"
            stubCaseDelete = ProfileStubDelete.SUCCESS
        }

        val crud = ProfileEducationCrud()

        runBlocking {
            crud.delete(context)
        }

        assertEquals("", context.responseProfile.profileId)
        assertEquals(mutableListOf(), context.responseProfile.mainEducation)
    }
}