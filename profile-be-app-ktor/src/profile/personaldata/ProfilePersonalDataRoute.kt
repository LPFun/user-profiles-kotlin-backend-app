package com.lpfun.profile.personaldata

import com.lpfun.base.handleParams
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataCreate
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataDelete
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataUpdate
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.profilePersonalDataRoute(service: ProfilePersonalDataService) {
    route("/personal") {
        get {
            call.respond(service.get(call.request.queryParameters.handleParams()))
        }

        post {
            val query = call.receiveOrNull<KmpProfilePersonalDataCreate>()
            query?.let {
                call.respond(service.create(it))
            } ?: call.respond(HttpStatusCode.BadRequest)
        }

        put {
            val query = call.receiveOrNull<KmpProfilePersonalDataUpdate>()
            query?.let {
                call.respond(service.update(it))
            } ?: call.respond(HttpStatusCode.BadRequest)
        }

        delete {
            val query = call.receiveOrNull<KmpProfilePersonalDataDelete>()
            query?.let {
                call.respond(service.delete(query))
            } ?: call.respond(HttpStatusCode.BadRequest)
        }
    }
}