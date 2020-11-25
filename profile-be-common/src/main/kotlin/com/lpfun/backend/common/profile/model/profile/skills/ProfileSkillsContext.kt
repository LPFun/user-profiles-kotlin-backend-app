package com.lpfun.backend.common.profile.model.profile.skills

import com.lpfun.backend.common.profile.model.profile.base.BaseProfileContext
import com.lpfun.backend.common.profile.repository.IProfileSkillsAndTechRepository

data class ProfileSkillsContext(
    var requestProfile: ProfileSkillsAndTech = ProfileSkillsAndTech.NONE,
    var responseProfile: ProfileSkillsAndTech = ProfileSkillsAndTech.NONE,
    var repository: IProfileSkillsAndTechRepository = IProfileSkillsAndTechRepository.NONE,
    var testRepository: IProfileSkillsAndTechRepository = IProfileSkillsAndTechRepository.NONE,
    var prodRepository: IProfileSkillsAndTechRepository = IProfileSkillsAndTechRepository.NONE,
) : BaseProfileContext()