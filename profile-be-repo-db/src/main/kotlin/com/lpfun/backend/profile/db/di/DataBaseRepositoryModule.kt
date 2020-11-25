package com.lpfun.backend.profile.db.di

import com.lpfun.backend.common.WorkMode
import com.lpfun.backend.common.profile.repository.IProfileEducationRepository
import com.lpfun.backend.common.profile.repository.IProfilePersonalDataRepository
import com.lpfun.backend.common.profile.repository.IProfileSkillsAndTechRepository
import com.lpfun.backend.profile.db.DataBaseFactory
import com.lpfun.backend.profile.db.education.ProfileEducationRepository
import com.lpfun.backend.profile.db.personal.ProfilePersonalRepo
import com.lpfun.backend.profile.db.skills.ProfileSkillsAndTechRepo
import org.jetbrains.exposed.sql.Database
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.provider
import org.kodein.di.singleton

val dbRepoModule = DI.Module("InMemoryModule") {
    bind<Database>(WorkMode.PROD) with singleton { DataBaseFactory.init() }
    bind<IProfileEducationRepository>(WorkMode.PROD) with provider {
        ProfileEducationRepository()
    }
    bind<IProfileSkillsAndTechRepository>(WorkMode.PROD) with provider {
        ProfileSkillsAndTechRepo()
    }
    bind<IProfilePersonalDataRepository>(WorkMode.PROD) with provider {
        ProfilePersonalRepo()
    }
}