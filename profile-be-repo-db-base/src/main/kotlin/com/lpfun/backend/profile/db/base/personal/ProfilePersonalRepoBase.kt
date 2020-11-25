package com.lpfun.backend.profile.db.base.personal

import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalData
import com.lpfun.backend.common.profile.repository.IProfilePersonalDataRepository
import com.lpfun.backend.profile.db.extensions.toProfilePersonalData
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ProfilePersonalRepoBase : IProfilePersonalDataRepository {

    override suspend fun get(id: String): ProfilePersonalData {
        return transaction {
            val profile = ProfilePersonalEntity.find { ProfilePersonalTable.id eq id }.first()
            profile.toProfilePersonalData()
        }
    }

    override suspend fun create(profile: ProfilePersonalData): ProfilePersonalData {
        return transaction {
            addLogger(StdOutSqlLogger)
            val generatedId = if (profile.profileId.isEmpty()) UUID.randomUUID().toString() else profile.profileId
            ProfilePersonalEntity.new(generatedId) {
                this applyProfile profile
            }.toProfilePersonalData()
        }
    }

    infix fun ProfilePersonalEntity.applyProfile(profile: ProfilePersonalData): ProfilePersonalEntity {
        firstName = profile.firstName
        middleName = profile.middleName
        lastName = profile.lastName
        displayName = profile.displayName
        phone = profile.phone
        email = profile.email
        bday = profile.bday.toString()
        country = profile.locationModel.country
        city = profile.locationModel.city
        return this
    }

    override suspend fun update(profile: ProfilePersonalData): ProfilePersonalData {
        return transaction {
            addLogger(StdOutSqlLogger)
            val currentProfile =
                ProfilePersonalEntity.find { ProfilePersonalTable.id eq profile.profileId }.first()
            currentProfile.apply {
                this applyProfile profile
            }.toProfilePersonalData()
        }
    }

    override suspend fun delete(id: String): ProfilePersonalData {
        return transaction {
            addLogger(StdOutSqlLogger)
            val profile = ProfilePersonalEntity.findById(id)
            profile?.delete()
            profile?.toProfilePersonalData() ?: ProfilePersonalData.NONE
        }
    }
}

