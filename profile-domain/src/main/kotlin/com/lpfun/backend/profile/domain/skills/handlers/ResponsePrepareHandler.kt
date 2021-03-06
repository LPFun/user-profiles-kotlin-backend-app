package com.lpfun.backend.profile.domain.skills.handlers

import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsContext

val responsePrepareHandler = cor<ProfileSkillsContext> {
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