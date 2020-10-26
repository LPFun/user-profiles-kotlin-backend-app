package com.lpfun.backend.common.profile.repository

import com.lpfun.backend.common.model.profile.skills.ProfileSkillsAndTech

interface IProfileSkillsAndTechRepository {
    fun get(id: String): ProfileSkillsAndTech
    fun create(profile: ProfileSkillsAndTech): ProfileSkillsAndTech
    fun update(profile: ProfileSkillsAndTech): ProfileSkillsAndTech
    fun delete(id: String): ProfileSkillsAndTech
}