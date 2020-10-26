package com.lpfun.backend.common.profile.repository

import com.lpfun.backend.common.model.profile.personal.ProfilePersonalData

interface IProfilePersonalDataRepository {
    fun get(id: String): ProfilePersonalData
    fun create(profile: ProfilePersonalData): ProfilePersonalData
    fun update(profile: ProfilePersonalData): ProfilePersonalData
    fun delete(id: String): ProfilePersonalData
}