package com.lpfun.backend.common.profile.model.profile.education

import com.lpfun.backend.common.profile.model.profile.base.BaseProfileContext
import com.lpfun.backend.common.profile.repository.IProfileEducationRepository

data class ProfileEducationContext(
    var requestProfile: ProfileEducation = ProfileEducation.NONE,
    var responseProfile: ProfileEducation = ProfileEducation.NONE,
    var repository: IProfileEducationRepository = IProfileEducationRepository.NONE,
    var prodRepository: IProfileEducationRepository = IProfileEducationRepository.NONE,
    var testRepository: IProfileEducationRepository = IProfileEducationRepository.NONE,
) : BaseProfileContext()