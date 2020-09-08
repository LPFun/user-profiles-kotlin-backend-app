package com.lpfun.transport.multiplatform.profile.skills.model

import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileSkillsAndTech(
        var profileId: String? = null,
        var specialization: KmpSpecializationModel? = null,
        var operatingSystems: MutableSet<String>? = null,
        var dataBases: MutableSet<String>? = null
)