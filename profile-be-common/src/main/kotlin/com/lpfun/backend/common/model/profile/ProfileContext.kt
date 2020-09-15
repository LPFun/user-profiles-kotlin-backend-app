package com.lpfun.backend.common.model.profile

import com.lpfun.backend.common.model.error.IProfileError

data class ProfileContext(
        var requestProfileId: String = "",
        var requestProfile: ProfileBase = ProfileBase.NONE,
        var responseProfile: ProfileBase = ProfileBase.NONE,
        var responseProfileStatus: ProfileContextStatus = ProfileContextStatus.NONE,
        var errors: MutableList<IProfileError> = mutableListOf()
)