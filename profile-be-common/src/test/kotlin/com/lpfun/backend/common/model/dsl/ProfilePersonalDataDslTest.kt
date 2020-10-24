package com.lpfun.backend.common.model.dsl

import com.lpfun.backend.common.model.dsl.personal.profilePersonalData
import com.lpfun.backend.common.model.extensions.applyRequest
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalData
import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class ProfilePersonalDataDslTest {
    private val profilePersonalData = profilePersonalData {
        id = "testId"
        name {
            first = "First"
            second = "Second"
            last = "Last"
            display = "Display"
        }
        contacts {
            phone = "+79991111111"
            email = "test@test.com"
        }
        birth {
            date = LocalDate.parse("2000-01-01")
        }
        location {
            country = "Country"
            city = "City"
        }
    }

    @Test
    fun profilePersonalDataDslSuccessTest() {
        testSuccessProfilePersonalDataFields(profilePersonalData)
    }

    @Test
    fun infixProfilePersonalContextSuccessTest() {
        val context = ProfilePersonalContext()
        context applyRequest profilePersonalData
        testSuccessProfilePersonalDataFields(context.requestProfile)
    }

    private fun testSuccessProfilePersonalDataFields(profile: ProfilePersonalData) {
        assertEquals(profile.firstName, "First")
        assertEquals(profile.middleName, "Second")
        assertEquals(profile.lastName, "Last")
        assertEquals(profile.displayName, "Display")
        assertEquals(profile.phone, "+79991111111")
        assertEquals(profile.email, "test@test.com")
        assertEquals(profile.bday, LocalDate.parse("2000-01-01"))
        assertEquals(profile.locationModel.country, "Country")
        assertEquals(profile.locationModel.city, "City")
    }
}