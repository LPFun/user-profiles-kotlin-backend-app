package com.lpfun.transport.multiplatform.profile.personal

import com.lpfun.transport.multiplatform.profile.KmpProfileDbMode
import kotlinx.serialization.Serializable

@Serializable
data class KmpProfilePersonalDataGet(
    val profileId: String? = null,
    val debug: Debug? = null,
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