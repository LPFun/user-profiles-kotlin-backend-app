package com.lpfun.transport.multiplatform.profile.skills.model

import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileSkillsAndTech(
    var profileId: String? = null,
    var specialization: KmpSpecializationModel? = null,
    var operatingSystems: MutableSet<KmpOperatingSystemModel>? = null,
    var dataBases: MutableSet<KmpDataBaseModel>? = null
)

@Serializable
data class KmpOperatingSystemModel(
    var id: String? = null,
    var operatingSystem: String? = null
)

@Serializable
data class KmpDataBaseModel(
    var id: String? = null,
    var dataBase: String? = null
)