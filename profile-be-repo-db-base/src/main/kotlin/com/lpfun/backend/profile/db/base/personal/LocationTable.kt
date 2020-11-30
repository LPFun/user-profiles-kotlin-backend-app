package com.lpfun.backend.profile.db.base.personal

import org.jetbrains.exposed.dao.id.IdTable

object LocationTable : IdTable<String>("profile_location_table") {
    var country = text("country")
    var city = text("city")
    override val id = varchar("id", 50).entityId()
    override val primaryKey = PrimaryKey(id)
}