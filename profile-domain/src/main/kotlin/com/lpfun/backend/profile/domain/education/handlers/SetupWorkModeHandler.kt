package com.lpfun.backend.profile.domain.education.handlers

import com.lpfun.backend.common.WorkMode
import com.lpfun.backend.common.cor.corHandler
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducationContext

val setupWorkMode = corHandler<ProfileEducationContext> {
    exec {
        repository = when (workMode) {
            WorkMode.TEST -> testRepository
            WorkMode.PROD -> prodRepository
        }
    }
}