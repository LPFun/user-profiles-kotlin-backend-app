package com.lpfun.backend.profile.inmemorydb.personal.table

import org.jetbrains.exposed.dao.id.IdTable

object ProfilePersonalTable : IdTable<String>("profile_personal_data_table") {
    var profileId = varchar("profile_id", 50)
    var firstName = text("first_name")
    var middleName = text("middle_name")
    var lastName = text("last_name")
    var displayName = text("display_name")
    var phone = text("phone")
    var email = text("email")
    var bday = text("birth_day")
    val location = reference("location", LocationTable)
    override val id = profileId.entityId()
}