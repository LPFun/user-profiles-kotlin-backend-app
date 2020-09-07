package com.lpfun.base

import com.lpfun.backend.common.model.error.InternalServerError
import com.lpfun.backend.common.model.profile.ProfileContext
import com.lpfun.backend.kmp.profile.resultItem
import com.lpfun.transport.multiplatform.profile.KmpProfileResponse

inline fun <reified R : KmpProfileResponse> ProfileContext.request(crossinline block: ProfileContext.() -> Unit) = run {
    try {
        block.invoke(this)
    } catch (t: Throwable) {
        errors.add(InternalServerError.instance)
    }
    resultItem<R>()
}