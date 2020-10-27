package com.lpfun.backend.common.profile.repository

import com.lpfun.backend.common.model.profile.personal.ProfilePersonalData

interface IProfilePersonalDataRepository {
    suspend fun get(id: String): ProfilePersonalData
    suspend fun create(profile: ProfilePersonalData): ProfilePersonalData
    suspend fun update(profile: ProfilePersonalData): ProfilePersonalData
    suspend fun delete(id: String): ProfilePersonalData
}