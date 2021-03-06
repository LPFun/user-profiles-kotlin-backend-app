package com.lpfun.di

import com.lpfun.backend.profile.db.di.dbRepoModule
import com.lpfun.backend.profile.db.inmemory.di.dbRepoInMemoryModule
import com.lpfun.backend.profile.domain.di.domainModule
import com.lpfun.backend.profile.logger.di.loggingModule
import com.lpfun.profile.education.ProfileEducationService
import com.lpfun.profile.personaldata.ProfilePersonalDataService
import com.lpfun.profile.skillsandtech.ProfileSkillsAndTechService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val profileModule = DI.Module("ProfileModule") {
    import(domainModule)
    import(dbRepoModule)
    import(dbRepoInMemoryModule)
    import(loggingModule)
    bind<ProfileEducationService>() with provider { ProfileEducationService(instance()) }
    bind<ProfilePersonalDataService>() with provider { ProfilePersonalDataService(instance()) }
    bind<ProfileSkillsAndTechService>() with provider { ProfileSkillsAndTechService(instance()) }
}