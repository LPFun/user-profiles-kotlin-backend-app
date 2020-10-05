package com.lpfun.backend.common.model.dsl

import com.lpfun.backend.common.model.dsl.skills.profileSkills
import com.lpfun.backend.common.model.extensions.applyRequest
import com.lpfun.backend.common.model.profile.skills.ProfileSkillsAndTech
import com.lpfun.backend.common.model.profile.skills.ProfileSkillsContext
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
    fun profileSkillsDslTest() {
        profileSkillsFieldsTest(profileSkills)
    }

    @Test
    fun profileContextInfixText() {
        val context = ProfileSkillsContext()
        context applyRequest profileSkills
        profileSkillsFieldsTest(context.requestProfile)
    }

    private fun profileSkillsFieldsTest(profileSkills: ProfileSkillsAndTech) {
        assertEquals(profileSkills.profileId, "testId")
        assertEquals(profileSkills.specialization.category, "Category")
        assertTrue(profileSkills.operatingSystems.containsAll(listOf("system 1", "system 2")))
        assertTrue(profileSkills.dataBases.containsAll(listOf("base 1", "base 2")))
    }
}