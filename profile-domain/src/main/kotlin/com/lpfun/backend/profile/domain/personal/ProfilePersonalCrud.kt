package com.lpfun.backend.profile.domain.personal

import com.lpfun.backend.common.model.profile.personal.ProfilePersonalContext

class ProfilePersonalCrud {
    private val getUseCase = ProfilePersonalGetUseCase()
    private val createUseCase = ProfilePersonalCreateUseCase()
    private val updateUseCase = ProfilePersonalUpdateUseCase()
    private val deleteUseCase = ProfilePersonalDeleteUseCase()

    suspend fun get(context: ProfilePersonalContext) = getUseCase.execute(context)
    suspend fun create(context: ProfilePersonalContext) = createUseCase.execute(context)
    suspend fun update(context: ProfilePersonalContext) = updateUseCase.execute(context)
    suspend fun delete(context: ProfilePersonalContext) = deleteUseCase.execute(context)
}