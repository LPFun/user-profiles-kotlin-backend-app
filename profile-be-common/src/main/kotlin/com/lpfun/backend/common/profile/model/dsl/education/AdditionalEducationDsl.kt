package com.lpfun.backend.common.profile.model.dsl.education

import com.lpfun.backend.common.profile.model.dsl.ProfileDslMarker

@ProfileDslMarker
class AdditionalEducationDsl(
    var id: String = "",
    var nameOfInstitution: String = "",
    var courseName: String = "",
    var yearOfCompletion: String = "",
) {
    companion object {
        val EMPTY = AdditionalEducationDsl()
    }
}
