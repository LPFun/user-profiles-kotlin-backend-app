package com.lpfun.backend.profile.domain.education

import com.lpfun.backend.common.model.profile.education.ProfileEducationContext

class ProfileEducationCrud {
    private val getUseCase = ProfileEducationGetUseCase()
    private val createUseCase = ProfileEducationCreateUseCase()
    private val updateUseCase = ProfileEducationUpdateUseCase()
    private val deleteUseCase = ProfileEducationDeleteUseCase()

    suspend fun get(context: ProfileEducationContext) = getUseCase.execute(context)
    suspend fun create(context: ProfileEducationContext) = createUseCase.execute(context)
    suspend fun update(context: ProfileEducationContext) = updateUseCase.execute(context)
    suspend fun delete(context: ProfileEducationContext) = deleteUseCase.execute(context)
}