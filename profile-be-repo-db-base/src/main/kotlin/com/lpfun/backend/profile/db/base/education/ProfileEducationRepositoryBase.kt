package com.lpfun.backend.profile.db.base.education

import com.lpfun.backend.common.profile.model.profile.education.AdditionalEducationModel
import com.lpfun.backend.common.profile.model.profile.education.EducationModel
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducation
import com.lpfun.backend.common.profile.repository.IProfileEducationRepository
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ProfileEducationRepositoryBase : IProfileEducationRepository {
    override suspend fun get(id: String): ProfileEducation {
        val profile = ProfileEducation(profileId = id)
        transaction {
            addLogger(StdOutSqlLogger)
            MainEducationEntity.find { MainEducationTable.profileId eq profile.profileId }.forEach {
                profile.mainEducation.add(
                    EducationModel(
                        id = profile.profileId,
                        university = it.university,
                        department = it.department,
                        specialty = it.speciality,
                        yearOfCompletion = it.yearOfCompletion
                    )
                )
            }

            AdditionalEducationEntity.find { AdditionalEducationTable.profileId eq profile.profileId }.forEach {
                profile.additionalEducation.add(
                    AdditionalEducationModel(
                        id = profile.profileId,
                        nameOfInstitution = it.nameOfInstitution,
                        courseName = it.courseName,
                        yearOfCompletion = it.yearOfCompletion
                    )
                )
            }
        }
        return profile
    }

    override suspend fun create(profile: ProfileEducation): ProfileEducation {
        profile.apply {
            profileId = if (profile.profileId.isEmpty()) UUID.randomUUID().toString() else profile.profileId
        }
        transaction {
            addLogger(StdOutSqlLogger)
            val createdProfileId = ProfileEducationTable.insertAndGetId {
                it[id] = profile.profileId
            }
            profile.mainEducation.forEach { ed ->
                MainEducationEntity.new(if (ed.id.isEmpty()) UUID.randomUUID().toString() else ed.id) {
                    profileId = createdProfileId
                    university = ed.university
                    department = ed.department
                    speciality = ed.specialty
                    yearOfCompletion = ed.yearOfCompletion
                }
            }
            profile.additionalEducation.forEach { ed ->
                AdditionalEducationEntity.new(if (ed.id.isEmpty()) UUID.randomUUID().toString() else ed.id) {
                    profileId = createdProfileId
                    nameOfInstitution = ed.nameOfInstitution
                    courseName = ed.courseName
                    yearOfCompletion = ed.yearOfCompletion
                }
            }
        }
        return get(profile.profileId)
    }

    override suspend fun update(profile: ProfileEducation): ProfileEducation {
        transaction {
            addLogger(StdOutSqlLogger)
            profile.mainEducation.forEach { ed ->
                MainEducationEntity.find {
                    (MainEducationTable.profileId eq profile.profileId) and (MainEducationTable.id eq ed.id)
                }.forEach {
                    it.university = ed.university
                    it.department = ed.department
                    it.speciality = ed.specialty
                    it.yearOfCompletion = ed.yearOfCompletion
                }
            }
            profile.additionalEducation.forEach { ed ->
                AdditionalEducationEntity.find {
                    (AdditionalEducationTable.profileId eq profile.profileId) and (AdditionalEducationTable.id eq ed.id)
                }.forEach {
                    it.nameOfInstitution = ed.nameOfInstitution
                    it.courseName = ed.courseName
                    it.yearOfCompletion = ed.yearOfCompletion
                }
            }
        }
        return get(profile.profileId)
    }

    override suspend fun delete(id: String): ProfileEducation {
        val profile = get(id)
        transaction {
            addLogger(StdOutSqlLogger)
            AdditionalEducationEntity.find { AdditionalEducationTable.profileId eq id }.forEach {
                it.delete()
            }
            MainEducationEntity.find { MainEducationTable.profileId eq id }.forEach {
                it.delete()
            }
            ProfileEducationEntity.find { ProfileEducationTable.id eq id }.forEach {
                it.delete()
            }
        }
        return profile
    }
}

