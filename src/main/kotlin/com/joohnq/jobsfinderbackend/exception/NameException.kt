package com.joohnq.jobsfinderbackend.exception

sealed class NameException(override val message: String): InvalidParameterException(message) {
    data object EmptyName: NameException("Name cannot be empty")
}