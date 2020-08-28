package com.lpfun.transport.multiplatform.profile.education

import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileEducationGet(
        val profileId: String? = null,
        val debug: KmpDebug? = null,
) {
    @Serializable
    class KmpDebug
}