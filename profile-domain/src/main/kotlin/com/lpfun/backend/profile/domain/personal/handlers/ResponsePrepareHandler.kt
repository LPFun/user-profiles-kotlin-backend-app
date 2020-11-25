package com.lpfun.backend.profile.domain.personal.handlers

import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalContext

val responsePrepareHandler = cor<ProfilePersonalContext> {
    handler {
        condition {
            responseProfileStatus in arrayListOf(ProfileContextStatus.RUNNING, ProfileContextStatus.FINISHING)
        }
        exec {
            responseProfileStatus = ProfileContextStatus.SUCCESS
        }
    }
    handler {
        condition { responseProfileStatus != ProfileContextStatus.SUCCESS }
        exec { responseProfileStatus = ProfileContextStatus.ERROR }
    }
}