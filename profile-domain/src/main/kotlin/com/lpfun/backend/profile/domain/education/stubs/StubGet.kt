package com.lpfun.backend.profile.domain.education.stubs

import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.dsl.education.profileEducation
import com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubGet
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducationContext

val stubGet = cor<ProfileEducationContext> {
    condition { stubCaseGet != ProfileStubGet.NONE }
    handler {
        condition { stubCaseGet == ProfileStubGet.SUCCESS }
        exec {
            responseProfile = profileEducation {
                id = requestProfileId
                mainEducation {
                    university = "Garvard"
                    department = "IT"
                    speciality = "Programming"
                    yearOfCompletion = "2020"
                }
                +additionalEducation {
                    nameOfInstitution = "OTUS"
                    courseName = "Kotlin"
                    yearOfCompletion = "2020"
                }
            }

            responseProfileStatus = ProfileContextStatus.FINISHING
        }
    }
}