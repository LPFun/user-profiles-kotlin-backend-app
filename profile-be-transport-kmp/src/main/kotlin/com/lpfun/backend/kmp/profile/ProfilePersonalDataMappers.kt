package com.lpfun.backend.kmp.profile

import com.lpfun.backend.common.model.profile.LocationModel
import com.lpfun.backend.common.model.profile.ProfileContext
import com.lpfun.backend.common.model.profile.ProfilePersonalData
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataDelete
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataGet
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataSave
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataUpdate
import com.lpfun.transport.multiplatform.profile.personal.model.KmpLocationModel
import com.lpfun.transport.multiplatform.profile.personal.model.KmpProfilePersonalData
import kotlinx.datetime.LocalDate
import java.time.Year

fun ProfileContext.setQuery(get: KmpProfilePersonalDataGet) = this.apply {
    requestProfileId = get.profileId ?: ""
}

fun ProfileContext.setQuery(save: KmpProfilePersonalDataSave) = this.apply {
    requestProfile = save.toModel()
}

fun ProfileContext.setQuery(del: KmpProfilePersonalDataDelete) = this.apply {
    requestProfileId = del.profileId ?: ""
}

fun ProfilePersonalData.toKmp() = KmpProfilePersonalData(
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

