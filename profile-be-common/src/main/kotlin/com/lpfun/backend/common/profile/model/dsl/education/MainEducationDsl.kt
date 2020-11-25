package com.lpfun.backend.common.profile.model.dsl.education

import com.lpfun.backend.common.profile.model.dsl.ProfileDslMarker

@ProfileDslMarker
class MainEducationDsl(
    var id: String = "",
    var university: String = "",
    var department: String = "",
    var speciality: String = "",
    var yearOfCompletion: String = "",
) {
    val mainEducationList = mutableListOf<MainEducationDsl>()

    companion object {
        val EMPTY = MainEducationDsl()
    }
}