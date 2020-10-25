package com.lpfun.backend.profile.domain.skills

import com.lpfun.backend.common.model.profile.skills.ProfileSkillsContext

class ProfileSkillsCrud {
    private val getUseCase = ProfileSkillsGetUseCase()
    private val createUseCase = ProfileSkillsCreateUseCase()
    private val updateUseCase = ProfileSkillsUpdateUseCase()
    private val deleteUseCase = ProfileSkillsDeleteUseCase()

    suspend fun get(context: ProfileSkillsContext) = getUseCase.execute(context)
    suspend fun create(context: ProfileSkillsContext) = createUseCase.execute(context)
    suspend fun update(context: ProfileSkillsContext) = updateUseCase.execute(context)
    suspend fun delete(context: ProfileSkillsContext) = deleteUseCase.execute(context)
}