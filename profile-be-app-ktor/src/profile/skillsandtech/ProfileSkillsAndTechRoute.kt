package com.lpfun.profile.skillsandtech

import com.lpfun.base.mapToProfileSkillsGetRequest
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.profileSkillsAndTechRoute(service: ProfileSkillsAndTechService) {
    route("/skills") {
        get {
            call.respond(service.get(call.request.queryParameters.mapToProfileSkillsGetRequest()))
        }

        post {
            call.respond(service.create(call.receiveOrNull()))
        }

        put {
            call.respond(service.update(call.receiveOrNull()))
        }

        delete {
            call.respond(service.delete(call.receiveOrNull()))
        }
    }
}
