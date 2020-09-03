package com.lpfun.transport.multiplatform.profile.skills

import com.lpfun.transport.multiplatform.profile.skills.model.KmpSpecializationModel
import kotlinx.serialization.Serializable

@Serializable
abstract class KmpProfileSkillsAndTechSave(
    open var specialization: KmpSpecializationModel? = null,
    open var operatingSystems: MutableSet<String>? = null,
    open var dataBases: MutableSet<String>? = null,
)