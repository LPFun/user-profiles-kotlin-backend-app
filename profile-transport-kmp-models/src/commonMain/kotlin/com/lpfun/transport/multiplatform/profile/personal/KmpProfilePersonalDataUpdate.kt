package com.lpfun.transport.multiplatform.profile.personal

import com.lpfun.transport.multiplatform.profile.personal.model.KmpLocationModel
import kotlinx.serialization.Serializable

@Serializable
data class KmpProfilePersonalDataUpdate(
        var profileId: String? = null,
        override var firstName: String? = null,
        override var middleName: String? = null,
        override var lastName: String? = null,
        override var displayName: String? = null,
        override var phone: String? = null,
        override var email: String? = null,
        override var bday: String? = null,
        override var locationModel: KmpLocationModel? = null,
        var debug: KmpDebug? = null
) : KmpProfilePersonalDataSave(
        firstName = firstName,
        middleName = middleName,
        lastName = lastName,
        displayName = displayName,
        phone = phone,
        email = email,
        bday = bday,
        locationModel = locationModel
) {
        @Serializable
        class KmpDebug
}