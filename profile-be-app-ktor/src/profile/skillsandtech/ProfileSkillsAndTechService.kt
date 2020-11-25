package com.lpfun.profile.skillsandtech

import com.lpfun.backend.common.profile.model.profile.skills.ProfileSkillsContext
import com.lpfun.backend.kmp.profile.setQuery
import com.lpfun.backend.profile.domain.skills.ProfileSkillsCrud
import com.lpfun.base.request
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechCreate
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechDelete
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechGet
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechUpdate

class ProfileSkillsAndTechService(
    private val crud: ProfileSkillsCrud
) {
    suspend fun get(query: KmpProfileSkillsAndTechGet) = ProfileSkillsContext().request {
        crud.get(setQuery(query))
    }

    suspend fun create(query: KmpProfileSkillsAndTechCreate) = ProfileSkillsContext().request {
        crud.create(setQuery(query))
    }

    suspend fun update(query: KmpProfileSkillsAndTechUpdate) = ProfileSkillsContext().request {
        crud.update(setQuery(query))
    }

    suspend fun delete(query: KmpProfileSkillsAndTechDelete) = ProfileSkillsContext().request {
        crud.update(setQuery(query))
    }
}