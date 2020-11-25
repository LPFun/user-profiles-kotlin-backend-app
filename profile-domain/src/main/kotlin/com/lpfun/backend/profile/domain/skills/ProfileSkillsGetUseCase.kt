package com.lpfun.backend.profile.domain.skills

import com.lpfun.backend.common.WorkMode
import com.lpfun.backend.common.cor.IExec
import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.profile.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsContext
import com.lpfun.backend.common.profile.repository.IProfileSkillsAndTechRepository
import com.lpfun.backend.profile.domain.skills.stubs.stubGet

class ProfileSkillsGetUseCase(
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

            // Валидация

            // Обработка стабов
            execute(stubGet)

            // Обращение к тестовой БД
            handler {
                condition {
                    responseProfileStatus == ProfileContextStatus.RUNNING && workMode == WorkMode.TEST
                }
                exec {
                    responseProfile = testRepository.get(requestProfile.profileId)
                }
                error { }
            }

            // Обращение к БД
            handler {
                condition { responseProfileStatus == ProfileContextStatus.RUNNING }
                exec {
                    responseProfile = repository.get(requestProfile.profileId)
                }
                error { }
            }

            // Обработка ответа
            execute { responseProfileStatus = ProfileContextStatus.SUCCESS }
        }
    }
}
