package com.lpfun.backend.common.profile.repository

import com.lpfun.backend.common.model.profile.education.ProfileEducation

interface IProfileEducationRepository {
    suspend fun get(id: String): ProfileEducation
    suspend fun create(profile: ProfileEducation): ProfileEducation
    suspend fun update(profile: ProfileEducation): ProfileEducation
    suspend fun delete(id: String): ProfileEducation
}