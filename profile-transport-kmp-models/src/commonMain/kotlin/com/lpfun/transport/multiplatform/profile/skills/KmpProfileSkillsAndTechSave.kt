package com.lpfun.transport.multiplatform.profile.skills

import com.lpfun.transport.multiplatform.profile.skills.model.KmpSpecializationModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
abstract class KmpProfileSkillsAndTechSave(
    @Transient open var specialization: KmpSpecializationModel? = null,
    @Transient open var operatingSystems: MutableSet<String>? = null,
    @Transient open var dataBases: MutableSet<String>? = null,
)