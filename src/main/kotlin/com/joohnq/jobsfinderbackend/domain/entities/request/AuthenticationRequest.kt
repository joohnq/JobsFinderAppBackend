package com.joohnq.jobsfinderbackend.domain.entities.request

data class AuthenticationRequest(
    val email: String,
    val password: String
)