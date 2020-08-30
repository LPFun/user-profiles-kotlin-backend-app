package com.lpfun.backend.common.model.profile

data class ProfileContext(
        var requestProfileId: String = "",
        var requestProfile: ProfileBase = ProfileBase.NONE,
        var responseProfile: ProfileBase = ProfileBase.NONE,
        var responseProfileStatus: ProfileContextStatus = ProfileContextStatus.NONE
)