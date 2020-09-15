package com.lpfun.di

import com.lpfun.profile.education.ProfileEducationService
import com.lpfun.profile.personaldata.ProfilePersonalDataService
import com.lpfun.profile.skillsandtech.ProfileSkillsAndTechService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val profileModule = Kodein.Module("profile") {
    bind<ProfileEducationService>() with provider { ProfileEducationService() }
    bind<ProfilePersonalDataService>() with provider { ProfilePersonalDataService() }
    bind<ProfileSkillsAndTechService>() with provider { ProfileSkillsAndTechService() }
}