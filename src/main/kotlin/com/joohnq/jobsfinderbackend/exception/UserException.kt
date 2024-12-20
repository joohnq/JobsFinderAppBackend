package com.joohnq.jobsfinderbackend.exception

sealed class UserException(override val message: String): RuntimeException(message) {
    data object UserAlreadyExists: UserException("User with this email already exists")
    data object UserNotFound: UserException("User not found")
}