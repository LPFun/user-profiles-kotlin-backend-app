package com.lpfun.backend.common.model.dsl.personal

import com.lpfun.backend.common.model.dsl.ProfileDslMarker
import kotlinx.datetime.LocalDate

@ProfileDslMarker
class ProfileBirthDsl(
    var date: LocalDate = LocalDate.parse(java.time.LocalDate.MIN.toString())
) {
    companion object {
        val EMPTY = ProfileBirthDsl()
    }
}
