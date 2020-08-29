package com.lpfun.backend.common.model.profile

import java.time.LocalDate

data class ProfilePersonalData(
        var profileId: String = "",
        var firstName: String = "",
        var middleName: String = "",
        var lastName: String = "",
        var displayName: String = "",
        var phone: String = "",
        var email: String = "",
        var bday: LocalDate = LocalDate.MIN,
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