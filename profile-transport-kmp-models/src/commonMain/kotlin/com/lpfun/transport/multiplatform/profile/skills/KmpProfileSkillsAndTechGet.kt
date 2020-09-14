package com.lpfun.transport.multiplatform.profile.skills

import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileSkillsAndTechGet(
        val profileId: String? = null,
        val debug: KmpDebug? = null
) {
    @Serializable
    class KmpDebug
}