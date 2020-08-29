package com.lpfun.transport.multiplatform.profile.personal.model

import kotlinx.serialization.Serializable

@Serializable
data class KmpLocationModel(
        val country: String? = null,
        val city: String? = null
)