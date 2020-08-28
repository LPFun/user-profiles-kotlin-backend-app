package com.lpfun.transport.multiplatform.profile.personal

import com.lpfun.transport.multiplatform.profile.personal.model.KmpLocationModel
import kotlinx.serialization.Serializable

data class KmpProfilePersonalDataUpdate(
        var id: String? = null,
        var firstName: String? = null,
        var middleName: String? = null,
        var lastName: String? = null,
        var displayName: String? = null,
        var phone: String? = null,
        var email: String? = null,
        var bday: String? = null,
        var locationModel: KmpLocationModel? = null,
        var debug: KmpDebug? = null
){
    @Serializable
    class KmpDebug
}