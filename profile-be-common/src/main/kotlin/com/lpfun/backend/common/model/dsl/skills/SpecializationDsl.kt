package com.lpfun.backend.common.model.dsl.skills

import com.lpfun.backend.common.model.dsl.ProfileDslMarker

@ProfileDslMarker
class SpecializationDsl(
    var category: String = "",
    var subCategory: String = "",
) {
    companion object {
        val EMPTY = SpecializationDsl()
    }
}
