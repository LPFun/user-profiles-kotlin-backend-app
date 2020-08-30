package com.lpfun.transport.multiplatform.profile.education.model

import com.lpfun.transport.multiplatform.profile.KmpProfile
import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileEducation(
        override var id: String? = null,
        var mainEducation: MutableList<KmpEducationModel>? = null,
        var additionalEducation: MutableList<KmpAdditionalEducationModel>? = null
) : KmpProfile(
        id = id
)

