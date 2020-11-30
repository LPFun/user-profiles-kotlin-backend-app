package com.lpfun.backend.profile.domain.skills

import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsContext
import com.lpfun.backend.common.profile.repository.IProfileSkillsAndTechRepository

class ProfileSkillsCrud(
    repository: IProfileSkillsAndTechRepository = IProfileSkillsAndTechRepository.NONE,
    testRepository: IProfileSkillsAndTechRepository = IProfileSkillsAndTechRepository.NONE
) {
    private val getUseCase = ProfileSkillsGetUseCase(repository, testRepository)
    private val createUseCase = ProfileSkillsCreateUseCase(repository, testRepository)
    private val updateUseCase = ProfileSkillsUpdateUseCase(repository, testRepository)
    private val deleteUseCase = ProfileSkillsDeleteUseCase(repository, testRepository)

    suspend fun get(context: ProfileSkillsContext) = getUseCase.execute(context)
    suspend fun create(context: ProfileSkillsContext) = createUseCase.execute(context)
    suspend fun update(context: ProfileSkillsContext) = updateUseCase.execute(context)
    suspend fun delete(context: ProfileSkillsContext) = deleteUseCase.execute(context)
}