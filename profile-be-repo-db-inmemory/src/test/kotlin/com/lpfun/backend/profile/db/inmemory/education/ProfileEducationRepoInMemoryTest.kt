package com.lpfun.backend.profile.db.inmemory.education

import com.lpfun.backend.common.profile.model.dsl.education.profileEducation
import com.lpfun.backend.profile.db.inmemory.DataBaseInMemFactory
import kotlinx.coroutines.runBlocking
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ProfileEducationRepoInMemoryTest {

    private val repo = ProfileEducationRepoInMemory(DataBaseInMemFactory.init())
    private val testProfileId = "test-profile-id"

    @BeforeTest
    fun create() {
        val profile = profileEducation {
            id = testProfileId
            +mainEducation {
                id = testProfileId
                university = "University"
                department = "Department"
                speciality = "Speciality"
                yearOfCompletion = "Year of Completion"
            }
            +additionalEducation {
                id = testProfileId
                nameOfInstitution = "Name of Institution"
                courseName = "Course Name"
                yearOfCompletion = "Year of Completion"
            }
        }
        runBlocking {
            repo.create(profile)
        }
    }

    @AfterTest
    fun delete() {
        runBlocking {
            repo.delete(testProfileId)
        }
    }

    @Test
    fun getSuccessTest() {
        runBlocking {
            val createdProfile = repo.get(testProfileId)
            assertEquals("University", createdProfile.mainEducation.first().university)
            assertEquals("Department", createdProfile.mainEducation.first().department)
            assertEquals("Speciality", createdProfile.mainEducation.first().specialty)
            assertEquals("Year of Completion", createdProfile.mainEducation.first().yearOfCompletion)

            assertEquals("Name of Institution", createdProfile.additionalEducation.first().nameOfInstitution)
            assertEquals("Course Name", createdProfile.additionalEducation.first().courseName)
            assertEquals("Year of Completion", createdProfile.additionalEducation.first().yearOfCompletion)
        }
    }

    @Test
    fun updateSuccessTest() {
        val profile = profileEducation {
            id = testProfileId
            +mainEducation {
                id = testProfileId
                university = "Updated University"
                department = "Updated Department"
                speciality = "Updated Speciality"
                yearOfCompletion = "Updated Year"
            }
        }
        runBlocking {
            val updatedProfile = repo.update(profile)
            assertEquals("Updated University", updatedProfile.mainEducation.first().university)
            assertEquals("Updated Department", updatedProfile.mainEducation.first().department)
            assertEquals("Updated Speciality", updatedProfile.mainEducation.first().specialty)
            assertEquals("Updated Year", updatedProfile.mainEducation.first().yearOfCompletion)
        }
    }
}