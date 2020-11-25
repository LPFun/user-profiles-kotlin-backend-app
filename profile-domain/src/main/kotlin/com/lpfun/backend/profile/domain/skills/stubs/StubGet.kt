package com.lpfun.backend.profile.domain.skills.stubs

import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.dsl.skills.profileSkills
import com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubGet
import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsContext

val stubGet = cor<ProfileSkillsContext> {
    condition { stubCaseGet != ProfileStubGet.NONE }
    handler {
        condition { stubCaseGet == ProfileStubGet.SUCCESS }
        exec {
            responseProfile = profileSkills {
                id = requestProfileId
                specialization {
                    category = "Development"
                    subCategory = "Mobile developer"
                }
                operatingSystems {
                    +"Android"
                }
                dataBases {
                    +"MySql"
                }
            }
            responseProfileStatus = ProfileContextStatus.FINISHING
        }
    }
}