package com.lpfun.transport.multiplatform.profile.education

import com.lpfun.transport.multiplatform.profile.education.model.KmpAdditionalEducationModel
import com.lpfun.transport.multiplatform.profile.education.model.KmpEducationModel
import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileEducationCreate(
    override var mainEducation: MutableList<KmpEducationModel>? = null,
    override var additionalEducation: MutableList<KmpAdditionalEducationModel>? = null,
    var debug: KmpDebug? = null
) : KmpProfileEducationSave(
    mainEducation = mainEducation,
    additionalEducation = additionalEducation
) {
    @Serializable
    class KmpDebug
}