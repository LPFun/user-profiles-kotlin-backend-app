package com.lpfun.backend.common.model.profile

import com.lpfun.backend.common.model.error.IProfileError

abstract class BaseProfileContext(
    var requestProfileId: String = "",
    var responseProfileStatus: ProfileContextStatus = ProfileContextStatus.NONE,
    var errors: MutableList<IProfileError> = mutableListOf()
)