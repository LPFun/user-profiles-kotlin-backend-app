package com.lpfun.backend.profile.domain.education

import com.lpfun.backend.common.cor.IExec
import com.lpfun.backend.common.cor.cor
import com.lpfun.backend.common.model.dsl.education.profileEducation
import com.lpfun.backend.common.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubGet
import com.lpfun.backend.common.model.profile.education.ProfileEducationContext

class ProfileEducationGetUseCase : IExec<ProfileEducationContext> {
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
                condition { stubCaseGet != ProfileStubGet.NONE }
                handler {
                    condition { stubCaseGet == ProfileStubGet.SUCCESS }
                    exec {
                        responseProfile = profileEducation {
                            id = requestProfileId
                            mainEducation {
                                university = "Garvard"
                                department = "IT"
                                speciality = "Programming"
                                yearOfCompletion = "2020"
                            }
                            +additionalEducation {
                                nameOfInstitution = "OTUS"
                                courseName = "Kotlin"
                                yearOfCompletion = "2020"
                            }
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