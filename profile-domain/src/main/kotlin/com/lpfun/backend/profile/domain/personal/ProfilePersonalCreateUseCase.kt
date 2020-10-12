package com.lpfun.backend.profile.domain.personal

import com.lpfun.backend.common.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubCreate
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.profile.domain.cor.IExec
import com.lpfun.backend.profile.domain.cor.cor

class ProfilePersonalCreateUseCase : IExec<ProfilePersonalContext> {
    override suspend fun execute(ctx: ProfilePersonalContext) = chain.execute(ctx.apply {

    })

    companion object {
        val chain = cor<ProfilePersonalContext> {
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

            // Работа с БД

            // Обработка ответа
            execute {
                responseProfileStatus = ProfileContextStatus.SUCCESS
            }
        }
    }
}
