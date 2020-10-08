package com.lpfun.backend.profile.domain.education

import com.lpfun.backend.common.model.profile.personal.ProfilePersonalContext

class ProfilePersonalCrud {
    private val getChain = ProfilePersonalGetChain()
    private val createChain = ProfilePersonalCreateChain()
    private val updateChain = ProfilePersonalUpdateChain()
    private val deleteChain = ProfilePersonalDeleteChain()

    suspend fun get(context: ProfilePersonalContext) = getChain.exec(context)
    suspend fun create(context: ProfilePersonalContext) = createChain.exec(context)
    suspend fun update(context: ProfilePersonalContext) = updateChain.exec(context)
    suspend fun delete(context: ProfilePersonalContext) = deleteChain.exec(context)
}