package com.lpfun.backend.kmp.profile

import com.lpfun.backend.common.model.profile.ProfileContext
import com.lpfun.backend.common.model.profile.ProfileEducation
import com.lpfun.backend.common.model.profile.ProfilePersonalData
import com.lpfun.transport.multiplatform.profile.KmpProfileResponseStatus
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationResponse
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataResponse

inline fun <reified T> ProfileContext.resultItem(): T {
    return when (T::class) {
        KmpProfileEducationResponse::class -> KmpProfileEducationResponse(
            data = (responseProfile as ProfileEducation).toKmp(),
            status = KmpProfileResponseStatus.SUCCESS
        ) as T
        else -> KmpProfilePersonalDataResponse(
            data = (responseProfile as ProfilePersonalData).toKmp(),
            status = KmpProfileResponseStatus.SUCCESS
        ) as T
    }
}

fun String?.toModelString() = this?.takeIf { it.isNotBlank() } ?: ""