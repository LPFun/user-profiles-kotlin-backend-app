package com.lpfun.backend.profile.domain.personal.stubs

import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubDelete
import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalData

val stubProfilePersonalDelete = cor<ProfilePersonalContext> {
    condition { stubCaseDelete != ProfileStubDelete.NONE }
    handler {
        condition { stubCaseDelete == ProfileStubDelete.SUCCESS }
        exec {
            responseProfile = ProfilePersonalData()
            responseProfileStatus = ProfileContextStatus.FINISHING
        }
    }
}
