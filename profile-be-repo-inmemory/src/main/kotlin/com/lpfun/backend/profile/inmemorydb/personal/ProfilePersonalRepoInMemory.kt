package com.lpfun.backend.profile.inmemorydb.personal

import com.lpfun.backend.common.model.profile.personal.ProfilePersonalData
import com.lpfun.backend.common.profile.repository.IProfilePersonalDataRepository
import com.lpfun.backend.profile.inmemorydb.extensions.toProfilePersonalData
import com.lpfun.backend.profile.inmemorydb.personal.entity.LocationEntity
import com.lpfun.backend.profile.inmemorydb.personal.entity.ProfilePersonalEntity
import com.lpfun.backend.profile.inmemorydb.personal.table.LocationTable
import com.lpfun.backend.profile.inmemorydb.personal.table.ProfilePersonalTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ProfilePersonalRepoInMemory : IProfilePersonalDataRepository {
    init {
        Database
            .connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "org.h2.Driver")
        transaction {
            SchemaUtils.create(
                ProfilePersonalTable,
                LocationTable
            )
        }
    }

    override suspend fun get(id: String): ProfilePersonalData {
        return transaction {
            val profile = ProfilePersonalEntity.find { ProfilePersonalTable.id eq id }.first()
            profile.toProfilePersonalData()
        }
    }

    override suspend fun create(profile: ProfilePersonalData): ProfilePersonalData {
        var createdProfile = ProfilePersonalData.NONE
        transaction {
            addLogger(StdOutSqlLogger)
            val generatedId = UUID.randomUUID().toString()
            createdProfile = ProfilePersonalEntity.new(generatedId) {
                firstName = profile.firstName
                middleName = profile.middleName
                lastName = profile.lastName
                displayName = profile.displayName
                phone = profile.phone
                email = profile.email
                bday = profile.bday.toString()
                location = LocationEntity.new(UUID.randomUUID().toString()) {
                    country = profile.locationModel.country
                    city = profile.locationModel.city
                }
            }.toProfilePersonalData()
        }
        return createdProfile
    }

    override suspend fun update(profile: ProfilePersonalData): ProfilePersonalData {
        return transaction {
            addLogger(StdOutSqlLogger)
            val currentProfile =
                ProfilePersonalEntity.find { ProfilePersonalTable.profileId eq profile.profileId }.first()
            currentProfile.apply {
                firstName = profile.firstName
                middleName = profile.middleName
                lastName = profile.lastName
                displayName = profile.displayName
                phone = profile.phone
                email = profile.email
                bday = profile.bday.toString()
                location.city = profile.locationModel.city
                location.country = profile.locationModel.country
            }.toProfilePersonalData()
        }
    }

    override suspend fun delete(id: String): ProfilePersonalData {
        return transaction {
            addLogger(StdOutSqlLogger)
            val profile = ProfilePersonalEntity.findById(id)!!
            profile.delete()
            profile.toProfilePersonalData()
        }
    }
}

