package com.lpfun.backend.profile.db.extensions

import com.lpfun.backend.common.profile.model.profile.personal.LocationModel
import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalData
import com.lpfun.backend.common.profile.model.profile.personal.toLocalDate
import com.lpfun.backend.profile.db.base.personal.ProfilePersonalEntity

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
            country = country,
            city = city
        )
    )
}

infix fun ProfilePersonalEntity.applyProfile(profile: ProfilePersonalData): ProfilePersonalEntity {
    firstName = profile.firstName
    middleName = profile.middleName
    lastName = profile.lastName
    displayName = profile.displayName
    phone = profile.phone
    email = profile.email
    bday = profile.bday.toString()
    country = profile.locationModel.country
    city = profile.locationModel.city
    return this
}