package com.lpfun.backend.profile.db.base.personal

import org.jetbrains.exposed.dao.id.IdTable

object ProfilePersonalTable : IdTable<String>("profile_personal_table") {
    val firstName = text("first_name")
    val middleName = text("middle_name")
    val lastName = text("last_name")
    val displayName = text("display_name")
    val phone = text("phone")
    val email = text("email")
    val bday = text("birth_day")
    val country = text("country")
    val city = text("city")
    override val id = varchar("id", 50).entityId()
    override val primaryKey = PrimaryKey(id)
}