package com.lpfun.di

import com.lpfun.profile.education.ProfileEducationService
import com.lpfun.profile.personaldata.ProfilePersonalDataService
import com.lpfun.profile.skillsandtech.ProfileSkillsAndTechService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.provider

val profileModule = DI.Module("profile") {
    bind<ProfileEducationService>() with provider { ProfileEducationService() }
    bind<ProfilePersonalDataService>() with provider { ProfilePersonalDataService() }
    bind<ProfileSkillsAndTechService>() with provider { ProfileSkillsAndTechService() }
}