package com.lpfun.backend.profile.db.base.skills

import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsAndTech
import com.lpfun.backend.common.profile.repository.IProfileSkillsAndTechRepository
import com.lpfun.backend.profile.db.extensions.toDataBasesModel
import com.lpfun.backend.profile.db.extensions.toModel
import com.lpfun.backend.profile.db.extensions.toOperatingSystemsModel
import com.lpfun.backend.profile.db.extensions.toSpecializationModel
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ProfileSkillsAndTechRepoBase : IProfileSkillsAndTechRepository {

    override suspend fun get(id: String): ProfileSkillsAndTech {
        return transaction {
            val profile = ProfileSkillsAndTech()
            ProfileSpecializationTable.select { ProfileSpecializationTable.profileId eq UUID.fromString(id) }
                .firstOrNull()?.let {
                profile.profileId = it[ProfileSpecializationTable.profileId].toString()
            }
            profile.dataBases = ProfileDataBaseEntity.find {
                ProfileDataBaseTable.profileId eq UUID.fromString(id)
            }.toDataBasesModel()
            profile.operatingSystems = ProfileOperatingSystemEntity.find {
                ProfileOperatingSystemTable.profileId eq UUID.fromString(id)
            }.toOperatingSystemsModel()
            profile.specialization = ProfileSpecializationEntity.find {
                ProfileSpecializationTable.profileId eq UUID.fromString(id)
            }.firstOrNull().toSpecializationModel()
            profile
        }
    }

    override suspend fun create(profile: ProfileSkillsAndTech): ProfileSkillsAndTech {
        val requestId = if (profile.profileId.isEmpty()) UUID.randomUUID().toString() else profile.profileId
        val createdProfileId = transaction {
            ProfileSkillsAndTechTable.insertAndGetId {
                it[id] = UUID.fromString(requestId)
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
        transaction {
            val updateProfileId = profile.profileId
            addLogger(StdOutSqlLogger)
            profile.operatingSystems.forEach { os ->
                ProfileOperatingSystemEntity.find {
                    (ProfileOperatingSystemTable.profileId eq UUID.fromString(updateProfileId))
                }.forEach {
                    it.operatingSystem = os.operatingSystem
                }
            }
            ProfileSpecializationEntity.find {
                ProfileSpecializationTable.profileId eq UUID.fromString(updateProfileId)
            }.firstOrNull()?.let {
                it.category = profile.specialization.category
                it.subCategory = profile.specialization.subCategory
            }
            profile.dataBases.forEach { db ->
                ProfileDataBaseEntity.find {
                    (ProfileDataBaseTable.profileId eq UUID.fromString(updateProfileId))
                }.forEach {
                    it.dataBase = db.dataBase
                }
            }
        }
        return get(profile.profileId)
    }

    override suspend fun delete(id: String): ProfileSkillsAndTech {
        return transaction {
            val profile = ProfileSkillsAndTech()
            ProfileSkillsAndTechTable.select { ProfileSkillsAndTechTable.id eq UUID.fromString(id) }.forEach {
                profile.profileId = this.id
            }

            ProfileDataBaseEntity.find {
                ProfileDataBaseTable.profileId eq UUID.fromString(id)
            }.forEach {
                profile.dataBases.add(it.toModel())
                it.delete()
            }
            ProfileSpecializationEntity.find {
                ProfileSpecializationTable.profileId eq UUID.fromString(id)
            }.forEach {
                profile.specialization = it.toSpecializationModel()
                it.delete()
            }
            ProfileOperatingSystemEntity.find {
                ProfileOperatingSystemTable.profileId eq UUID.fromString(id)
            }.forEach {
                profile.operatingSystems.add(it.toModel())
                it.delete()
            }
            profile
        }
    }
}




