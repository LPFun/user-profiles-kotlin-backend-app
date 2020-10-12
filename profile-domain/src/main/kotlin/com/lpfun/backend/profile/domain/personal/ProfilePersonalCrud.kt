package com.lpfun.backend.profile.domain.personal

import com.lpfun.backend.common.model.profile.personal.ProfilePersonalContext

class ProfilePersonalCrud {
    private val getChain = ProfilePersonalGetUseCase()
    private val createChain = ProfilePersonalCreateUseCase()
    private val updateChain = ProfilePersonalUpdateUseCase()
    private val deleteChain = ProfilePersonalDeleteUseCase()

    suspend fun get(context: ProfilePersonalContext) = getChain.execute(context)
    suspend fun create(context: ProfilePersonalContext) = createChain.execute(context)
    suspend fun update(context: ProfilePersonalContext) = updateChain.execute(context)
    suspend fun delete(context: ProfilePersonalContext) = deleteChain.execute(context)
}