package com.lpfun.backend.kmp.profile

import com.lpfun.backend.common.model.profile.base.stub.ProfileStubCreate
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubDelete
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubGet
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubUpdate
import com.lpfun.backend.common.model.profile.personal.LocationModel
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalData
import com.lpfun.transport.multiplatform.profile.personal.*
import com.lpfun.transport.multiplatform.profile.personal.model.KmpLocationModel
import com.lpfun.transport.multiplatform.profile.personal.model.KmpProfilePersonalData
import kotlinx.datetime.LocalDate
import java.time.Year

fun ProfilePersonalContext.setQuery(get: KmpProfilePersonalDataGet) = this.apply {
    requestProfile.profileId = get.profileId ?: ""
    stubCaseGet = when (get.debug?.stub) {
        KmpProfilePersonalDataGet.StubCase.RUNNING -> ProfileStubGet.RUNNING
        else -> ProfileStubGet.NONE
    }
}

fun ProfilePersonalContext.setQuery(save: KmpProfilePersonalDataSave) = this.apply {
    requestProfile = save.toModel()
    when (save) {
        is KmpProfilePersonalDataCreate -> stubCaseCreate = when (save.debug?.stub) {
            KmpProfilePersonalDataCreate.StubCase.RUNNING -> ProfileStubCreate.RUNNING
            else -> ProfileStubCreate.NONE
        }
        is KmpProfilePersonalDataUpdate -> stubCaseUpdate = when (save.debug?.stub) {
            KmpProfilePersonalDataUpdate.StubCase.RUNNING -> ProfileStubUpdate.RUNNING
            else -> ProfileStubUpdate.NONE
        }
    }
}

fun ProfilePersonalContext.setQuery(del: KmpProfilePersonalDataDelete) = this.apply {
    requestProfileId = del.profileId ?: ""
    stubCaseDelete = when (del.debug?.stub) {
        KmpProfilePersonalDataDelete.StubCase.RUNNING -> ProfileStubDelete.RUNNING
        else -> ProfileStubDelete.NONE
    }
}

fun ProfilePersonalContext.resultItem() = KmpProfilePersonalDataResponse(
    data = responseProfile.toKmp(),
    status = kmpStatus(),
    errors = errors.map { it.toKmp() }
)

fun ProfilePersonalData.toKmp() = KmpProfilePersonalData(
    profileId = profileId,
    firstName = firstName,
    middleName = middleName,
    lastName = lastName,
    displayName = displayName,
    phone = phone,
    email = email,
    bday = bday.toString(),
    locationModel = locationModel.toKmpModel()
)

private fun LocationModel.toKmpModel() = KmpLocationModel(
    country = country,
    city = city
)

fun KmpProfilePersonalDataSave.toModel() = ProfilePersonalData(
    profileId = if (this is KmpProfilePersonalDataUpdate) profileId ?: "" else "",
    firstName = firstName.toModelString(),
    middleName = middleName.toModelString(),
    lastName = lastName.toModelString(),
    displayName = displayName.toModelString(),
    phone = phone.toModelString(),
    email = email.toModelString(),
    bday = bday.toModelDate(),
    locationModel = locationModel.toModel()
)

private fun String?.toModelDate(): LocalDate {
    return try {
        this?.let {
            LocalDate.parse(it)
        } ?: throw IllegalArgumentException()
    } catch (e: Exception) {
        LocalDate(Year.MIN_VALUE, 1, 1)
    }
}

private fun KmpLocationModel?.toModel() = LocationModel(
    country = this?.country.toModelString(),
    city = this?.city.toModelString()
)

