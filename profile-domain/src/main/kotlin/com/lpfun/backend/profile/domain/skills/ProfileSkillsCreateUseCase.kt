package com.lpfun.backend.profile.domain.skills

import com.lpfun.backend.common.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubCreate
import com.lpfun.backend.common.model.profile.skills.ProfileSkillsContext
import com.lpfun.backend.profile.domain.cor.IExec
import com.lpfun.backend.profile.domain.cor.cor

class ProfileSkillsCreateUseCase : IExec<ProfileSkillsContext> {
    override suspend fun execute(ctx: ProfileSkillsContext) = chain.execute(ctx.apply {

    })

    companion object {
        val chain = cor<ProfileSkillsContext> {
            // Запуск пайплайна
            execute { responseProfileStatus = ProfileContextStatus.RUNNING }

            // Обработка стабов
            processor {
                condition { stubCaseCreate != ProfileStubCreate.NONE }
                handler {
                    condition { stubCaseCreate == ProfileStubCreate.RUNNING }
                    exec {
                        responseProfile = requestProfile.apply {
                            profileId = "test-id"
                        }
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