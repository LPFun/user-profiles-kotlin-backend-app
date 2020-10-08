package com.lpfun.profile.personaldata

import com.lpfun.backend.common.model.error.InternalServerError
import com.lpfun.backend.common.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.model.profile.personal.LocationModel
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalData
import com.lpfun.backend.kmp.profile.resultItem
import com.lpfun.backend.kmp.profile.setQuery
import com.lpfun.backend.profile.domain.education.ProfilePersonalCrud
import com.lpfun.base.request
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataCreate
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataDelete
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataGet
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataUpdate
import kotlinx.datetime.LocalDate
import java.util.*

class ProfilePersonalDataService(
    val crud: ProfilePersonalCrud
) {

    private var profilePersonal = ProfilePersonalData(
        profileId = "12345",
        firstName = "John",
        middleName = "Junior",
        lastName = "Smith",
        displayName = "John Smith",
        phone = "+1234",
        email = "mail@mail.com",
        bday = LocalDate(2000, 1, 1),
        locationModel = LocationModel(
            country = "Test Country",
            city = "Test City"
        )
    )

    suspend fun get(query: KmpProfilePersonalDataGet) = ProfilePersonalContext().run {
        try {
            crud.get(setQuery(query))
        } catch (t: Throwable) {
            errors.add(InternalServerError.instance)
        }
        resultItem()
    }

    suspend fun create(query: KmpProfilePersonalDataCreate) = ProfilePersonalContext().request {
        setQuery(query)
            .apply {
                profilePersonal =
                    (requestProfile as ProfilePersonalData).copy(profileId = UUID.randomUUID().toString())
                responseProfile = profilePersonal
                responseProfileStatus = ProfileContextStatus.SUCCESS
            }
    }

    suspend fun update(query: KmpProfilePersonalDataUpdate) = ProfilePersonalContext().request {
        setQuery(query).apply {
            requestProfileId = profilePersonal.profileId
            responseProfile = requestProfile
            responseProfileStatus = ProfileContextStatus.SUCCESS
        }
    }

    suspend fun delete(query: KmpProfilePersonalDataDelete) = ProfilePersonalContext().request {
        setQuery(query).apply {
            responseProfile = ProfilePersonalData()
            responseProfileStatus = ProfileContextStatus.SUCCESS
        }
    }
}


