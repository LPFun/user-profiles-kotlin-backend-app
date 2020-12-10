package com.lpfun.profile.education

import com.lpfun.base.mapToProfileEducationGetRequest
import com.lpfun.base.request
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationCreate
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationDelete
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationUpdate
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*
import org.slf4j.Logger

fun Route.profileEducationRoute(service: ProfileEducationService, logger: Logger) {
    route("/education") {
        get {
            request(
                logId = "profile-education-get",
                logger = logger,
                q = { call.request.mapToProfileEducationGetRequest() }
            ) { q, _ ->
                service.get(q)
            }
        }
        put {
            request(
                logId = "profile-education-update",
                logger = logger,
                q = { call.receive<KmpProfileEducationUpdate>() }
            ) { q, _ ->
                service.update(q)
            }
        }

        delete {
            request(
                logId = "profile-education-delete",
                logger = logger,
                q = { call.receive<KmpProfileEducationDelete>() }
            ) { q, _ ->
                service.delete(q)
            }
        }

        post {
            request(
                logId = "profile-education-create",
                logger = logger,
                q = { call.receive<KmpProfileEducationCreate>() }
            ) { q, _ ->
                service.create(q)
            }
        }
    }
}