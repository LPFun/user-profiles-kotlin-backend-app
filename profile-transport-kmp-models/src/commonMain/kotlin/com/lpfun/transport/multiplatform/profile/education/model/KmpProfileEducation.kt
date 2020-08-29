package com.lpfun.transport.multiplatform.profile.education.model

import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileEducation(
        var mainEducation: MutableList<KmpEducationModel>? = null,
        var additionalEducation: MutableList<KmpAdditionalEducationModel>? = null
)

