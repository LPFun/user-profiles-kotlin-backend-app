package com.lpfun.transport.multiplatform.profile.education.model

import kotlinx.serialization.Serializable

@Serializable
data class KmpEducationModel(
    var id: String? = null,
    var university: String? = null,
    var department: String? = null,
    var specialty: String? = null,
    var yearOfCompletion: String? = null,
)