package com.lpfun.backend.common.profile.model.dsl.skills

import com.lpfun.backend.common.profile.model.dsl.ProfileDslMarker

@ProfileDslMarker
class SpecializationDsl(
    var category: String = "",
    var subCategory: String = "",
) {
    companion object {
        val EMPTY = SpecializationDsl()
    }
}
