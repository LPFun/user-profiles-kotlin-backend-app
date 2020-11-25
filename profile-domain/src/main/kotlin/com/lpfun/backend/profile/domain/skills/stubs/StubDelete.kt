package com.lpfun.backend.profile.domain.skills.stubs

import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubDelete
import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsAndTech
import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsContext

val stubDelete = cor<ProfileSkillsContext> {
    condition { stubCaseDelete != ProfileStubDelete.NONE }
    handler {
        condition { stubCaseDelete == ProfileStubDelete.SUCCESS }
        exec {
            responseProfile = ProfileSkillsAndTech()
            responseProfileStatus = com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus.FINISHING
        }
    }
}