package com.lpfun.backend.common.model.error

data class GeneralError(
    override val code: String = "",
    override val group: IProfileError.Groups,
    override val field: String = "",
    override val level: IProfileError.Levels,
    override val message: String = ""

) : IProfileError {
    constructor(
        code: String,
        group: IProfileError.Groups = IProfileError.Groups.SERVER,
        e: Throwable
    ) : this(
        code = "",
        group = IProfileError.Groups.SERVER,
        field = "",
        level = IProfileError.Levels.ERROR,
        message = e.message ?: "Unknown exception"
    )
}