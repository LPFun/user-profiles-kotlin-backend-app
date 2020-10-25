package com.lpfun.transport.multiplatform.profile.skills

import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileSkillsAndTechGet(
    val profileId: String? = null,
    val debug: Debug? = null
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