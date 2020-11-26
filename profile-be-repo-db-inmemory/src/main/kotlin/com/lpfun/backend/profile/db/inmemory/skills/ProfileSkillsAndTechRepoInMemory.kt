package com.lpfun.backend.profile.db.inmemory.skills

import com.lpfun.backend.common.profile.model.dsl.skills.profileSkills
import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsAndTech
import com.lpfun.backend.profile.db.base.skills.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ProfileSkillsAndTechRepoInMemory(
    private val db: Database? = null
) : ProfileSkillsAndTechRepoBase() {
    private fun init() {
        db?.let {
            transaction(it) {
                addLogger(StdOutSqlLogger)
                SchemaUtils.createMissingTablesAndColumns(
                    ProfileSkillsAndTechTable,
                    ProfileSpecializationTable,
                    ProfileOperatingSystemTable,
                    ProfileDataBaseTable
                )
                setInitialData()
            }
        }
    }

    override suspend fun get(id: String): ProfileSkillsAndTech {
        init()
        return super.get(id)
    }

    override suspend fun create(profile: ProfileSkillsAndTech): ProfileSkillsAndTech {
        init()
        return super.create(profile)
    }

    override suspend fun update(profile: ProfileSkillsAndTech): ProfileSkillsAndTech {
        init()
        return super.update(profile)
    }

    override suspend fun delete(id: String): ProfileSkillsAndTech {
        init()
        return super.delete(id)
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




