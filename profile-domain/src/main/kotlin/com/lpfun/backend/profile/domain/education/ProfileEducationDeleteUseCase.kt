package com.lpfun.backend.profile.domain.education

import com.lpfun.backend.common.cor.IExec
import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducationContext
import com.lpfun.backend.common.profile.repository.IProfileEducationRepository
import com.lpfun.backend.profile.domain.education.handlers.responsePrepareHandler
import com.lpfun.backend.profile.domain.education.handlers.setupWorkMode
import com.lpfun.backend.profile.domain.education.stubs.stubDelete

class ProfileEducationDeleteUseCase(
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

            // Валидация

            // Обработка стабов
            execute(stubDelete)

            // Обращение к БД и обработка
            handler {
                condition { responseProfileStatus == ProfileContextStatus.RUNNING }
                exec {
                    responseProfile = repository.delete(requestProfile.profileId)
                }
                error {

                }
            }

            // Подготовка ответа
            execute(responsePrepareHandler)
        }
    }
}