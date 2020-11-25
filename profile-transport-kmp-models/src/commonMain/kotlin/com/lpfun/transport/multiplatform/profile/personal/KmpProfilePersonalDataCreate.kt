package com.lpfun.transport.multiplatform.profile.personal

import com.lpfun.transport.multiplatform.profile.KmpProfileDbMode
import com.lpfun.transport.multiplatform.profile.personal.model.KmpLocationModel
import kotlinx.serialization.Serializable

@Serializable
data class KmpProfilePersonalDataCreate(
    override var firstName: String? = null,
    override var middleName: String? = null,
    override var lastName: String? = null,
    override var displayName: String? = null,
    override var phone: String? = null,
    override var email: String? = null,
    override var bday: String? = null,
    override var locationModel: KmpLocationModel? = null,
    var debug: Debug? = null
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
    class Debug {
        var stub: StubCase? = null
        var db: KmpProfileDbMode? = null
    }

    @Serializable
    enum class StubCase {
        NONE,
        RUNNING
    }
}