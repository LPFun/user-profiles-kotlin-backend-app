package com.lpfun.backend.profile.inmemorydb.extensions

import com.lpfun.backend.common.model.profile.personal.LocationModel
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalData
import com.lpfun.backend.common.model.profile.personal.toLocalDate
import com.lpfun.backend.profile.inmemorydb.personal.entity.ProfilePersonalEntity


fun ProfilePersonalEntity.toProfilePersonalData(): ProfilePersonalData {
    return ProfilePersonalData(
        profileId = id.toString(),
        firstName = firstName,
        middleName = middleName,
        lastName = lastName,
        displayName = displayName,
        phone = phone,
        email = email,
        bday = bday.toLocalDate(),
        locationModel = LocationModel(
            country = location.country,
            city = location.city
        )
    )
}