package com.lpfun.backend.profile.domain.personal

import com.lpfun.backend.common.model.profile.personal.ProfilePersonalContext

class ProfilePersonalCrud {
    private val getChain = ProfilePersonalGetChain()
    private val createChain = ProfilePersonalCreateChain()
    private val updateChain = ProfilePersonalUpdateChain()
    private val deleteChain = ProfilePersonalDeleteChain()

    suspend fun get(context: ProfilePersonalContext) = getChain.execute(context)
    suspend fun create(context: ProfilePersonalContext) = createChain.execute(context)
    suspend fun update(context: ProfilePersonalContext) = updateChain.execute(context)
    suspend fun delete(context: ProfilePersonalContext) = deleteChain.execute(context)
}