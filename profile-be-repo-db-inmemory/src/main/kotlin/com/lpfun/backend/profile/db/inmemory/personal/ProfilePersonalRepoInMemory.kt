package com.lpfun.backend.profile.db.inmemory.personal

import com.lpfun.backend.common.profile.model.dsl.personal.profilePersonalData
import com.lpfun.backend.common.profile.repository.IProfilePersonalDataRepository
import com.lpfun.backend.profile.db.base.personal.LocationTable
import com.lpfun.backend.profile.db.base.personal.ProfilePersonalEntity
import com.lpfun.backend.profile.db.base.personal.ProfilePersonalRepoBase
import com.lpfun.backend.profile.db.base.personal.ProfilePersonalTable
import com.lpfun.backend.profile.db.extensions.applyProfile
import kotlinx.datetime.LocalDate
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class ProfilePersonalRepoInMemory(db: Database? = null) : IProfilePersonalDataRepository by ProfilePersonalRepoBase() {
    init {
        db?.let {
            transaction(it) {
                SchemaUtils.create(
                    ProfilePersonalTable,
                    LocationTable
                )
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
    }
}

