package com.lpfun.backend.profile.domain.education

import com.lpfun.backend.common.cor.IExec
import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.error.GeneralError
import com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducationContext
import com.lpfun.backend.common.profile.repository.IProfileEducationRepository
import com.lpfun.backend.profile.domain.education.handlers.responsePrepareHandler
import com.lpfun.backend.profile.domain.education.handlers.setupWorkMode
import com.lpfun.backend.profile.domain.education.stubs.stubUpdate

class ProfileEducationUpdateUseCase(
    private val repo: IProfileEducationRepository,
    private val testRepo: IProfileEducationRepository
) : IExec<ProfileEducationContext> {
    override suspend fun execute(ctx: ProfileEducationContext) = chain.execute(ctx.apply {
        repository = repo
        testRepository = testRepo
    })

    companion object {
        val chain = cor<ProfileEducationContext> {
            // Инициализация пайплайна
            execute {
                responseProfileStatus = ProfileContextStatus.RUNNING
            }

            // Установка режима работы
            execute(setupWorkMode)

            // Обработка стабов
            execute(stubUpdate)

            // Валидация

            // Обращение к БД и обработка
            handler {
                condition { responseProfileStatus == ProfileContextStatus.RUNNING }
                exec {
                    responseProfile = repository.update(requestProfile)
                }
                error {
                    responseProfileStatus = ProfileContextStatus.ERROR
                    errors.add(GeneralError(code = "repo-update-error", e = it))
                }
            }

            // Подготовка ответа
            execute(responsePrepareHandler)
        }
    }
}