package com.lpfun.backend.profile.domain.education

import com.lpfun.backend.common.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.profile.domain.cor.IExec
import com.lpfun.backend.profile.domain.cor.cor

class ProfilePersonalDeleteChain : IExec<ProfilePersonalContext> {
    override suspend fun exec(ctx: ProfilePersonalContext) = chain.exec(ctx.apply {

    })

    companion object {
        val chain = cor<ProfilePersonalContext> {
            // Инициализация пайплайна

            // Обработка стабов

            // Валидация

            // Работа с БД

            // Обработка ответа

        }
    }
}
