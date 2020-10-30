package com.lpfun.backend.profile.inmemorydb.skills

import com.lpfun.backend.common.model.profile.skills.ProfileSkillsAndTech
import com.lpfun.backend.common.profile.repository.IProfileSkillsAndTechRepository
import com.lpfun.backend.profile.inmemorydb.extensions.toDataBasesModel
import com.lpfun.backend.profile.inmemorydb.extensions.toModel
import com.lpfun.backend.profile.inmemorydb.extensions.toOperatingSystemsModel
import com.lpfun.backend.profile.inmemorydb.extensions.toSpecializationModel
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ProfileSkillsAndTechRepoInMemory : IProfileSkillsAndTechRepository {
    init {
        Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "org.h2.Driver")
        transaction {
            SchemaUtils.create(
                ProfileSkillsAndTechTable,
                ProfileSpecializationTable,
                ProfileOperatingSystemTable,
                ProfileDataBaseTable
            )
        }
    }

    override suspend fun get(id: String): ProfileSkillsAndTech {
        val profile = ProfileSkillsAndTech.NONE
        transaction {
            ProfileSpecializationTable.select { ProfileSpecializationTable.profileId eq id }.firstOrNull()?.let {
                profile.profileId = it[ProfileSpecializationTable.profileId].toString()
            }
            profile.dataBases = ProfileDataBaseEntity.find {
                ProfileDataBaseTable.profileId eq id
            }.toDataBasesModel()
            profile.operatingSystems = ProfileOperatingSystemEntity.find {
                ProfileOperatingSystemTable.profileId eq id
            }.toOperatingSystemsModel()
            profile.specialization = ProfileSpecializationEntity.find {
                ProfileSpecializationTable.profileId eq id
            }.firstOrNull().toSpecializationModel()
        }
        return profile
    }

    override suspend fun create(profile: ProfileSkillsAndTech): ProfileSkillsAndTech {
        val requestId = UUID.randomUUID().toString()
        val createdProfileId = transaction {
            ProfileSkillsAndTechTable.insertAndGetId {
                it[id] = requestId
            }
        }
        profile.operatingSystems.forEach {
            transaction {
                ProfileOperatingSystemEntity.new {
                    profileId = createdProfileId
                    operatingSystem = it.operatingSystem
                }
            }
        }
        profile.dataBases.forEach {
            transaction {
                ProfileDataBaseEntity.new {
                    profileId = createdProfileId
                    dataBase = it.dataBase
                }
            }
        }
        transaction {
            ProfileSpecializationEntity.new {
                profileId = createdProfileId
                category = profile.specialization.category
                subCategory = profile.specialization.subCategory
            }
        }
        return get(requestId)
    }

    override suspend fun update(profile: ProfileSkillsAndTech): ProfileSkillsAndTech {
        val updateProfileId = profile.profileId
        transaction {
            addLogger(StdOutSqlLogger)
            profile.operatingSystems.forEach { os ->
                ProfileOperatingSystemEntity.find {
                    (ProfileOperatingSystemTable.profileId eq updateProfileId) and (ProfileOperatingSystemTable.id eq UUID.fromString(
                        os.id
                    ))
                }.forEach {
                    it.operatingSystem = os.operatingSystem
                }
            }
            ProfileSpecializationEntity.find {
                ProfileSpecializationTable.profileId eq updateProfileId
            }.firstOrNull()?.let {
                it.category = profile.specialization.category
                it.subCategory = profile.specialization.subCategory
            }
            profile.dataBases.forEach { db ->
                ProfileDataBaseEntity.find {
                    (ProfileDataBaseTable.profileId eq updateProfileId) and (ProfileDataBaseTable.id eq UUID.fromString(
                        db.id
                    ))
                }.forEach {
                    it.dataBase = db.dataBase
                }
            }
        }
        return get(updateProfileId)
    }

    override suspend fun delete(id: String): ProfileSkillsAndTech {
        val profile = ProfileSkillsAndTech.NONE
        transaction {
            ProfileSkillsAndTechTable.select { ProfileSkillsAndTechTable.id eq id }.forEach {
                profile.profileId = this.id
            }

            ProfileDataBaseEntity.find {
                ProfileDataBaseTable.profileId eq id
            }.forEach {
                profile.dataBases.add(it.toModel())
                it.delete()
            }
            ProfileSpecializationEntity.find {
                ProfileSpecializationTable.profileId eq id
            }.forEach {
                profile.specialization = it.toSpecializationModel()
                it.delete()
            }
            ProfileOperatingSystemEntity.find {
                ProfileOperatingSystemTable.profileId eq id
            }.forEach {
                profile.operatingSystems.add(it.toModel())
                it.delete()
            }
        }
        return profile
    }
}




