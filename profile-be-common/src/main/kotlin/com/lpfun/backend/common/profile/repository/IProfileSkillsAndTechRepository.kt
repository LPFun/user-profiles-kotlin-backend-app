package com.lpfun.backend.common.profile.repository

import com.lpfun.backend.common.model.profile.skills.ProfileSkillsAndTech

interface IProfileSkillsAndTechRepository {
    suspend fun get(id: String): ProfileSkillsAndTech
    suspend fun create(profile: ProfileSkillsAndTech): ProfileSkillsAndTech
    suspend fun update(profile: ProfileSkillsAndTech): ProfileSkillsAndTech
    suspend fun delete(id: String): ProfileSkillsAndTech
}