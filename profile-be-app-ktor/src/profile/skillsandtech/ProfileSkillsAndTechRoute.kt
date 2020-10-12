package com.lpfun.profile.skillsandtech

import com.lpfun.base.mapToProfileSkillsGetRequest
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.profileSkillsAndTechRoute(service: ProfileSkillsAndTechService) {
    route("/skills") {
        get {
            call.respond(service.get(call.request.mapToProfileSkillsGetRequest()))
        }

        post {
            call.respond(service.create(call.receive()))
        }

        put {
            call.respond(service.update(call.receive()))
        }

        delete {
            call.respond(service.delete(call.receive()))
        }
    }
}
