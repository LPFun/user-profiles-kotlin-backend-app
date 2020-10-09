package com.lpfun.backend.profile.domain.di

import com.lpfun.backend.profile.domain.personal.ProfilePersonalCrud
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.provider

val domainModule = DI.Module("DomainModule") {
    bind<ProfilePersonalCrud>() with provider {
        ProfilePersonalCrud()
    }
}