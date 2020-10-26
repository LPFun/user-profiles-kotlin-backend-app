package com.lpfun.backend.profile.inmemorydb.education

import com.lpfun.backend.common.model.profile.education.AdditionalEducationModel
import com.lpfun.backend.common.model.profile.education.EducationModel
import com.lpfun.backend.common.model.profile.education.ProfileEducation
import com.lpfun.backend.common.profile.repository.IProfileEducationRepository
import com.lpfun.backend.profile.inmemorydb.education.entity.AdditionalEducationEntity
import com.lpfun.backend.profile.inmemorydb.education.entity.MainEducationEntity
import com.lpfun.backend.profile.inmemorydb.education.table.AdditionalEducationTable
import com.lpfun.backend.profile.inmemorydb.education.table.MainEducationTable
import com.lpfun.backend.profile.inmemorydb.education.table.ProfileEducationTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ProfileEducationRepositoryInMemory : IProfileEducationRepository {
    init {
        Database
            .connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "org.h2.Driver")
        transaction {
            SchemaUtils.create(
                ProfileEducationTable,
                MainEducationTable,
                AdditionalEducationTable
            )
        }
    }

    override suspend fun get(id: String): ProfileEducation {
        val profile = ProfileEducation.NONE.apply {
            profileId = id
        }
        transaction {
            addLogger(StdOutSqlLogger)
            MainEducationEntity.find { MainEducationTable.profileId eq id }.forEach {
                profile.mainEducation.add(
                    EducationModel(
                        id = it.id.toString(),
                        university = it.university,
                        department = it.department,
                        specialty = it.speciality,
                        yearOfCompletion = it.yearOfCompletion
                    )
                )
            }

            AdditionalEducationEntity.find { AdditionalEducationTable.profileId eq id }.forEach {
                profile.additionalEducation.add(
                    AdditionalEducationModel(
                        id = it.id.toString(),
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
            profileId = UUID.randomUUID().toString()
        }
        transaction {
            addLogger(StdOutSqlLogger)
            ProfileEducationTable.insert { it[profileId] = profile.profileId }
            profile.mainEducation.forEach { ed ->
                MainEducationEntity.new(UUID.randomUUID().toString()) {
                    profileId = profile.profileId
                    university = ed.university
                    department = ed.department
                    speciality = ed.specialty
                    yearOfCompletion = ed.yearOfCompletion
                }
            }
            profile.additionalEducation.forEach { ed ->
                AdditionalEducationEntity.new(UUID.randomUUID().toString()) {
                    profileId = profile.profileId
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
            MainEducationEntity.find { MainEducationTable.profileId eq id }.forEach {
                it.delete()
            }
            AdditionalEducationEntity.find { MainEducationTable.profileId eq id }.forEach {
                it.delete()
            }
            ProfileEducationTable.deleteWhere { ProfileEducationTable.profileId eq id }
        }
        return profile
    }
}

