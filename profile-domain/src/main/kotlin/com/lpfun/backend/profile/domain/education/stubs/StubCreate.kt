package com.lpfun.backend.profile.domain.education.stubs

import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubCreate
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducationContext

val stubCreate = cor<ProfileEducationContext> {
    condition { stubCaseCreate != ProfileStubCreate.NONE }
    handler {
        condition { stubCaseCreate == ProfileStubCreate.SUCCESS }
        exec {
            responseProfile = requestProfile.apply {
                profileId = "test-id"
            }
            responseProfileStatus = ProfileContextStatus.FINISHING
        }
    }
}