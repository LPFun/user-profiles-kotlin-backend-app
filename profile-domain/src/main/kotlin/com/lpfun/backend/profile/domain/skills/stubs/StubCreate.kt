package com.lpfun.backend.profile.domain.skills.stubs

import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubCreate
import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsContext

val stubCreate = cor<ProfileSkillsContext> {
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