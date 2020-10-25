package com.lpfun.backend.profile.domain.skills

import com.lpfun.backend.common.cor.IExec
import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubUpdate
import com.lpfun.backend.common.model.profile.skills.ProfileSkillsContext

class ProfileSkillsUpdateUseCase : IExec<ProfileSkillsContext> {
    override suspend fun execute(ctx: ProfileSkillsContext) = chain.execute(ctx.apply {

    })

    companion object {
        val chain = cor<ProfileSkillsContext> {
            // Запуск пайплайна
            execute { responseProfileStatus = ProfileContextStatus.RUNNING }

            // Обработка стабов
            processor {
                condition { stubCaseUpdate != ProfileStubUpdate.NONE }
                handler {
                    condition { stubCaseUpdate == ProfileStubUpdate.SUCCESS }
                    exec {
                        responseProfile = requestProfile
                        responseProfileStatus = ProfileContextStatus.FINISHING
                    }
                }
            }

            // Валидация

            // Обращение к БД

            // Обработка ответа
            execute { responseProfileStatus = ProfileContextStatus.SUCCESS }
        }
    }
}