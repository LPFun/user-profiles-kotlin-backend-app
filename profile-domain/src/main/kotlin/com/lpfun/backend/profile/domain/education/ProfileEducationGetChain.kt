package com.lpfun.backend.profile.domain.education

import com.lpfun.backend.common.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.model.profile.education.ProfileEducationContext
import com.lpfun.backend.profile.domain.cor.IExec
import com.lpfun.backend.profile.domain.cor.cor

class ProfileEducationGetChain : IExec<ProfileEducationContext> {
    override suspend fun exec(ctx: ProfileEducationContext) = chain.exec(ctx.apply {

    })

    companion object {
        val chain = cor<ProfileEducationContext> {
            // Инициализация пайплайна
            execute {
                responseProfileStatus = ProfileContextStatus.RUNNING
            }

            // Обработка стабов
            processor {
                handler {

                    exec {


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