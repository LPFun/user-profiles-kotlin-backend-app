package com.lpfun.transport.multiplatform.profile

import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileError(
        val code: String? = null,
        val group: String? = null,
        val message: String? = null,
        val description: String? = null
)