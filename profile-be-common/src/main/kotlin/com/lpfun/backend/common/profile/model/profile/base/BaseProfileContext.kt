package com.lpfun.backend.common.profile.model.profile.base

import com.lpfun.backend.common.WorkMode
import com.lpfun.backend.common.profile.model.error.IProfileError
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubCreate
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubDelete
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubGet
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubUpdate

abstract class BaseProfileContext(
    var requestProfileId: String = "",
    var responseProfileStatus: ProfileContextStatus = ProfileContextStatus.NONE,
    var errors: MutableList<IProfileError> = mutableListOf(),
    var workMode: WorkMode = WorkMode.DEFAULT,
    var stubCaseGet: ProfileStubGet = ProfileStubGet.NONE,
    var stubCaseCreate: ProfileStubCreate = ProfileStubCreate.NONE,
    var stubCaseDelete: ProfileStubDelete = ProfileStubDelete.NONE,
    var stubCaseUpdate: ProfileStubUpdate = ProfileStubUpdate.NONE
)