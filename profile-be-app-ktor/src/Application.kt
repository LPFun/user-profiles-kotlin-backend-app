package com.lpfun

import com.lpfun.di.profileModule
import com.lpfun.profile.education.ProfileEducationService
import com.lpfun.profile.education.profileEducationRoute
import com.lpfun.profile.personaldata.ProfilePersonalDataService
import com.lpfun.profile.personaldata.profilePersonalDataRoute
import com.lpfun.profile.skillsandtech.ProfileSkillsAndTechService
import com.lpfun.profile.skillsandtech.profileSkillsAndTechRoute
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.netty.*
import org.kodein.di.instance
import org.kodein.di.ktor.di

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@JvmOverloads
fun Application.module(testing: Boolean = false) {

    di {
        import(profileModule)
    }

    install(CORS) {
        method(HttpMethod.Get)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Post)
        anyHost()
    }
    install(ContentNegotiation) {
        json()
    }
    install(Compression) {
        gzip()
    }

    val educationService by di().instance<ProfileEducationService>()
    val personalDataService by di().instance<ProfilePersonalDataService>()
    val skillsAndTechService by di().instance<ProfileSkillsAndTechService>()

    routing {
        route("/profile") {
            profileEducationRoute(educationService)
            profilePersonalDataRoute(personalDataService)
            profileSkillsAndTechRoute(skillsAndTechService)
        }
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        // Static feature. Try to access `/static/ktor_logo.svg`
        static("/static") {
            resources("static")
        }
    }
}



