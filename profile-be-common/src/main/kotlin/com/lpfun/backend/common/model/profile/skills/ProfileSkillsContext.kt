package com.lpfun.backend.common.model.profile.skills

import com.lpfun.backend.common.model.profile.BaseProfileContext

data class ProfileSkillsContext(
    var requestProfile: ProfileSkillsAndTech = ProfileSkillsAndTech.NONE,
    var responseProfile: ProfileSkillsAndTech = ProfileSkillsAndTech.NONE,
) : BaseProfileContext()