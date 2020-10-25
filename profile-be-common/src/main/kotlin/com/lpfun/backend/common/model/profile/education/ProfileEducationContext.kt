package com.lpfun.backend.common.model.profile.education

import com.lpfun.backend.common.model.profile.base.BaseProfileContext

data class ProfileEducationContext(
    var requestProfile: ProfileEducation = ProfileEducation.NONE,
    var responseProfile: ProfileEducation = ProfileEducation.NONE,
) : BaseProfileContext()