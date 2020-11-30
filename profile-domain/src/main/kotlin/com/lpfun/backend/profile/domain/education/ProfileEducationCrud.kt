package com.lpfun.backend.profile.domain.education

import com.lpfun.backend.common.profile.model.profile.education.ProfileEducationContext
import com.lpfun.backend.common.profile.repository.IProfileEducationRepository

class ProfileEducationCrud(
    repository: IProfileEducationRepository = IProfileEducationRepository.NONE,
    testRepository: IProfileEducationRepository = IProfileEducationRepository.NONE,
) {
    private val getUseCase = ProfileEducationGetUseCase(repository, testRepository)
    private val createUseCase = ProfileEducationCreateUseCase(repository, testRepository)
    private val updateUseCase = ProfileEducationUpdateUseCase(repository, testRepository)
    private val deleteUseCase = ProfileEducationDeleteUseCase(repository, testRepository)

    suspend fun get(context: ProfileEducationContext) = getUseCase.execute(context)
    suspend fun create(context: ProfileEducationContext) = createUseCase.execute(context)
    suspend fun update(context: ProfileEducationContext) = updateUseCase.execute(context)
    suspend fun delete(context: ProfileEducationContext) = deleteUseCase.execute(context)
}