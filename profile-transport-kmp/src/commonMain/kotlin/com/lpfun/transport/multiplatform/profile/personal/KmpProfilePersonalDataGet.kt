package com.lpfun.transport.multiplatform.profile.personal

import kotlinx.serialization.Serializable

@Serializable
data class KmpProfilePersonalDataGet(
        val profileId: String? = null,
        val debug: KmpDebug? = null,
) {
    @Serializable
    class KmpDebug
}