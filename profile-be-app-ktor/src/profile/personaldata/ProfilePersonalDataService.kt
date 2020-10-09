package com.lpfun.profile.personaldata

import com.lpfun.backend.common.model.error.InternalServerError
import com.lpfun.backend.common.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalContext
import com.lpfun.backend.common.model.profile.personal.ProfilePersonalData
import com.lpfun.backend.kmp.profile.resultItem
import com.lpfun.backend.kmp.profile.setQuery
import com.lpfun.backend.profile.domain.personal.ProfilePersonalCrud
import com.lpfun.base.request
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataCreate
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataDelete
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataGet
import com.lpfun.transport.multiplatform.profile.personal.KmpProfilePersonalDataUpdate

class ProfilePersonalDataService(
    val crud: ProfilePersonalCrud
) {
    suspend fun get(query: KmpProfilePersonalDataGet) = ProfilePersonalContext().run {
        try {
            crud.get(setQuery(query))
        } catch (t: Throwable) {
            errors.add(InternalServerError.instance)
        }
        resultItem()
    }

    suspend fun create(query: KmpProfilePersonalDataCreate) = ProfilePersonalContext().run {
        try {
            crud.create(setQuery(query))
        } catch (t: Throwable) {
            errors.add(InternalServerError.instance)
        }
        resultItem()
    }

    suspend fun update(query: KmpProfilePersonalDataUpdate) = ProfilePersonalContext().run {
        try {
            crud.update(setQuery(query))
        } catch (t: Throwable) {
            errors.add(InternalServerError.instance)
        }
        resultItem()
    }

    suspend fun delete(query: KmpProfilePersonalDataDelete) = ProfilePersonalContext().request {
        setQuery(query).apply {
            responseProfile = ProfilePersonalData()
            responseProfileStatus = ProfileContextStatus.SUCCESS
        }
    }
}


