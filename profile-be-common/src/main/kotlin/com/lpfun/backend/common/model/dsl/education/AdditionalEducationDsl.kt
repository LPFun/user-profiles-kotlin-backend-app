package com.lpfun.backend.common.model.dsl.education

import com.lpfun.backend.common.model.dsl.ProfileDslMarker

@ProfileDslMarker
class AdditionalEducationDsl(
    var nameOfInstitution: String = "",
    var courseName: String = "",
    var yearOfCompletion: String = "",
) {
    companion object {
        val EMPTY = AdditionalEducationDsl()
    }
}
