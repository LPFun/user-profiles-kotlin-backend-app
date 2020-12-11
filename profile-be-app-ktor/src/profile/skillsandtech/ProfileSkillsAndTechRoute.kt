package com.lpfun.profile.skillsandtech

import com.lpfun.base.mapToProfileSkillsGetRequest
import com.lpfun.base.request
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechCreate
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechDelete
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechUpdate
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*
import org.slf4j.Logger

fun Route.profileSkillsAndTechRoute(service: ProfileSkillsAndTechService, logger: Logger) {
    route("/skills") {
        get {
            request(
                logId = "profile-skills-get",
                logger = logger,
                q = { call.request.mapToProfileSkillsGetRequest() }
            ) { q, _ ->
                service.get(q)
            }
        }

        post {
            request(
                logId = "profile-skills-create",
                logger = logger,
                q = { call.receive<KmpProfileSkillsAndTechCreate>() }
            ) { q, _ ->
                service.create(q)
            }
        }

        put {
            request(
                logId = "profile-skills-create",
                logger = logger,
                q = { call.receive<KmpProfileSkillsAndTechUpdate>() }
            ) { q, _ ->
                service.update(q)
            }
        }

        delete {
            request(
                logId = "profile-skills-create",
                logger = logger,
                q = { call.receive<KmpProfileSkillsAndTechDelete>() }
            ) { q, _ ->
                service.delete(q)
            }
        }
    }
}
