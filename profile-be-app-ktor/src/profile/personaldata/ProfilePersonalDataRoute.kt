package com.lpfun.profile.personaldata

import com.lpfun.backend.profile.logger.di.LoggerParam
import com.lpfun.base.mapToProfilePersonalGetRequest
import com.lpfun.base.request
import com.lpfun.main
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataCreate
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataDelete
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataResponse
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataUpdate
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*
import org.kodein.di.factory
import org.kodein.di.ktor.di
import org.slf4j.Logger

fun Route.profilePersonalDataRoute(service: ProfilePersonalDataService) {
    route("/personal") {
        val loggerFactory: (LoggerParam) -> Logger by di().factory()
        val logger = loggerFactory(LoggerParam(::main::class.java))
        get {
            request(
                "profile-personal-get",
                logger,
                { call.request.mapToProfilePersonalGetRequest() },
            ) { q, _ ->
                service.get(q)
            }
        }

        post {
            request<KmpProfilePersonalDataCreate, KmpProfilePersonalDataResponse>(
                "profile-personal-create",
                logger,
                { call.receive() },
            ) { q, _ ->
                service.create(q)
            }
        }

        put {
            request<KmpProfilePersonalDataUpdate, KmpProfilePersonalDataResponse>(
                "profile-personal-update",
                logger,
                { call.receive() }
            ) { query, _ ->
                service.update(query)
            }
        }

        delete {
            request<KmpProfilePersonalDataDelete, KmpProfilePersonalDataResponse>(
                "profile-personal-delete",
                logger,
                { call.receive() }
            ) { query, _ ->
                service.delete(query)
            }
        }
    }
}