package com.lpfun.transport.multiplatform.profile.education

import com.lpfun.transport.multiplatform.profile.education.model.KmpAdditionalEducationModel
import com.lpfun.transport.multiplatform.profile.education.model.KmpEducationModel
import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileEducationSave(
        var id: String? = null,
        var mainEducation: MutableList<KmpEducationModel>? = null,
        var additionalEducation: MutableList<KmpAdditionalEducationModel>? = null,
        var debug: KmpDebug? = null
) {
    @Serializable
    class KmpDebug
}