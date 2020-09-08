package com.lpfun.transport.multiplatform.profile.skills

import com.lpfun.transport.multiplatform.profile.skills.model.KmpSpecializationModel
import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileSkillsAndTechUpdate(
    var id: String? = null,
    override var specialization: KmpSpecializationModel? = null,
    override var operatingSystems: MutableSet<String>? = null,
    override var dataBases: MutableSet<String>? = null,
    var debug: KmpDebug? = null
) : KmpProfileSkillsAndTechSave(
    specialization = specialization,
    operatingSystems = operatingSystems,
    dataBases = dataBases
) {
    @Serializable
    class KmpDebug
}