package com.lpfun.backend.common.model.profile.personal

import com.lpfun.backend.common.model.profile.base.BaseProfileContext

data class ProfilePersonalContext(
    var requestProfile: ProfilePersonalData = ProfilePersonalData.NONE,
    var responseProfile: ProfilePersonalData = ProfilePersonalData.NONE,
) : BaseProfileContext()