package com.lpfun.backend.common.model.extensions

import com.lpfun.backend.common.model.profile.education.ProfileEducation
import com.lpfun.backend.common.model.profile.education.ProfileEducationContext
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalData
import com.lpfun.backend.common.model.profile.skills.ProfileSkillsAndTech
import com.lpfun.backend.common.model.profile.skills.ProfileSkillsContext

infix fun ProfilePersonalContext.applyRequest(profilePersonalData: ProfilePersonalData) {
    requestProfile = profilePersonalData
}

infix fun ProfileEducationContext.applyRequest(profileEducation: ProfileEducation) {
    requestProfile = profileEducation
}

infix fun ProfileSkillsContext.applyRequest(profileSkillsAndTech: ProfileSkillsAndTech) {
    requestProfile = profileSkillsAndTech
}