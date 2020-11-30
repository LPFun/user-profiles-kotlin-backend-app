package com.lpfun.backend.common.profile.model.dsl.personal

import com.lpfun.backend.common.profile.model.dsl.ProfileDslMarker

@ProfileDslMarker
class ProfileContactsDsl(
    var phone: String = "",
    var email: String = ""
) {
    companion object {
        val EMPTY = ProfileContactsDsl()
    }
}
