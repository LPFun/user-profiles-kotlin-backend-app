package com.lpfun.transport.multiplatform.profile.skills.model

data class KmpProfileSkillsAndTech(
        var specialization: KmpSpecializationModel? = null,
        var operatingSystems: MutableSet<String>? = null,
        var dataBases: MutableSet<String>? = null
)