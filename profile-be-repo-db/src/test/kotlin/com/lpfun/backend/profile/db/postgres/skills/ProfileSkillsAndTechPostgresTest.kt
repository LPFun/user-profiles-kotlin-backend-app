package com.lpfun.backend.profile.db.inmemory.skills

import com.lpfun.backend.common.profile.model.dsl.skills.profileSkills
import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsAndTech
import com.lpfun.backend.profile.db.postgres.ProfilePostgresContainer
import com.lpfun.backend.profile.db.skills.ProfileSkillsAndTechRepo
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

internal class ProfileSkillsAndTechPostgresTest {
    val repo = ProfileSkillsAndTechRepo()

    @BeforeTest
    fun init() {
        ProfilePostgresContainer.start()
    }

    @Test
    fun get() {
        val profile = profileSkills {
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

        var getProfile: ProfileSkillsAndTech
        runBlocking {
            val createdProfile = repo.create(profile)
            getProfile = repo.get(createdProfile.profileId)
        }
        assertNotEquals("", getProfile.profileId)
        assertEquals("Category", getProfile.specialization.category)
        assertEquals("Sub Category", getProfile.specialization.subCategory)
        assertEquals("Operating System", getProfile.operatingSystems.first().operatingSystem)
        assertEquals("Data Base", getProfile.dataBases.first().dataBase)
    }

    @Test
    fun create() {
        val profile = profileSkills {
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
        val profile = profileSkills {
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
        var updatedProfile: ProfileSkillsAndTech
        runBlocking {
            val createdProfile = repo.create(profile).apply {
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
    fun delete() {
        val profile = profileSkills {
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
        var deletedProfile: ProfileSkillsAndTech
        runBlocking {
            val createdProfile = repo.create(profile)
            deletedProfile = repo.delete(createdProfile.profileId)
        }
        assertNotEquals("", deletedProfile.profileId)
        assertEquals("Category", deletedProfile.specialization.category)
        assertEquals("Sub Category", deletedProfile.specialization.subCategory)
        assertEquals("Operating System", deletedProfile.operatingSystems.first().operatingSystem)
        assertEquals("Data Base", deletedProfile.dataBases.first().dataBase)
    }
}