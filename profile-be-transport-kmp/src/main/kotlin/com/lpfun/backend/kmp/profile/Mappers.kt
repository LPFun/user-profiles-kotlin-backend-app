package com.lpfun.backend.kmp.profile

import com.lpfun.backend.common.WorkMode
import com.lpfun.backend.common.profile.model.error.IProfileError
import com.lpfun.backend.common.profile.model.profile.base.BaseProfileContext
import com.lpfun.transport.multiplatform.profile.KmpProfileDbMode
import com.lpfun.transport.multiplatform.profile.KmpProfileError
import com.lpfun.transport.multiplatform.profile.KmpProfileResponseStatus

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

fun BaseProfileContext.kmpStatus() = when {
    responseProfileStatus.isError || errors.any { it.level.isError } -> KmpProfileResponseStatus.ERROR
    errors.any { it.level.isWarning } -> KmpProfileResponseStatus.WARNING
    else -> KmpProfileResponseStatus.SUCCESS
}

fun String?.toModelString() = this?.takeIf { it.isNotBlank() } ?: ""

fun KmpProfileDbMode?.toModel() = when (this) {
    KmpProfileDbMode.TEST -> WorkMode.TEST
    KmpProfileDbMode.PROD -> WorkMode.PROD
    else -> WorkMode.DEFAULT
}