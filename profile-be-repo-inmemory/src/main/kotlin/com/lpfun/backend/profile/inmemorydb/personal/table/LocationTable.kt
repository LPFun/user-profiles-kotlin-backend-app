package com.lpfun.backend.profile.inmemorydb.personal.table

import org.jetbrains.exposed.dao.id.IdTable

object LocationTable : IdTable<String>("location_table") {
    var profileId = reference("profile_id", ProfilePersonalTable.profileId)
    var country = text("country")
    var city = text("city")
    override val id = profileId.entityId()
}