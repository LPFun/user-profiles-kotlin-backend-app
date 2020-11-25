package com.lpfun.backend.profile.domain.skills

import com.lpfun.backend.common.cor.IExec
import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsContext
import com.lpfun.backend.common.profile.repository.IProfileSkillsAndTechRepository
import com.lpfun.backend.profile.domain.skills.handlers.responsePrepareHandler
import com.lpfun.backend.profile.domain.skills.handlers.setupWorkMode
import com.lpfun.backend.profile.domain.skills.stubs.stubUpdate

class ProfileSkillsUpdateUseCase(
    private val repo: IProfileSkillsAndTechRepository,
    private val testRepo: IProfileSkillsAndTechRepository,
) : IExec<ProfileSkillsContext> {
    override suspend fun execute(ctx: ProfileSkillsContext) = chain.execute(ctx.apply {
        repository = repo
        testRepository = testRepo
    })

    companion object {
        val chain = cor<ProfileSkillsContext> {
            // Запуск пайплайна
            execute { responseProfileStatus = ProfileContextStatus.RUNNING }

            // Установка режима работы
            execute(setupWorkMode)

            // Валидация

            // Обработка стабов
            execute(stubUpdate)

            // Обращение к БД
            handler {
                condition { responseProfileStatus == ProfileContextStatus.RUNNING }
                exec {
                    responseProfile = repository.update(requestProfile)
                }
                error { }
            }

            // Обработка ответа
            execute(responsePrepareHandler)
        }
    }
}