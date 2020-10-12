package com.lpfun.backend.profile.domain

import com.lpfun.backend.common.model.dsl.skills.profileSkills
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubCreate
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubDelete
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubGet
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubUpdate
import com.lpfun.backend.common.model.profile.skills.ProfileSkillsContext
import com.lpfun.backend.profile.domain.skills.ProfileSkillsCrud
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ProfileSkillsCrudTest {
    @Test
    fun getProfileSkillsCrudTest() {
        val context = ProfileSkillsContext().apply {
            requestProfile.profileId = "test-id"
            stubCaseGet = ProfileStubGet.RUNNING
        }

        val crud = ProfileSkillsCrud()

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
            stubCaseCreate = ProfileStubCreate.RUNNING
        }

        val crud = ProfileSkillsCrud()

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
            stubCaseUpdate = ProfileStubUpdate.RUNNING
        }
        val crud = ProfileSkillsCrud()
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
            stubCaseDelete = ProfileStubDelete.RUNNING
        }
        val crud = ProfileSkillsCrud()
        runBlocking {
            crud.delete(context)
        }
        assertEquals("", context.responseProfile.profileId)
        assertEquals(setOf<String>(), context.responseProfile.dataBases)
        assertEquals(setOf<String>(), context.responseProfile.operatingSystems)
    }
}