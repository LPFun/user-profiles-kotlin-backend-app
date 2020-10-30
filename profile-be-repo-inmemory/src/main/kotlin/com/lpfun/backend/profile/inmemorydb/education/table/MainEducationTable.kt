package com.lpfun.backend.profile.inmemorydb.education.table

import org.jetbrains.exposed.dao.id.IdTable

object MainEducationTable : IdTable<String>("main_education_table") {
    val profileId = reference("profile_id", ProfileEducationTable.id)
    val university = text("university")
    val department = text("department")
    val speciality = text("speciality")
    val yearOfCompletion = text("year")
    override val id = varchar("mainEducationId", 50).entityId()
}