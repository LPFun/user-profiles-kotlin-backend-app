package com.lpfun.profile.personaldata

import com.lpfun.backend.common.profile.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.kmp.profile.setQuery
import com.lpfun.backend.profile.domain.personal.ProfilePersonalCrud
import com.lpfun.base.request
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataCreate
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataDelete
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataGet
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataUpdate

class ProfilePersonalDataService(
    private val crud: ProfilePersonalCrud
) {
    suspend fun get(query: KmpProfilePersonalDataGet) = ProfilePersonalContext().request {
        crud.get(setQuery(query))
    }

    suspend fun create(query: KmpProfilePersonalDataCreate) = ProfilePersonalContext().request {
        crud.create(setQuery(query))
    }

    suspend fun update(query: KmpProfilePersonalDataUpdate) = ProfilePersonalContext().request {
        crud.update(setQuery(query))
    }

    suspend fun delete(query: KmpProfilePersonalDataDelete) = ProfilePersonalContext().request {
        crud.delete(setQuery(query))
    }
}


