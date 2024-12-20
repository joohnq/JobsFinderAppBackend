package com.joohnq.jobsfinderbackend.domain.entities.request

import com.joohnq.jobsfinderbackend.domain.entities.User

data class UserRequest(
    val name: String,
    val email: String,
    val password: String
) {
    companion object {
        fun UserRequest.toDomain(): User = User(name = name, email = email, password = password)
    }

}