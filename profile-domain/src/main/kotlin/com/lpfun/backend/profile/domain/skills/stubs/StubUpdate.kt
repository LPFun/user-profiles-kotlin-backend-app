package com.lpfun.backend.profile.domain.skills.stubs

import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubUpdate
import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsContext

val stubUpdate = cor<ProfileSkillsContext> {
    condition { stubCaseUpdate != ProfileStubUpdate.NONE }
    handler {
        condition { stubCaseUpdate == ProfileStubUpdate.SUCCESS }
        exec {
            responseProfile = requestProfile
            responseProfileStatus = com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus.FINISHING
        }
    }
}