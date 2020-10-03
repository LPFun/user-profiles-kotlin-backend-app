package com.lpfun.backend.common.model.dsl.personal

import com.lpfun.backend.common.model.dsl.ProfileDslMarker
import com.lpfun.backend.common.model.profile.personal.LocationModel
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalData

@ProfileDslMarker
class ProfilePersonalDataDsl(
    var id: String = ""
) {

    var name = ProfileNameDsl.EMPTY
        private set
    var contacts = ProfileContactsDsl.EMPTY
        private set
    var birth = ProfileBirthDsl.EMPTY
        private set
    var location = ProfileLocationDsl.EMPTY
        private set

    fun name(conf: ProfileNameDsl.() -> Unit) {
        name = ProfileNameDsl().apply(conf)
    }

    fun contacts(conf: ProfileContactsDsl.() -> Unit) {
        contacts = ProfileContactsDsl().apply(conf)
    }

    fun birth(conf: ProfileBirthDsl.() -> Unit) {
        birth = ProfileBirthDsl().apply(conf)
    }

    fun location(conf: ProfileLocationDsl.() -> Unit) {
        location = ProfileLocationDsl().apply(conf)
    }
}

fun profilePersonalData(conf: ProfilePersonalDataDsl.() -> Unit) = ProfilePersonalDataDsl().apply(conf).run {
    ProfilePersonalData(
        profileId = id,
        firstName = name.first,
        middleName = name.second,
        lastName = name.last,
        displayName = name.display,
        phone = contacts.phone,
        email = contacts.email,
        bday = birth.date,
        locationModel = LocationModel(location.country, location.city)
    )
}