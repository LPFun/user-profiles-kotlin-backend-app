package com.lpfun.backend.profile.db.inmemory.skills

import com.lpfun.backend.common.profile.model.dsl.skills.profileSkills
import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsAndTech
import com.lpfun.backend.profile.db.inmemory.DataBaseInMemFactory
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.test.*

internal class ProfileSkillsAndTechRepoInMemoryTest {
    val repo = ProfileSkillsAndTechRepoInMemory(DataBaseInMemFactory.init())
    var profileId = "a9d77992-3e96-4010-bc35-ab7b8b88da40"

    @BeforeTest
    fun create() {
        profileId = UUID.randomUUID().toString()
        val profile = profileSkills {
            id = profileId
            specialization {
                category = "Category"
                subCategory = "Sub Category"
            }
            operatingSystems {
                add("Operating System")
            }
            dataBases {
                add("Data Base")
            }
        }
        runBlocking {
            repo.create(profile)
        }
    }

    @AfterTest
    fun delete() {
        runBlocking {
            repo.delete(profileId)
        }
    }

    @Test
    fun get() {
        var getProfile: ProfileSkillsAndTech
        runBlocking {
            getProfile = repo.get(profileId)
        }
        assertNotEquals("", getProfile.profileId, getProfile.profileId)
        assertEquals("Category", getProfile.specialization.category)
        assertEquals("Sub Category", getProfile.specialization.subCategory)
        assertEquals("Operating System", getProfile.operatingSystems.first().operatingSystem)
        assertEquals("Data Base", getProfile.dataBases.first().dataBase)
    }

    @Test
    fun createSuccessTest() {
        val profile = profileSkills {
            id = UUID.randomUUID().toString()
            specialization {
                category = "Category"
                subCategory = "Sub Category"
            }
            operatingSystems {
                add("Operating System")
            }
            dataBases {
                add("Data Base")
            }
        }
        val createdProfile: ProfileSkillsAndTech
        runBlocking {
            createdProfile = repo.create(profile)
        }
        assertNotEquals("", createdProfile.profileId)
        assertEquals("Category", createdProfile.specialization.category)
        assertEquals("Sub Category", createdProfile.specialization.subCategory)
        assertEquals("Operating System", createdProfile.operatingSystems.first().operatingSystem)
        assertEquals("Data Base", createdProfile.dataBases.first().dataBase)
    }

    @Test
    fun update() {
        var updatedProfile: ProfileSkillsAndTech
        runBlocking {
            val createdProfile = repo.get(profileId).apply {
                specialization.category = "Updated Category"
            }
            updatedProfile = repo.update(createdProfile)
        }
        assertNotEquals("", updatedProfile.profileId)
        assertEquals("Updated Category", updatedProfile.specialization.category)
        assertEquals("Sub Category", updatedProfile.specialization.subCategory)
        assertEquals("Operating System", updatedProfile.operatingSystems.first().operatingSystem)
        assertEquals("Data Base", updatedProfile.dataBases.first().dataBase)
    }

    @Test
    fun deleteSuccessTest() {
        var deletedProfile: ProfileSkillsAndTech
        runBlocking {
            deletedProfile = repo.delete(profileId)
        }
        assertNotEquals("", deletedProfile.profileId)
        assertEquals("Category", deletedProfile.specialization.category)
        assertEquals("Sub Category", deletedProfile.specialization.subCategory)
        assertEquals("Operating System", deletedProfile.operatingSystems.first().operatingSystem)
        assertEquals("Data Base", deletedProfile.dataBases.first().dataBase)
    }
}