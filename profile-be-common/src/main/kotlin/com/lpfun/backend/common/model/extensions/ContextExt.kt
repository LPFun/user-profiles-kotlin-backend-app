package com.lpfun.backend.common.model.extensions

import com.lpfun.backend.common.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalData

infix fun ProfilePersonalContext.applyRequest(profilePersonalData: ProfilePersonalData) {
    requestProfile = profilePersonalData
}