package com.lpfun.transport.multiplatform.profile.skills.model

import kotlinx.serialization.Serializable

@Serializable
data class KmpSpecializationModel(
        var category: String? = null,
        var subCategory: String? = null,
)