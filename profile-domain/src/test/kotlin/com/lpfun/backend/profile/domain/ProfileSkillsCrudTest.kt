package com.lpfun.backend.profile.domain

import com.lpfun.backend.common.profile.model.dsl.skills.profileSkills
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubCreate
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubDelete
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubGet
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubUpdate
import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsContext
import com.lpfun.backend.profile.domain.skills.ProfileSkillsCrud
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ProfileSkillsCrudTest {
    val crud = ProfileSkillsCrud()
    @Test
    fun getProfileSkillsCrudTest() {
        val context = ProfileSkillsContext().apply {
            requestProfile.profileId = "test-id"
            stubCaseGet = ProfileStubGet.SUCCESS
        }

        runBlocking {
            crud.get(context)
        }
        assertEquals("test-id", context.requestProfile.profileId)
    }

    @Test
    fun createProfileSkillsCrudTest() {
        val context = ProfileSkillsContext().apply {
            requestProfile = profileSkills {
                dataBases {
                    +"MySql"
                }
                operatingSystems {
                    +"Android"
                }
                specialization {
                    category = "Category"
                    subCategory = "Subcategory"
                }

            }
            stubCaseCreate = ProfileStubCreate.SUCCESS
        }

        runBlocking {
            crud.create(context)
        }

        assertEquals(setOf("MySql"), context.responseProfile.dataBases)
        assertEquals(setOf("Android"), context.responseProfile.operatingSystems)
        assertEquals("Category", context.requestProfile.specialization.category)
        assertEquals("Subcategory", context.requestProfile.specialization.subCategory)
    }

    @Test
    fun updateProfileSkillsCrudTest() {
        val context = ProfileSkillsContext().apply {
            requestProfile = profileSkills {
                specialization {
                    category = "Developer"
                    subCategory = "Backend"
                }
            }
            stubCaseUpdate = ProfileStubUpdate.SUCCESS
        }

        runBlocking {
            crud.update(context)
        }
        assertEquals("Developer", context.responseProfile.specialization.category)
        assertEquals("Backend", context.responseProfile.specialization.subCategory)
    }

    @Test
    fun deleteProfileSkillsCrudTest() {
        val context = ProfileSkillsContext().apply {
            requestProfile.profileId = "test-id"
            stubCaseDelete = ProfileStubDelete.SUCCESS
        }

        runBlocking {
            crud.delete(context)
        }
        assertEquals("", context.responseProfile.profileId)
        assertEquals(setOf<String>(), context.responseProfile.dataBases)
        assertEquals(setOf<String>(), context.responseProfile.operatingSystems)
    }
}