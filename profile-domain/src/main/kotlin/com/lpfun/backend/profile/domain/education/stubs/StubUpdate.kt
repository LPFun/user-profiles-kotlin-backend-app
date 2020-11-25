package com.lpfun.backend.profile.domain.education.stubs

import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubUpdate
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducationContext

val stubUpdate = cor<ProfileEducationContext> {
    condition { stubCaseUpdate != ProfileStubUpdate.NONE }
    handler {
        condition { stubCaseUpdate == ProfileStubUpdate.SUCCESS }
        exec {
            responseProfile = requestProfile
            responseProfileStatus = ProfileContextStatus.FINISHING
        }
    }
}