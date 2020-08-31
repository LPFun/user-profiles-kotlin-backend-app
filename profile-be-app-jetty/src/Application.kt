package com.lpfun

import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationDelete
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationSave
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CORS) {
        method(HttpMethod.Get)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        anyHost()
    }
    install(ContentNegotiation) {
        json()
    }
    install(Compression) {
        gzip()
    }

    val service = ProfileEducationService()

    routing {
        route("/profile") {
            route("/education") {
                get {
                    val profileId = call.request.queryParameters["id"] ?: ""
                    call.respond(service.get(profileId))
                }
                put {
                    val body = call.receiveOrNull<KmpProfileEducationSave>()
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
            }

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

