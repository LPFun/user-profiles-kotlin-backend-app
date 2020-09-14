package com.lpfun.transport.multiplatform.profile.personal

import com.lpfun.transport.multiplatform.profile.personal.model.KmpLocationModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
abstract class KmpProfilePersonalDataSave(
    @Transient open var firstName: String? = null,
    @Transient open var middleName: String? = null,
    @Transient open var lastName: String? = null,
    @Transient open var displayName: String? = null,
    @Transient open var phone: String? = null,
    @Transient open var email: String? = null,
    @Transient open var bday: String? = null,
    @Transient open var locationModel: KmpLocationModel? = null,
)