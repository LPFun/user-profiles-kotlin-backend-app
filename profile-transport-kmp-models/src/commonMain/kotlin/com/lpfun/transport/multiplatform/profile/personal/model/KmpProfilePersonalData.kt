package com.lpfun.transport.multiplatform.profile.personal.model

import kotlinx.serialization.Serializable

@Serializable
data class KmpProfilePersonalData(
        var profileId: String? = null,
        var firstName: String? = null,
        var middleName: String? = null,
        var lastName: String? = null,
        var displayName: String? = null,
        var phone: String? = null,
        var email: String? = null,
        var bday: String? = null,
        var locationModel: KmpLocationModel? = null,
)