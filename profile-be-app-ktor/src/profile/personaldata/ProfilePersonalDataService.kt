package com.lpfun.profile.personaldata

import com.lpfun.backend.common.model.profile.ProfileContextStatus
import com.lpfun.backend.common.model.profile.personal.LocationModel
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalData
import com.lpfun.backend.kmp.profile.setQuery
import com.lpfun.base.request
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataCreate
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataDelete
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataGet
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataUpdate
import kotlinx.datetime.LocalDate
import java.util.*

class ProfilePersonalDataService {

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

    fun get(query: KmpProfilePersonalDataGet) = ProfilePersonalContext().request {
        setQuery(query)
                .apply {
                    responseProfile =
                            if (query.profileId == profilePersonal.profileId) profilePersonal else throw IllegalArgumentException()
                    responseProfileStatus = ProfileContextStatus.SUCCESS
                }
    }

    fun create(query: KmpProfilePersonalDataCreate) = ProfilePersonalContext().request {
        setQuery(query)
                .apply {
                    profilePersonal =
                            (requestProfile as ProfilePersonalData).copy(profileId = UUID.randomUUID().toString())
                    responseProfile = profilePersonal
                    responseProfileStatus = ProfileContextStatus.SUCCESS
                }
    }

    fun update(query: KmpProfilePersonalDataUpdate) = ProfilePersonalContext().request {
        setQuery(query).apply {
            requestProfileId = profilePersonal.profileId
            responseProfile = requestProfile
            responseProfileStatus = ProfileContextStatus.SUCCESS
        }
    }

    fun delete(query: KmpProfilePersonalDataDelete) = ProfilePersonalContext().request {
        setQuery(query).apply {
            responseProfile = ProfilePersonalData()
            responseProfileStatus = ProfileContextStatus.SUCCESS
        }
    }
}


