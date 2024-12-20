package com.joohnq.jobsfinderbackend.domain.entities

import com.joohnq.jobsfinderbackend.domain.entities.response.UserResponse
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
data class User(
    @Id val id: String? = null,
    val name: String,
    val email: String,
    val password: String,
) {
    companion object {
        fun User.toResponse(): UserResponse = UserResponse(id = id, name = name, email = email)
    }
}