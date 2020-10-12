package com.lpfun.backend.profile.domain.skills

import com.lpfun.backend.common.model.dsl.skills.profileSkills
import com.lpfun.backend.common.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubGet
import com.lpfun.backend.common.model.profile.skills.ProfileSkillsContext
import com.lpfun.backend.profile.domain.cor.IExec
import com.lpfun.backend.profile.domain.cor.cor

class ProfileSkillsGetUseCase : IExec<ProfileSkillsContext> {
    override suspend fun execute(ctx: ProfileSkillsContext) = chain.execute(ctx.apply {

    })


    companion object {
        val chain = cor<ProfileSkillsContext> {
            // Запуск пайплайна
            execute { responseProfileStatus = ProfileContextStatus.RUNNING }

            // Обработка стабов
            processor {
                condition { stubCaseGet != ProfileStubGet.NONE }
                handler {
                    condition { stubCaseGet == ProfileStubGet.RUNNING }
                    exec {
                        responseProfile = profileSkills {
                            id = requestProfileId
                            specialization {
                                category = "Development"
                                subCategory = "Mobile developer"
                            }
                            operatingSystems {
                                +"Android"
                            }
                            dataBases {
                                +"MySql"
                            }
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