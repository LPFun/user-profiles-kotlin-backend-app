package com.lpfun.backend.profile.domain.education

import com.lpfun.backend.common.model.profile.education.ProfileEducationContext

class ProfileEducationCrud {
    private val getChain = ProfileEducationGetUseCase()
    private val createChain = ProfileEducationCreateUseCase()
    private val updateChain = ProfileEducationUpdateUseCase()
    private val deleteChain = ProfileEducationDeleteUseCase()

    suspend fun get(context: ProfileEducationContext) = getChain.execute(context)
    suspend fun create(context: ProfileEducationContext) = createChain.execute(context)
    suspend fun update(context: ProfileEducationContext) = updateChain.execute(context)
    suspend fun delete(context: ProfileEducationContext) = deleteChain.execute(context)
}