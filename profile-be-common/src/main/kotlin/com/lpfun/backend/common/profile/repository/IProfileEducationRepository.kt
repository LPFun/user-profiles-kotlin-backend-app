package com.lpfun.backend.common.profile.repository

import com.lpfun.backend.common.profile.model.profile.education.ProfileEducation

interface IProfileEducationRepository {
    suspend fun get(id: String): ProfileEducation
    suspend fun create(profile: ProfileEducation): ProfileEducation
    suspend fun update(profile: ProfileEducation): ProfileEducation
    suspend fun delete(id: String): ProfileEducation

    companion object {
        val NONE = object : IProfileEducationRepository {
            override suspend fun get(id: String): ProfileEducation {
                TODO("Not yet implemented")
            }

            override suspend fun create(profile: ProfileEducation): ProfileEducation {
                TODO("Not yet implemented")
            }

            override suspend fun update(profile: ProfileEducation): ProfileEducation {
                TODO("Not yet implemented")
            }

            override suspend fun delete(id: String): ProfileEducation {
                TODO("Not yet implemented")
            }

        }
    }
}