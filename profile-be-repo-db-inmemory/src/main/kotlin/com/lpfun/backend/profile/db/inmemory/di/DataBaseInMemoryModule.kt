package com.lpfun.backend.profile.db.inmemory.di

import com.lpfun.backend.common.WorkMode
import com.lpfun.backend.common.profile.repository.IProfileEducationRepository
import com.lpfun.backend.common.profile.repository.IProfilePersonalDataRepository
import com.lpfun.backend.common.profile.repository.IProfileSkillsAndTechRepository
import com.lpfun.backend.profile.db.inmemory.DataBaseInMemFactory
import com.lpfun.backend.profile.db.inmemory.education.ProfileEducationRepoInMemory
import com.lpfun.backend.profile.db.inmemory.personal.ProfilePersonalRepoInMemory
import com.lpfun.backend.profile.db.inmemory.skills.ProfileSkillsAndTechRepoInMemory
import org.jetbrains.exposed.sql.Database
import org.kodein.di.*

val dbRepoInMemoryModule = DI.Module("DataBaseInMemoryModule") {
    bind<Database>(WorkMode.TEST) with singleton { DataBaseInMemFactory.init() }
    bind<IProfileEducationRepository>(WorkMode.TEST) with provider {
        ProfileEducationRepoInMemory(instance(WorkMode.TEST))
    }
    bind<IProfileSkillsAndTechRepository>(WorkMode.TEST) with provider {
        ProfileSkillsAndTechRepoInMemory(instance(WorkMode.TEST))
    }
    bind<IProfilePersonalDataRepository>(WorkMode.TEST) with provider {
        ProfilePersonalRepoInMemory(instance(WorkMode.TEST))
    }
}