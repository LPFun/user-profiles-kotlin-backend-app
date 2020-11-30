package com.lpfun.backend.common.profile.model.dsl.personal

import com.lpfun.backend.common.profile.model.dsl.ProfileDslMarker

@ProfileDslMarker
class ProfileNameDsl(
    var first: String = "",
    var second: String = "",
    var last: String = "",
    var display: String = "",
) {
    companion object {
        val EMPTY = ProfileNameDsl()
    }
}
