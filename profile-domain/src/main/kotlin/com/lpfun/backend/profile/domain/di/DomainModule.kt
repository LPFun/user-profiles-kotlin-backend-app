package com.lpfun.backend.profile.domain.di

import com.lpfun.backend.common.WorkMode
import com.lpfun.backend.profile.domain.education.ProfileEducationCrud
import com.lpfun.backend.profile.domain.personal.ProfilePersonalCrud
import com.lpfun.backend.profile.domain.skills.ProfileSkillsCrud
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val domainModule = DI.Module("DomainModule") {
    bind<ProfilePersonalCrud>() with provider {
        ProfilePersonalCrud(instance(WorkMode.PROD), instance(WorkMode.TEST))
    }
    bind<ProfileEducationCrud>() with provider {
        ProfileEducationCrud(instance(WorkMode.PROD), instance(WorkMode.TEST))
    }
    bind<ProfileSkillsCrud>() with provider {
        ProfileSkillsCrud(instance(WorkMode.PROD), instance(WorkMode.TEST))
    }
}