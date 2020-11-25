package com.lpfun.backend.profile.domain.personal

import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.common.profile.repository.IProfilePersonalDataRepository

class ProfilePersonalCrud(
    repository: IProfilePersonalDataRepository = IProfilePersonalDataRepository.NONE,
    testRepository: IProfilePersonalDataRepository = IProfilePersonalDataRepository.NONE,
) {
    private val getUseCase = ProfilePersonalGetUseCase(repository, testRepository)
    private val createUseCase = ProfilePersonalCreateUseCase(repository, testRepository)
    private val updateUseCase = ProfilePersonalUpdateUseCase(repository, testRepository)
    private val deleteUseCase = ProfilePersonalDeleteUseCase(repository, testRepository)

    suspend fun get(context: ProfilePersonalContext) = getUseCase.execute(context)
    suspend fun create(context: ProfilePersonalContext) = createUseCase.execute(context)
    suspend fun update(context: ProfilePersonalContext) = updateUseCase.execute(context)
    suspend fun delete(context: ProfilePersonalContext) = deleteUseCase.execute(context)
}