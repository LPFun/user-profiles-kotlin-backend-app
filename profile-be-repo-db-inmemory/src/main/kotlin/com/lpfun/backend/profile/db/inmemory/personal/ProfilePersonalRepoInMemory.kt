package com.lpfun.backend.profile.db.inmemory.personal

import com.lpfun.backend.common.profile.model.dsl.personal.profilePersonalData
import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalData
import com.lpfun.backend.profile.db.base.personal.LocationTable
import com.lpfun.backend.profile.db.base.personal.ProfilePersonalEntity
import com.lpfun.backend.profile.db.base.personal.ProfilePersonalRepoBase
import com.lpfun.backend.profile.db.base.personal.ProfilePersonalTable
import kotlinx.datetime.LocalDate
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class ProfilePersonalRepoInMemory(
    private val db: Database? = null
) : ProfilePersonalRepoBase() {
    private fun init() {
        db?.let {
            transaction(it) {
                SchemaUtils.createMissingTablesAndColumns(
                    ProfilePersonalTable,
                    LocationTable
                )
                setInitialData()
            }
        }
    }

    override suspend fun get(id: String): ProfilePersonalData {
        init()
        return super.get(id)
    }

    override suspend fun create(profile: ProfilePersonalData): ProfilePersonalData {
        init()
        return super.create(profile)
    }

    override suspend fun update(profile: ProfilePersonalData): ProfilePersonalData {
        init()
        return super.update(profile)
    }

    override suspend fun delete(id: String): ProfilePersonalData {
        init()
        return super.delete(id)
    }

    private fun setInitialData() {
        val testProfile = profilePersonalData {
            name {
                first = "First name"
                second = "Second name"
                last = "Last name"
                display = "Display name"
            }
            birth {
                date = LocalDate.parse("2020-01-01")
            }
            contacts {
                phone = "Phone"
                email = "Email"
            }
            location {
                country = "Country"
                city = "City"
            }
        }
        ProfilePersonalEntity.findById("test-id")?.delete()
        ProfilePersonalEntity.new("test-id") {
            this applyProfile testProfile
        }
    }
}

