package com.lpfun.backend.common.model.profile

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
) : ProfileBase(
        id = profileId
)

data class LocationModel(
        val country: String = "",
        val city: String = ""
) {
    companion object{
        val NONE = LocationModel()
    }
}