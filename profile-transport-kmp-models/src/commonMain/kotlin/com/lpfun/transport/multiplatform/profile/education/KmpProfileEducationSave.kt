package com.lpfun.transport.multiplatform.profile.education

import com.lpfun.transport.multiplatform.profile.education.model.KmpAdditionalEducationModel
import com.lpfun.transport.multiplatform.profile.education.model.KmpEducationModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
abstract class KmpProfileEducationSave(
    @Transient open var mainEducation: MutableList<KmpEducationModel>? = null,
    @Transient open var additionalEducation: MutableList<KmpAdditionalEducationModel>? = null,
)