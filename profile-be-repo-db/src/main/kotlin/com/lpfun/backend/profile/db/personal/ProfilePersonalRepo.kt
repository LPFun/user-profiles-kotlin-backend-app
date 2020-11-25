package com.lpfun.backend.profile.db.personal

import com.lpfun.backend.common.profile.repository.IProfilePersonalDataRepository
import com.lpfun.backend.profile.db.base.personal.ProfilePersonalRepoBase

class ProfilePersonalRepo : IProfilePersonalDataRepository by ProfilePersonalRepoBase()
