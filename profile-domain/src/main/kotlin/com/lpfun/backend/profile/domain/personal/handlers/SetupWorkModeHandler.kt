package com.lpfun.backend.profile.domain.personal.handlers

import com.lpfun.backend.common.WorkMode
import com.lpfun.backend.common.cor.corHandler
import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalContext

val setupWorkMode = corHandler<ProfilePersonalContext> {
    exec {
        repository = when (workMode) {
            WorkMode.TEST -> testRepository
            WorkMode.PROD -> prodRepository
        }
    }
}