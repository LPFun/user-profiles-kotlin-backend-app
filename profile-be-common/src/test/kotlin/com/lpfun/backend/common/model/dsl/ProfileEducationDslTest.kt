package com.lpfun.backend.common.model.dsl

import com.lpfun.backend.common.model.dsl.education.profileEducation
import com.lpfun.backend.common.model.extensions.applyRequest
import com.lpfun.backend.common.model.profile.education.ProfileEducation
import com.lpfun.backend.common.model.profile.education.ProfileEducationContext
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
    fun profileEducationTest() {
        profileTests(profileEducation)
    }

    @Test
    fun infixContextTest() {
        val context = ProfileEducationContext()
        context applyRequest profileEducation
        profileTests(context.requestProfile)
    }

    private fun profileTests(profile: ProfileEducation) {
        assertEquals(profile.mainEducation.first().university, "First university")
        assertEquals(profile.mainEducation.get(1).university, "Second university")
    }
}