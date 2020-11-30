package com.lpfun.backend.common.model.dsl

import com.lpfun.backend.common.profile.model.dsl.education.profileEducation
import com.lpfun.backend.common.profile.model.extensions.applyRequest
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducation
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducationContext
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ProfileEducationDslTest {
    private val profileEducation = profileEducation {
        +mainEducation {
            university = "First university"
            department = "First department"
            speciality = "First speciality"
            yearOfCompletion = "First year"
        }
        +mainEducation {
            university = "Second university"
            department = "Second department"
            speciality = "Second speciality"
            yearOfCompletion = "Second year"
        }
        +additionalEducation {
            nameOfInstitution = "First name"
            courseName = "First course"
            yearOfCompletion = "First year"
        }
    }

    @Test
    fun profileEducationSuccessTest() {
        profileEducationFieldsSuccessTests(profileEducation)
    }

    @Test
    fun infixContextSuccessTest() {
        val context = ProfileEducationContext()
        context applyRequest profileEducation
        profileEducationFieldsSuccessTests(context.requestProfile)
    }

    private fun profileEducationFieldsSuccessTests(profile: ProfileEducation) {
        assertEquals(profile.mainEducation.first().university, "First university")
        assertEquals(profile.mainEducation.first().department, "First department")
        assertEquals(profile.mainEducation.first().specialty, "First speciality")
        assertEquals(profile.mainEducation.first().yearOfCompletion, "First year")
        assertEquals(profile.mainEducation.get(1).university, "Second university")
        assertEquals(profile.mainEducation.get(1).department, "Second department")
        assertEquals(profile.mainEducation.get(1).specialty, "Second speciality")
        assertEquals(profile.mainEducation.get(1).yearOfCompletion, "Second year")
        assertEquals(profile.additionalEducation.first().nameOfInstitution, "First name")
        assertEquals(profile.additionalEducation.first().courseName, "First course")
        assertEquals(profile.additionalEducation.first().yearOfCompletion, "First year")
    }
}