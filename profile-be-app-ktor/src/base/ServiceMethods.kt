package com.lpfun.base

import com.lpfun.backend.common.Constants
import com.lpfun.backend.common.profile.model.error.GeneralError
import com.lpfun.backend.common.profile.model.error.InternalServerError
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducationContext
import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsContext
import com.lpfun.backend.kmp.profile.resultItem
import com.lpfun.backend.profile.logger.ProfileLogger.kv
import com.lpfun.backend.profile.logger.doLoggingSusp
import com.lpfun.transport.multiplatform.profile.KmpProfileDbMode
import com.lpfun.transport.multiplatform.profile.KmpProfileResponse
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationGet
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationResponse
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataGet
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataResponse
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechGet
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechResponse
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.pipeline.*
import org.slf4j.Logger
import java.util.*

val STUB_KEY = "stub"
val STUB_SUCCESS_KEY = "success"
val IN_MEMORY_KEY = "test"
val IN_MEMORY_VALUE = "inmemory"

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

suspend inline fun <reified T : Any, reified K : KmpProfileResponse> PipelineContext<Unit, ApplicationCall>.request(
    logId: String,
    logger: Logger,
    crossinline q: suspend () -> T,
    crossinline block: suspend (T, String) -> K
) {
    val requestId = call.request.headers[Constants.requestIdHeader] ?: UUID.randomUUID().toString()
    try {
        logger.doLoggingSusp(logId, requestId = requestId) {
            val query = q.invoke()
            logger.info("Query for $logId, query {}", kv("requestId", requestId), kv("data", query))
            val response = block(query, requestId)
            call.response.headers.append(Constants.requestIdHeader, requestId)
            call.respond(response)
            logger.info("Response for $logId, query {}", kv("requestId", requestId), kv("data", response))
        }
    } catch (e: Throwable) {
        logger.doLoggingSusp("$logId-error", requestId) {
            val res = when (K::class) {
                KmpProfilePersonalDataResponse::class -> ProfilePersonalContext().apply {
                    errors = mutableListOf(GeneralError(code = "$logId-parse-error", e = e))
                }.resultItem()
                KmpProfileEducationResponse::class -> ProfileEducationContext().apply {
                    errors = mutableListOf(GeneralError(code = "$logId-parse-error", e = e))
                }.resultItem()
                else -> ProfileSkillsContext().apply {
                    errors = mutableListOf(GeneralError(code = "$logId-parse-error", e = e))
                }.resultItem()
            }
            call.respond(res)
        }
    }
}

fun ApplicationRequest.mapToProfileEducationGetRequest() = KmpProfileEducationGet(
    profileId = this.queryParameters["id"],
    debug = KmpProfileEducationGet.Debug().also {
        if (this.headers[IN_MEMORY_KEY] == IN_MEMORY_VALUE) it.db = KmpProfileDbMode.TEST
        when (this.headers[STUB_KEY]) {
            STUB_SUCCESS_KEY -> it.stub = KmpProfileEducationGet.StubCase.SUCCESS
        }
    }
)

fun ApplicationRequest.mapToProfilePersonalGetRequest() = KmpProfilePersonalDataGet(
    profileId = this.queryParameters["id"],
    debug = KmpProfilePersonalDataGet.Debug().also {
        if (this.headers[IN_MEMORY_KEY] == IN_MEMORY_VALUE) it.db = KmpProfileDbMode.TEST
        when (this.headers[STUB_KEY]) {
            STUB_SUCCESS_KEY -> it.stub = KmpProfilePersonalDataGet.StubCase.SUCCESS
        }
    }
)

fun ApplicationRequest.mapToProfileSkillsGetRequest() = KmpProfileSkillsAndTechGet(
    profileId = this.queryParameters["id"],
    debug = KmpProfileSkillsAndTechGet.Debug().also {
        if (this.headers[IN_MEMORY_KEY] == IN_MEMORY_VALUE) it.db = KmpProfileDbMode.TEST
        when (this.headers[STUB_KEY]) {
            STUB_SUCCESS_KEY -> it.stub = KmpProfileSkillsAndTechGet.StubCase.SUCCESS
        }
    }
)