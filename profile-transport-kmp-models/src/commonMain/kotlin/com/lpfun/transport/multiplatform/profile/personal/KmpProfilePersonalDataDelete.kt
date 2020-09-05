package com.lpfun.transport.multiplatform.profile.personal

import kotlinx.serialization.Serializable

@Serializable
data class KmpProfilePersonalDataDelete(
    var profileId: String? = null,
    var debug: KmpDebug? = null
) {
    @Serializable
    class KmpDebug
}