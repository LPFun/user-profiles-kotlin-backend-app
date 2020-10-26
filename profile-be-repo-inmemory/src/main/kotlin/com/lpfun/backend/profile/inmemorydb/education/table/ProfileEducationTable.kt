package com.lpfun.backend.profile.inmemorydb.education.table

import org.jetbrains.exposed.sql.Table

object ProfileEducationTable : Table("profile_education_table") {
    var profileId = varchar("profileId", 50)
    override val primaryKey = PrimaryKey(profileId)
}