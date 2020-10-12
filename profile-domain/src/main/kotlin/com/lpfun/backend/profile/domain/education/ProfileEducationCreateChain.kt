package com.lpfun.backend.profile.domain.education

import com.lpfun.backend.common.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubCreate
import com.lpfun.backend.common.model.profile.education.ProfileEducationContext
import com.lpfun.backend.profile.domain.cor.IExec
import com.lpfun.backend.profile.domain.cor.cor

class ProfileEducationCreateChain : IExec<ProfileEducationContext> {
    override suspend fun execute(ctx: ProfileEducationContext) = chain.execute(ctx.apply {

    })

    companion object {
        val chain = cor<ProfileEducationContext> {
            // Инициализация пайплайна
            execute {
                responseProfileStatus = ProfileContextStatus.RUNNING
            }

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

            // Обращение к БД и обработка

            // Подготовка ответа
            execute {
                responseProfileStatus = ProfileContextStatus.SUCCESS
            }
        }
    }
}