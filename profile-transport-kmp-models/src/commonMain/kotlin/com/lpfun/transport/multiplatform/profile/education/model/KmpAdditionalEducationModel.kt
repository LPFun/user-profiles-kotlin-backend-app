package com.lpfun.transport.multiplatform.profile.education.model

import kotlinx.serialization.Serializable

@Serializable
data class KmpAdditionalEducationModel(
        var nameOfInstitution: String? = null,
        var courseName: String? = null,
        var yearOfCompletion: String? = null,
)