package com.lpfun.backend.profile.domain.personal

import com.lpfun.backend.common.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubDelete
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalData
import com.lpfun.backend.profile.domain.cor.IExec
import com.lpfun.backend.profile.domain.cor.cor

class ProfilePersonalDeleteChain : IExec<ProfilePersonalContext> {
    override suspend fun exec(ctx: ProfilePersonalContext) = chain.exec(ctx.apply {

    })

    companion object {
        val chain = cor<ProfilePersonalContext> {
            // Инициализация пайплайна
            execute {
                responseProfileStatus = ProfileContextStatus.RUNNING
            }

            // Обработка стабов
            processor {
                condition { stubCaseDelete != ProfileStubDelete.NONE }
                handler {
                    condition { stubCaseDelete == ProfileStubDelete.RUNNING }
                    exec {
                        responseProfile = ProfilePersonalData()
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
