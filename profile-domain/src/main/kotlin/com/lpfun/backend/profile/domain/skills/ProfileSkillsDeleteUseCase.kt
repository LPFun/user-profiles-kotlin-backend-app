package com.lpfun.backend.profile.domain.skills

import com.lpfun.backend.common.cor.IExec
import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubDelete
import com.lpfun.backend.common.model.profile.skills.ProfileSkillsAndTech
import com.lpfun.backend.common.model.profile.skills.ProfileSkillsContext

class ProfileSkillsDeleteUseCase : IExec<ProfileSkillsContext> {
    override suspend fun execute(ctx: ProfileSkillsContext) = chain.execute(ctx.apply {

    })

    companion object {
        val chain = cor<ProfileSkillsContext> {
            // Запуск пайплайна
            execute { responseProfileStatus = ProfileContextStatus.RUNNING }

            // Обработка стабов
            processor {
                condition { stubCaseDelete != ProfileStubDelete.NONE }
                handler {
                    condition { stubCaseDelete == ProfileStubDelete.SUCCESS }
                    exec {
                        responseProfile = ProfileSkillsAndTech()
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