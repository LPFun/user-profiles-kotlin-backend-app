package com.lpfun.transport.multiplatform.profile.skills

import com.lpfun.transport.multiplatform.profile.skills.model.KmpSpecializationModel
import kotlinx.serialization.Serializable

data class KmpProfileSkillsAndTechUpdate(
        var id: String? = null,
        var specialization: KmpSpecializationModel? = null,
        var operatingSystems: MutableSet<String>? = null,
        var dataBases: MutableSet<String>? = null,
        var debug: KmpDebug? = null
){
    @Serializable
    class KmpDebug
}