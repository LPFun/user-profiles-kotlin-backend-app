package com.lpfun.backend.common.profile.repository

import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalData

interface IProfilePersonalDataRepository {
    suspend fun get(id: String): ProfilePersonalData
    suspend fun create(profile: ProfilePersonalData): ProfilePersonalData
    suspend fun update(profile: ProfilePersonalData): ProfilePersonalData
    suspend fun delete(id: String): ProfilePersonalData

    companion object {
        val NONE = object : IProfilePersonalDataRepository {
            override suspend fun get(id: String): ProfilePersonalData {
                TODO("Not yet implemented")
            }

            override suspend fun create(profile: ProfilePersonalData): ProfilePersonalData {
                TODO("Not yet implemented")
            }

            override suspend fun update(profile: ProfilePersonalData): ProfilePersonalData {
                TODO("Not yet implemented")
            }

            override suspend fun delete(id: String): ProfilePersonalData {
                TODO("Not yet implemented")
            }

        }
    }
}