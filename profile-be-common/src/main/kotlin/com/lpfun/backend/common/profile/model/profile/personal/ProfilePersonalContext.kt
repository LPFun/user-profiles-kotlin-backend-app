package com.lpfun.backend.common.profile.model.profile.personal

import com.lpfun.backend.common.profile.model.profile.base.BaseProfileContext
import com.lpfun.backend.common.profile.repository.IProfilePersonalDataRepository

data class ProfilePersonalContext(
    var requestProfile: ProfilePersonalData = ProfilePersonalData.NONE,
    var responseProfile: ProfilePersonalData = ProfilePersonalData.NONE,
    var repository: IProfilePersonalDataRepository = IProfilePersonalDataRepository.NONE,
    var prodRepository: IProfilePersonalDataRepository = IProfilePersonalDataRepository.NONE,
    var testRepository: IProfilePersonalDataRepository = IProfilePersonalDataRepository.NONE,
) : BaseProfileContext()