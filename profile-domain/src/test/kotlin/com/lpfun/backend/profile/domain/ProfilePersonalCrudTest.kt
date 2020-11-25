package com.lpfun.backend.profile.domain

import com.lpfun.backend.common.profile.model.dsl.personal.profilePersonalData
import com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubCreate
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubDelete
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubGet
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubUpdate
import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalData
import com.lpfun.backend.profile.domain.personal.ProfilePersonalCrud
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ProfilePersonalCrudTest {

    @Test
    fun getProfilePersonalTest() {
        val context = ProfilePersonalContext().apply {
            stubCaseGet = ProfileStubGet.SUCCESS
            requestProfile = ProfilePersonalData(
                profileId = "test-id"
            )
        }
        val crud = ProfilePersonalCrud()
        runBlocking {
            crud.get(context)
        }
        assertEquals(ProfileContextStatus.SUCCESS, context.responseProfileStatus)
        assertEquals("test-id", context.responseProfile.profileId)
    }

    @Test
    fun createProfilePersonalTest() {
        val context = ProfilePersonalContext().apply {
            stubCaseCreate = ProfileStubCreate.SUCCESS
            requestProfile = profilePersonalData {
                name {
                    first = "John"
                    second = "Junior"
                    last = "Smith"
                    display = "John Smith"
                }
                contacts {
                    email = "test@mail.com"
                    phone = "+123456789"
                }
                birth {
                    date = LocalDate.parse("2000-01-01")
                }
                location {
                    city = "City"
                    country = "Country"
                }
            }
        }

        val crud = ProfilePersonalCrud()
        runBlocking {
            crud.create(context)
        }

        assertEquals("test-id", context.responseProfile.profileId)
        assertEquals("John", context.responseProfile.firstName)
    }

    @Test
    fun updateProfilePersonalTest() {
        val context = ProfilePersonalContext().apply {
            requestProfile = profilePersonalData {
                contacts {
                    email = "test@mail.com"
                    phone = "+123456789"
                }
            }
            stubCaseUpdate = ProfileStubUpdate.SUCCESS
        }
        val crud = ProfilePersonalCrud()
        runBlocking {
            crud.update(context)
        }
        assertEquals("test@mail.com", context.responseProfile.email)
        assertEquals("+123456789", context.responseProfile.phone)
    }

    @Test
    fun deleteProfilePersonalTest() {
        val context = ProfilePersonalContext().apply {
            requestProfile.profileId = "test-id"
            stubCaseDelete = ProfileStubDelete.SUCCESS
        }
        val crud = ProfilePersonalCrud()

        runBlocking {
            crud.delete(context)
        }
        assertEquals("", context.responseProfile.profileId)
        assertEquals("", context.responseProfile.firstName)
    }
}