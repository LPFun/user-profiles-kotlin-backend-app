package com.lpfun.transport.multiplatform.profile

import kotlinx.serialization.Serializable

@Serializable
data class KmpProfile(
        var id: String = "",
        var userId: String = "",
)