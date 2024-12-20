package com.joohnq.jobsfinderbackend.exception

sealed class PasswordException(override val message: String): InvalidParameterException(message) {
    data object PasswordEmpty: PasswordException("Password cannot be empty")
    data object PasswordTooShort: PasswordException("Password must be at least 8 characters")
}