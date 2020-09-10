package com.lpfun.profile.education

import com.lpfun.base.handleParams
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationCreate
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationDelete
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationUpdate
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.profileEducationRoute(service: ProfileEducationService) {
    route("/education") {
        get {
            call.respond(service.get(call.request.queryParameters.handleParams()))
        }
        put {
            val body = call.receiveOrNull<KmpProfileEducationUpdate>()
            body?.let {
                call.respond(service.update(body))
            } ?: call.respond(HttpStatusCode.BadRequest)
        }
        delete {
            val body = call.receiveOrNull<KmpProfileEducationDelete>()
            body?.let {
                call.respond(service.delete(body))
            } ?: call.respond(HttpStatusCode.BadRequest)
        }
        post {
            val body = call.receiveOrNull<KmpProfileEducationCreate>()
            body?.let {
                call.respond(service.create(body))
            } ?: call.respond(HttpStatusCode.BadRequest)
        }
    }
}