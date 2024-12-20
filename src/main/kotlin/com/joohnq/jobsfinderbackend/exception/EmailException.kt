package com.joohnq.jobsfinderbackend.exception

sealed class EmailException(override val message: String): InvalidParameterException(message) {
    data object EmailEmpty : EmailException("Email cannot be empty")
    data object EmailInvalid : EmailException("Email invalid")
}