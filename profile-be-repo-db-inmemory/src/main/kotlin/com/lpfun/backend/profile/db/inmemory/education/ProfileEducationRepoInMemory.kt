package com.lpfun.backend.profile.db.inmemory.education

import com.lpfun.backend.common.profile.model.dsl.education.profileEducation
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducation
import com.lpfun.backend.profile.db.base.education.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ProfileEducationRepoInMemory(private val db: Database? = null) :
    ProfileEducationRepositoryBase() {

    private fun init() {
        db?.let {
            transaction(it) {
                addLogger(StdOutSqlLogger)
                SchemaUtils.createMissingTablesAndColumns(
                    ProfileEducationTable,
                    MainEducationTable,
                    AdditionalEducationTable
                )

                setInitialData()
            }
        }
    }

    override suspend fun get(id: String): ProfileEducation {
        init()
        return super.get(id)
    }

    override suspend fun create(profile: ProfileEducation): ProfileEducation {
        init()
        return super.create(profile)
    }

    override suspend fun update(profile: ProfileEducation): ProfileEducation {
        init()
        return super.update(profile)
    }

    override suspend fun delete(id: String): ProfileEducation {
        init()
        return super.delete(id)
    }

    private fun setInitialData() {
        val profileEducationTest = profileEducation {
            id = "test-id"
            +mainEducation {
                id = "test-id"
                university = "University"
                department = "Department"
                speciality = "Speciality"
                yearOfCompletion = "Year Of Completion"
            }
            +additionalEducation {
                id = "test-id"
                nameOfInstitution = "Name Of Institution"
                courseName = "Course Name"
                yearOfCompletion = "Year Of Completion"
            }
        }

        //Create
        val ids = ProfileEducationEntity.findById(profileEducationTest.profileId)
        if (ids != null) return
        val createdProfileId = ProfileEducationTable.insertAndGetId {
            it[id] = profileEducationTest.profileId
        }
        profileEducationTest.mainEducation.forEach { ed ->
            MainEducationEntity.new(if (ed.id.isEmpty()) UUID.randomUUID().toString() else ed.id) {
                profileId = createdProfileId
                university = ed.university
                department = ed.department
                speciality = ed.specialty
                yearOfCompletion = ed.yearOfCompletion
            }
        }
        profileEducationTest.additionalEducation.forEach { ed ->
            AdditionalEducationEntity.new(if (ed.id.isEmpty()) UUID.randomUUID().toString() else ed.id) {
                profileId = createdProfileId
                nameOfInstitution = ed.nameOfInstitution
                courseName = ed.courseName
                yearOfCompletion = ed.yearOfCompletion
            }
        }
    }
}

