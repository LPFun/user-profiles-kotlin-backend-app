package com.lpfun.backend.profile.db.inmemory.personal

import com.lpfun.backend.common.profile.model.dsl.personal.profilePersonalData
import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalData
import com.lpfun.backend.profile.db.inmemory.DataBaseInMemFactory
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ProfilePersonalRepoInMemoryTest {
    private val repo by lazy {
        ProfilePersonalRepoInMemory(DataBaseInMemFactory.init())
    }

    private val successProfile by lazy {
        profilePersonalData {
            name {
                first = "First name"
                second = "Second name"
                last = "Last name"
                display = "Display name"
            }
            birth {
                date = LocalDate.parse("2020-01-01")
            }
            contacts {
                phone = "Phone"
                email = "Email"
            }
            location {
                country = "Country"
                city = "City"
            }
        }
    }

    @Test
    fun get() {
        var getProfile: ProfilePersonalData
        runBlocking {
            getProfile = repo.get("test-id")
        }
        assertEquals("First name", getProfile.firstName)
        assertEquals("Second name", getProfile.middleName)
        assertEquals("Last name", getProfile.lastName)
        assertEquals("Display name", getProfile.displayName)
        assertEquals("2020-01-01", getProfile.bday.toString())
        assertEquals("Phone", getProfile.phone)
        assertEquals("Email", getProfile.email)
        assertEquals("Country", getProfile.locationModel.country)
        assertEquals("City", getProfile.locationModel.city)
    }

    @Test
    fun update() {
        var updatedProfile: ProfilePersonalData
        runBlocking {
            updatedProfile = repo.update(successProfile.apply {
                profileId = "test-id"
                firstName = "Updated First Name"
            })
        }
        assertEquals("Updated First Name", updatedProfile.firstName)
    }

    @Test
    fun create() {
        var createdProfile: ProfilePersonalData

        runBlocking {
            createdProfile = repo.create(successProfile)
        }
        assertEquals("First name", createdProfile.firstName)
        assertEquals("Second name", createdProfile.middleName)
        assertEquals("Last name", createdProfile.lastName)
        assertEquals("Display name", createdProfile.displayName)
        assertEquals("2020-01-01", createdProfile.bday.toString())
        assertEquals("Phone", createdProfile.phone)
        assertEquals("Email", createdProfile.email)
        assertEquals("Country", createdProfile.locationModel.country)
        assertEquals("City", createdProfile.locationModel.city)
    }

    @Test
    fun delete() {
        var deletedProfile: ProfilePersonalData
        runBlocking {
            deletedProfile = repo.delete("test-id")
        }
        assertEquals("test-id", deletedProfile.profileId)
        assertEquals("First name", deletedProfile.firstName)
        assertEquals("Second name", deletedProfile.middleName)
        assertEquals("Last name", deletedProfile.lastName)
        assertEquals("Display name", deletedProfile.displayName)
        assertEquals("2020-01-01", deletedProfile.bday.toString())
        assertEquals("Phone", deletedProfile.phone)
        assertEquals("Email", deletedProfile.email)
        assertEquals("Country", deletedProfile.locationModel.country)
        assertEquals("City", deletedProfile.locationModel.city)
    }
}