package com.lpfun.base

import com.lpfun.backend.common.model.error.InternalServerError
import com.lpfun.backend.common.model.profile.education.ProfileEducationContext
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.common.model.profile.skills.ProfileSkillsContext
import com.lpfun.backend.kmp.profile.resultItem
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationGet
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataGet
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechGet
import io.ktor.http.*

inline fun ProfileEducationContext.request(crossinline block: ProfileEducationContext.() -> Unit) = run {
    try {
        block.invoke(this)
    } catch (t: Throwable) {
        errors.add(InternalServerError.instance)
    }
    resultItem()
}

inline fun ProfilePersonalContext.request(crossinline block: ProfilePersonalContext.() -> Unit) = run {
    try {
        block.invoke(this)
    } catch (t: Throwable) {
        errors.add(InternalServerError.instance)
    }
    resultItem()
}

inline fun ProfileSkillsContext.request(crossinline block: ProfileSkillsContext.() -> Unit) = run {
    try {
        block.invoke(this)
    } catch (t: Throwable) {
        errors.add(InternalServerError.instance)
    }
    resultItem()
}

fun Parameters.mapToProfileEducationGetRequest() = KmpProfileEducationGet(profileId = this["id"])

fun Parameters.mapToProfilePersonalGetRequest() = KmpProfilePersonalDataGet(profileId = this["id"])

fun Parameters.mapToProfileSkillsGetRequest() = KmpProfileSkillsAndTechGet(profileId = this["id"])