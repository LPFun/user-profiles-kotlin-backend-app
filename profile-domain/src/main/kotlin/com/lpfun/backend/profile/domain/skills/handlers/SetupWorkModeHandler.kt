package com.lpfun.backend.profile.domain.skills.handlers

import com.lpfun.backend.common.WorkMode
import com.lpfun.backend.common.cor.corHandler
import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsContext

val setupWorkMode = corHandler<ProfileSkillsContext> {
    exec {
        repository = when (workMode) {
            WorkMode.TEST -> testRepository
            WorkMode.PROD -> prodRepository
        }
    }
}