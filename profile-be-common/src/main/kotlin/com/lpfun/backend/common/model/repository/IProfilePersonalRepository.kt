package com.lpfun.backend.common.model.repository

import com.lpfun.backend.common.model.profile.personal.ProfilePersonalData

interface IProfilePersonalRepository {
    suspend fun get(profileId: String): ProfilePersonalData
    suspend fun delete(profileId: String): ProfilePersonalData
    suspend fun create(profile: ProfilePersonalData): ProfilePersonalData
    suspend fun update(profile: ProfilePersonalData): ProfilePersonalData
}