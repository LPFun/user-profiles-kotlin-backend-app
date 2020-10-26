package com.lpfun.backend.profile.inmemorydb.education.table

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object MainEducationTable : IdTable<String>("main_education_table") {
    val mainEducationId = varchar("mainEducationId", 50)
    val profileId = varchar("profileId", 50).references(ProfileEducationTable.profileId)
    val university = text("university")
    val department = text("department")
    val speciality = text("speciality")
    val yearOfCompletion = text("year")
    override val id: Column<EntityID<String>> = mainEducationId.entityId()
}