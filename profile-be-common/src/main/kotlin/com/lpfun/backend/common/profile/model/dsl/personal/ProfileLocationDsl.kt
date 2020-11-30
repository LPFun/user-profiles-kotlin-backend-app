package com.lpfun.backend.common.profile.model.dsl.personal

import com.lpfun.backend.common.profile.model.dsl.ProfileDslMarker

@ProfileDslMarker
class ProfileLocationDsl(
    var country: String = "",
    var city: String = ""
) {
    companion object {
        val EMPTY = ProfileLocationDsl()
    }
}
