package com.lpfun.backend.profile.db.inmemory.education

import com.lpfun.backend.common.profile.model.dsl.education.profileEducation
import com.lpfun.backend.profile.db.education.ProfileEducationRepository
import com.lpfun.backend.profile.db.postgres.ProfilePostgresContainer
import kotlinx.coroutines.runBlocking
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ProfileEducationRepoPostgresTest {
    var repo: ProfileEducationRepository? = null

    @BeforeTest
    fun initRepo() {
        ProfilePostgresContainer.start()
        repo = ProfileEducationRepository()
    }

    @Test
    fun create() {
        val profile = profileEducation {
            +mainEducation {
                university = "University"
                department = "Department"
                speciality = "Speciality"
                yearOfCompletion = "Year of Completion"
            }
            +additionalEducation {
                nameOfInstitution = "Name of Institution"
                courseName = "Course Name"
                yearOfCompletion = "Year of Completion"
            }
        }

        runBlocking {
            val createdProfile = repo?.create(profile)
            assertEquals("University", createdProfile?.mainEducation?.first()?.university)
            assertEquals("Department", createdProfile?.mainEducation?.first()?.department)
            assertEquals("Speciality", createdProfile?.mainEducation?.first()?.specialty)
            assertEquals("Year of Completion", createdProfile?.mainEducation?.first()?.yearOfCompletion)

            assertEquals("Name of Institution", createdProfile?.additionalEducation?.first()?.nameOfInstitution)
            assertEquals("Course Name", createdProfile?.additionalEducation?.first()?.courseName)
            assertEquals("Year of Completion", createdProfile?.additionalEducation?.first()?.yearOfCompletion)
        }
    }

    @Test
    fun update() {
        val profile = profileEducation {
            +mainEducation {
                id = "main-ed-test-id"
                university = "University"
                department = "Department"
                speciality = "Speciality"
                yearOfCompletion = "Year of Completion"
            }
            +additionalEducation {
                nameOfInstitution = "Name of Institution"
                courseName = "Course Name"
                yearOfCompletion = "Year of Completion"
            }
        }
        runBlocking {
            val createdProfile = repo?.create(profile)!!
            val updatedMainEducation = createdProfile.mainEducation.first().apply {
                id = "main-ed-test-id"
                university = "Updated University"
                department = "Updated Department"
                specialty = "Updated Speciality"
                yearOfCompletion = "Updated Year"
            }
            createdProfile.apply {
                mainEducation = mutableListOf(updatedMainEducation)
            }
            val updatedProfile = repo?.update(createdProfile)!!
            assertEquals("Updated University", updatedProfile.mainEducation.first().university)
            assertEquals("Updated Department", updatedProfile.mainEducation.first().department)
            assertEquals("Updated Speciality", updatedProfile.mainEducation.first().specialty)
            assertEquals("Updated Year", updatedProfile.mainEducation.first().yearOfCompletion)
        }

    }

    @Test
    fun delete() {
        val profile = profileEducation {
            +mainEducation {
                university = "University"
                department = "Department"
                speciality = "Speciality"
                yearOfCompletion = "Year of Completion"
            }
            +mainEducation {
                university = "University"
                department = "Department"
                speciality = "Speciality"
                yearOfCompletion = "Year of Completion"
            }
            +additionalEducation {
                nameOfInstitution = "Name of Institution"
                courseName = "Course Name"
                yearOfCompletion = "Year of Completion"
            }
        }
        runBlocking {
            val createdProfile = repo?.create(profile)!!
            repo?.delete(createdProfile.profileId)
        }

    }

    @AfterTest
    fun closeDb() {
        ProfilePostgresContainer.stop()
        repo = null
    }
}