package com.lpfun.backend.kmp.profile

import com.lpfun.backend.common.model.profile.ProfileContext
import com.lpfun.backend.common.model.profile.ProfileEducation
import com.lpfun.backend.common.model.profile.ProfilePersonalData
import com.lpfun.transport.multiplatform.profile.KmpProfileResponse
import com.lpfun.transport.multiplatform.profile.KmpProfileResponseStatus
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationResponse
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataResponse

inline fun <reified T> ProfileContext.resultItem(): KmpProfileResponse {
    return when (T::class) {
        KmpProfileEducationResponse::class -> KmpProfileEducationResponse(
            data = (responseProfile as ProfileEducation).toKmp(),
            status = KmpProfileResponseStatus.SUCCESS
        )
        else -> KmpProfilePersonalDataResponse(
            data = (responseProfile as ProfilePersonalData).toKmp(),
            status = KmpProfileResponseStatus.SUCCESS
        )
    }
}

fun String?.toModelString() = this?.takeIf { it.isNotBlank() } ?: ""