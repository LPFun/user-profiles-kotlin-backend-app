package com.lpfun.backend.profile.db.inmemory.skills

import com.lpfun.backend.common.profile.model.dsl.skills.profileSkills
import com.lpfun.backend.common.profile.repository.IProfileSkillsAndTechRepository
import com.lpfun.backend.profile.db.base.skills.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ProfileSkillsAndTechRepoInMemory(db: Database? = null) :
    IProfileSkillsAndTechRepository by ProfileSkillsAndTechRepoBase() {
    init {
        db?.let {
            transaction(it) {
                addLogger(StdOutSqlLogger)
                SchemaUtils.create(
                    ProfileSkillsAndTechTable,
                    ProfileSpecializationTable,
                    ProfileOperatingSystemTable,
                    ProfileDataBaseTable
                )
                setInitialData()
            }
        }
    }

    private fun setInitialData() {
        val profile = profileSkills {
            id = "a9d77992-3e96-4010-bc35-ab7b8b88da40"
            specialization {
                category = "Category"
                subCategory = "Sub Category"
            }
            operatingSystems {
                add("Operating System")
            }
            dataBases {
                add("Data Base")
            }
        }
        //Create
        val requestId = if (profile.profileId.isEmpty()) UUID.randomUUID().toString() else profile.profileId
        val result = ProfileSkillsAndTechEntity.findById(UUID.fromString(requestId))
        if (result != null) return
        val createdProfileId = ProfileSkillsAndTechTable.insertAndGetId {
            it[id] = UUID.fromString(requestId)
        }
        profile.operatingSystems.forEach {
            ProfileOperatingSystemEntity.new {
                profileId = createdProfileId
                operatingSystem = it.operatingSystem
            }
        }
        profile.dataBases.forEach {
            ProfileDataBaseEntity.new {
                profileId = createdProfileId
                dataBase = it.dataBase
            }
        }

        ProfileSpecializationEntity.new {
            profileId = createdProfileId
            category = profile.specialization.category
            subCategory = profile.specialization.subCategory
        }
    }
}




