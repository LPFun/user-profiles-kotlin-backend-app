package com.lpfun.backend.profile.domain.personal

import com.lpfun.backend.common.cor.IExec
import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.common.profile.repository.IProfilePersonalDataRepository
import com.lpfun.backend.profile.domain.personal.handlers.responsePrepareHandler
import com.lpfun.backend.profile.domain.personal.handlers.setupWorkMode
import com.lpfun.backend.profile.domain.personal.stubs.stubProfilePersonalGet

class ProfilePersonalGetUseCase(
    private val repo: IProfilePersonalDataRepository,
    private val testRepo: IProfilePersonalDataRepository
) : IExec<ProfilePersonalContext> {
    override suspend fun execute(ctx: ProfilePersonalContext) = chain.execute(ctx.apply {
        repository = repo
        testRepository = testRepo
    })

    companion object {
        val chain = cor<ProfilePersonalContext> {
            execute {
                responseProfileStatus = ProfileContextStatus.RUNNING
            }

            // Установка режима работы
            execute(setupWorkMode)

            // Валидация

            // Обработка стабов
            execute(stubProfilePersonalGet)

            // Обращение к БД и обработка
            handler {
                condition { responseProfileStatus == ProfileContextStatus.RUNNING }
                exec {
                    responseProfile = repository.get(requestProfile.profileId)
                    responseProfileStatus = ProfileContextStatus.SUCCESS
                }
                error {
                    responseProfileStatus = ProfileContextStatus.ERROR
                }
            }

            // Подготовка ответа
            execute(responsePrepareHandler)
        }
    }
}
