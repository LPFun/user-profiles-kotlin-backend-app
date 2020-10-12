package com.lpfun.backend.profile.domain.di

import com.lpfun.backend.profile.domain.education.ProfileEducationCrud
import com.lpfun.backend.profile.domain.personal.ProfilePersonalCrud
import com.lpfun.backend.profile.domain.skills.ProfileSkillsCrud
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val domainModule = DI.Module("DomainModule") {
    bind<ProfilePersonalCrud>() with singleton {
        ProfilePersonalCrud()
    }
    bind<ProfileEducationCrud>() with singleton {
        ProfileEducationCrud()
    }
    bind<ProfileSkillsCrud>() with singleton {
        ProfileSkillsCrud()
    }
}