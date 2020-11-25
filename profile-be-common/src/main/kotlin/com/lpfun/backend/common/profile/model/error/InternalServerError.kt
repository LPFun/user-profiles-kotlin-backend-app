package com.lpfun.backend.common.profile.model.error

data class InternalServerError(
    override val code: String = "internal-error",
    override val group: IProfileError.Groups = IProfileError.Groups.SERVER,
    override val field: String = "",
    override val level: IProfileError.Levels = IProfileError.Levels.ERROR,
    override val message: String = "Internal server error"

) : IProfileError {
    companion object {
        val instance = InternalServerError()
    }
}