package com.lpfun.backend.profile.db.inmemory.personal

import com.lpfun.backend.common.profile.model.dsl.personal.profilePersonalData
import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalData
import com.lpfun.backend.profile.db.personal.ProfilePersonalRepo
import com.lpfun.backend.profile.db.postgres.ProfilePostgresContainer
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ProfilePersonalRepoPostgresTest {
    val repo = ProfilePersonalRepo()
    val successProfile = profilePersonalData {
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

    @BeforeTest
    fun init() {
        ProfilePostgresContainer.start()
    }

    @Test
    fun get() {
        var getProfile: ProfilePersonalData

        runBlocking {
            val createdProfile = repo.create(successProfile)
            getProfile = repo.get(createdProfile.profileId)
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
    fun update() {
        var updatedProfile: ProfilePersonalData
        runBlocking {
            val createdProfile = repo.create(successProfile)
            updatedProfile = repo.update(successProfile.apply {
                profileId = createdProfile.profileId
                firstName = "Updated First Name"
            })
        }
        assertEquals("Updated First Name", updatedProfile.firstName)
    }

    @Test
    fun delete() {
        var deletedProfile: ProfilePersonalData
        runBlocking {
            val createdProfile = repo.create(successProfile)
            deletedProfile = repo.delete(createdProfile.profileId)
        }
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