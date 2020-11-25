package com.lpfun.base

import com.lpfun.backend.common.profile.model.error.InternalServerError
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducationContext
import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsContext
import com.lpfun.backend.kmp.profile.resultItem
import com.lpfun.transport.multiplatform.profile.KmpProfileDbMode
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationGet
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationResponse
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataGet
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataResponse
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechGet
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechResponse
import io.ktor.request.*


inline fun ProfileEducationContext.request(block: ProfileEducationContext.() -> Unit): KmpProfileEducationResponse {
    try {
        block.invoke(this)
    } catch (t: Throwable) {
        errors.add(InternalServerError.instance)
    }
    return resultItem()
}

inline fun ProfileSkillsContext.request(block: ProfileSkillsContext.() -> Unit): KmpProfileSkillsAndTechResponse {
    try {
        block.invoke(this)
    } catch (t: Throwable) {
        errors.add(InternalServerError.instance)
    }
    return resultItem()
}

inline fun ProfilePersonalContext.request(block: ProfilePersonalContext.() -> Unit): KmpProfilePersonalDataResponse {
    try {
        block.invoke(this)
    } catch (t: Throwable) {
        errors.add(InternalServerError.instance)
    }
    return resultItem()
}

fun ApplicationRequest.mapToProfileEducationGetRequest() = KmpProfileEducationGet(
    profileId = this.queryParameters["id"],
    debug = KmpProfileEducationGet.Debug().also {
        when (this.headers["test"]) {
            "test" -> it.stub = KmpProfileEducationGet.StubCase.RUNNING
            "inmemory" -> it.db = KmpProfileDbMode.TEST
        }
    }
)

fun ApplicationRequest.mapToProfilePersonalGetRequest() = KmpProfilePersonalDataGet(
    profileId = this.queryParameters["id"],
    debug = KmpProfilePersonalDataGet.Debug().also {
        when (this.headers["test"]) {
            "test" -> it.stub = KmpProfilePersonalDataGet.StubCase.RUNNING
            "inmemory" -> it.db = KmpProfileDbMode.TEST
        }
    }
)

fun ApplicationRequest.mapToProfileSkillsGetRequest() = KmpProfileSkillsAndTechGet(
    profileId = this.queryParameters["id"],
    debug = KmpProfileSkillsAndTechGet.Debug().also {
        when (this.headers["test"]) {
            "test" -> it.stub = KmpProfileSkillsAndTechGet.StubCase.RUNNING
            "inmemory" -> it.db = KmpProfileDbMode.TEST
        }
    }
)