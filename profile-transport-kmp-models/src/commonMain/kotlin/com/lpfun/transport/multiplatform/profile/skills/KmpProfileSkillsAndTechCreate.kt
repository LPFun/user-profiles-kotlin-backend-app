package com.lpfun.transport.multiplatform.profile.skills

import com.lpfun.transport.multiplatform.profile.skills.model.KmpDataBaseModel
import com.lpfun.transport.multiplatform.profile.skills.model.KmpOperatingSystemModel
import com.lpfun.transport.multiplatform.profile.skills.model.KmpSpecializationModel
import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileSkillsAndTechCreate(
    override var specialization: KmpSpecializationModel? = null,
    override var operatingSystems: MutableSet<KmpOperatingSystemModel>? = null,
    override var dataBases: MutableSet<KmpDataBaseModel>? = null,
    var debug: Debug? = null
) : KmpProfileSkillsAndTechSave(
    specialization = specialization,
    operatingSystems = operatingSystems,
    dataBases = dataBases
) {
    @Serializable
    class Debug {
        var stub: StubCase? = null
    }

    @Serializable
    enum class StubCase {
        NONE,
        RUNNING
    }
}