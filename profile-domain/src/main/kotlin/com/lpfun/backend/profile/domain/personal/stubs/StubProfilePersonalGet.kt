package com.lpfun.backend.profile.domain.personal.stubs

import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.dsl.personal.profilePersonalData
import com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubGet
import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalContext

val stubProfilePersonalGet = cor<ProfilePersonalContext> {
    condition { stubCaseGet != ProfileStubGet.NONE }
    handler {
        condition { stubCaseGet == ProfileStubGet.SUCCESS }
        exec {
            responseProfile = profilePersonalData {
                id = requestProfile.profileId
                name {
                    first = "John"
                    second = "Junior"
                    last = "Smith"
                    display = "John Smith"
                }
                contacts {
                    phone = "+1234"
                    email = "mail@mail.com"
                }
                location {
                    country = "Test Country"
                    city = "Test City"
                }
            }
            responseProfileStatus = ProfileContextStatus.FINISHING
        }
    }
}