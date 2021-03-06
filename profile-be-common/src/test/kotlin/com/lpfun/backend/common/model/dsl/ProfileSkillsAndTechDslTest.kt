package com.lpfun.backend.common.model.dsl

import com.lpfun.backend.common.profile.model.dsl.skills.profileSkills
import com.lpfun.backend.common.profile.model.extensions.applyRequest
import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsAndTech
import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsContext
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class ProfileSkillsAndTechDslTest {
    private val profileSkills = profileSkills {
        id = "testId"
        specialization {
            category = "Category"
            subCategory = "Sub category"
        }
        operatingSystems {
            +"system 1"
            add("system 2")
        }
        dataBases {
            +"base 1"
            add("base 2")
        }
    }

    @Test
    fun profileSkillsDslSuccessTest() {
        profileSkillsFieldsSuccessTest(profileSkills)
    }

    @Test
    fun profileContextInfixSuccessTest() {
        val context = ProfileSkillsContext()
        context applyRequest profileSkills
        profileSkillsFieldsSuccessTest(context.requestProfile)
    }

    private fun profileSkillsFieldsSuccessTest(profileSkills: ProfileSkillsAndTech) {
        assertEquals(profileSkills.profileId, "testId")
        assertEquals(profileSkills.specialization.category, "Category")
        assertEquals(profileSkills.specialization.subCategory, "Sub category")
        assertTrue(profileSkills.operatingSystems.containsAll(listOf("system 1", "system 2")))
        assertTrue(profileSkills.dataBases.containsAll(listOf("base 1", "base 2")))
    }
}