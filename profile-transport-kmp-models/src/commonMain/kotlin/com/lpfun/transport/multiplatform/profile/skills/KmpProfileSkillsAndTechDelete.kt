package com.lpfun.transport.multiplatform.profile.skills

import com.lpfun.transport.multiplatform.profile.KmpProfileDbMode
import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileSkillsAndTechDelete(
    var profileId: String? = null,
    var debug: Debug? = null
) {
    @Serializable
    class Debug {
        var stub: StubCase? = null
        var db: KmpProfileDbMode? = null
    }

    @Serializable
    enum class StubCase {
        NONE,
        SUCCESS
    }
}