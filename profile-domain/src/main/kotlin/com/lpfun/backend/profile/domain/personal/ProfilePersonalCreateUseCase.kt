package com.lpfun.backend.profile.domain.personal

import com.lpfun.backend.common.cor.IExec
import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.error.GeneralError
import com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.common.profile.repository.IProfilePersonalDataRepository
import com.lpfun.backend.profile.domain.personal.handlers.responsePrepareHandler
import com.lpfun.backend.profile.domain.personal.handlers.setupWorkMode
import com.lpfun.backend.profile.domain.personal.stubs.stubProfilePersonalCreate

class ProfilePersonalCreateUseCase(
    private val repo: IProfilePersonalDataRepository,
    private val testRepo: IProfilePersonalDataRepository
) : IExec<ProfilePersonalContext> {
    override suspend fun execute(ctx: ProfilePersonalContext) = chain.execute(ctx.apply {
        repository = repo
        testRepository = testRepo
    })

    companion object {
        val chain = cor<ProfilePersonalContext> {
            // Инициализация пайплайна
            execute {
                responseProfileStatus = ProfileContextStatus.RUNNING
            }

            // Установка режима работы
            execute(setupWorkMode)

            // Валидация


            // Обработка стабов
            execute(stubProfilePersonalCreate)

            // Работа с БД
            handler {
                condition { responseProfileStatus == ProfileContextStatus.RUNNING }
                exec {
                    responseProfile = repository.create(requestProfile)
                }
                error {
                    responseProfileStatus = ProfileContextStatus.ERROR
                    errors.add(GeneralError(code = "repo-create-error", e = it))
                }
            }

            // Обработка ответа
            execute(responsePrepareHandler)
        }
    }
}
