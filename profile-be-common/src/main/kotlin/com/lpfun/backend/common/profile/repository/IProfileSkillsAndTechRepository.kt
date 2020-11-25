package com.lpfun.backend.common.profile.repository

import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsAndTech

interface IProfileSkillsAndTechRepository {
    suspend fun get(id: String): ProfileSkillsAndTech
    suspend fun create(profile: ProfileSkillsAndTech): ProfileSkillsAndTech
    suspend fun update(profile: ProfileSkillsAndTech): ProfileSkillsAndTech
    suspend fun delete(id: String): ProfileSkillsAndTech

    companion object {
        val NONE = object : IProfileSkillsAndTechRepository {
            override suspend fun get(id: String): ProfileSkillsAndTech {
                TODO("Not yet implemented")
            }

            override suspend fun create(profile: ProfileSkillsAndTech): ProfileSkillsAndTech {
                TODO("Not yet implemented")
            }

            override suspend fun update(profile: ProfileSkillsAndTech): ProfileSkillsAndTech {
                TODO("Not yet implemented")
            }

            override suspend fun delete(id: String): ProfileSkillsAndTech {
                TODO("Not yet implemented")
            }

        }
    }
}