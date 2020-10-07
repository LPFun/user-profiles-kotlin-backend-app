package com.lpfun.backend.common.model.repository

import com.lpfun.backend.common.model.profile.skills.ProfileSkillsAndTech

interface IProfileSkillsAndTechRepository {
    fun get(profileId: String): ProfileSkillsAndTech
    fun delete(profileId: String): ProfileSkillsAndTech
    fun create(profile: ProfileSkillsAndTech): ProfileSkillsAndTech
    fun update(profile: ProfileSkillsAndTech): ProfileSkillsAndTech
}