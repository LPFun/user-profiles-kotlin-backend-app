package com.lpfun.backend.profile.domain.education.stubs

import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubDelete
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducation
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducationContext

val stubDelete = cor<ProfileEducationContext> {
    condition { stubCaseDelete != ProfileStubDelete.NONE }
    handler {
        condition { stubCaseDelete == ProfileStubDelete.SUCCESS }
        exec {
            responseProfile = ProfileEducation()
            responseProfileStatus = ProfileContextStatus.FINISHING
        }
    }
}