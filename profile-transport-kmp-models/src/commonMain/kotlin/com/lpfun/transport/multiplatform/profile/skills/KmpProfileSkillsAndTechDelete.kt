package com.lpfun.transport.multiplatform.profile.skills

import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileSkillsAndTechDelete(
    var profileId: String? = null,
    var debug: KmpDebug? = null
) {
    @Serializable
    class KmpDebug
}