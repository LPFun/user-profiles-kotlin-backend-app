package com.lpfun.backend.kmp.profile

import com.lpfun.backend.common.model.error.IProfileError
import com.lpfun.backend.common.model.profile.ProfileContext
import com.lpfun.backend.common.model.profile.ProfileEducation
import com.lpfun.backend.common.model.profile.ProfilePersonalData
import com.lpfun.transport.multiplatform.profile.KmpProfileError
import com.lpfun.transport.multiplatform.profile.KmpProfileResponseStatus
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationResponse
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataResponse

inline fun <reified T> ProfileContext.resultItem(): T {
    return when (T::class) {
        KmpProfileEducationResponse::class -> KmpProfileEducationResponse(
            data = (responseProfile as ProfileEducation).toKmp(),
            status = kmpStatus(),
            errors = errors.map { it.toKmp() }
        ) as T
        else -> KmpProfilePersonalDataResponse(
            data = (responseProfile as ProfilePersonalData).toKmp(),
            status = kmpStatus(),
            errors = errors.map { it.toKmp() }
        ) as T
    }
}

fun IProfileError.toKmp() = KmpProfileError(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it != IProfileError.Groups.NONE }?.toString(),
    field = field.takeIf { it.isNotBlank() },
    level = level.toKmp(),
    message = message.takeIf { it.isNotBlank() }
)

fun IProfileError.Levels.toKmp() = when (this) {
    IProfileError.Levels.ERROR -> KmpProfileError.Level.ERROR
    else -> KmpProfileError.Level.SUCCESS
}

fun ProfileContext.kmpStatus() = when {
    responseProfileStatus.isError || errors.any { it.level.isError } -> KmpProfileResponseStatus.ERROR
    errors.any { it.level.isWarning } -> KmpProfileResponseStatus.WARNING
    else -> KmpProfileResponseStatus.SUCCESS
}

fun String?.toModelString() = this?.takeIf { it.isNotBlank() } ?: ""