package com.lpfun.backend.common.model.profile.personal

import com.lpfun.backend.common.model.profile.base.ProfileConstants
import kotlinx.datetime.LocalDate

data class ProfilePersonalData(
    var profileId: String = "",
    var firstName: String = "",
    var middleName: String = "",
    var lastName: String = "",
    var displayName: String = "",
    var phone: String = "",
    var email: String = "",
    var bday: LocalDate = ProfileConstants.MIN_DATE,
    var locationModel: LocationModel = LocationModel.NONE,
) {
    companion object {
        val NONE = ProfilePersonalData()
    }
}

data class LocationModel(
    val country: String = "",
    val city: String = ""
) {
    companion object {
        val NONE = LocationModel()
    }
}

fun String.toLocalDate(): LocalDate {
    return LocalDate.parse(this)
}