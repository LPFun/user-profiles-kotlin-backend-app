package com.lpfun.backend.profile.domain.education

import com.lpfun.backend.common.model.profile.education.ProfileEducationContext

class ProfileEducationCrud {
    private val getChain = ProfileEducationGetChain()
    private val createChain = ProfileEducationCreateChain()
    private val updateChain = ProfileEducationUpdateChain()
    private val deleteChain = ProfileEducationDeleteChain()

    suspend fun get(context: ProfileEducationContext) = getChain.exec(context)
    suspend fun create(context: ProfileEducationContext) = createChain.exec(context)
    suspend fun update(context: ProfileEducationContext) = updateChain.exec(context)
    suspend fun delete(context: ProfileEducationContext) = deleteChain.exec(context)
}