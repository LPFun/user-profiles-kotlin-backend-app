package com.lpfun.backend.profile.db.base.personal

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class LocationEntity(id: EntityID<String>) : Entity<String>(id) {
    companion object : EntityClass<String, LocationEntity>(LocationTable)
    var country by LocationTable.country
    var city by LocationTable.city
}