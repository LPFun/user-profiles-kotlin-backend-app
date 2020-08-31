package com.lpfun.transport.multiplatform.profile.education

import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileEducationDelete(
    var id: String? = null,
    var debug: KmpDebug? = null
) {
    @Serializable
    class KmpDebug
}