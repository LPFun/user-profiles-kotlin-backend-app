package com.lpfun.backend.common.model.profile

abstract class ProfileBase(
        var id: String = ""
){
    companion object{
        val NONE = object : ProfileBase(""){}
    }
}