package com.joohnq.jobsfinderbackend.model.service.user

import com.joohnq.jobsfinderbackend.exception.UserException
import com.joohnq.jobsfinderbackend.domain.entities.User.Companion.toResponse
import com.joohnq.jobsfinderbackend.domain.entities.request.UserRequest
import com.joohnq.jobsfinderbackend.domain.entities.request.UserRequest.Companion.toDomain
import com.joohnq.jobsfinderbackend.domain.entities.response.UserResponse
import com.joohnq.jobsfinderbackend.model.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository,
    private val encoder: PasswordEncoder
) {
    fun create(userReq: UserRequest): UserResponse {
        val found = repository.findOneByEmail(userReq.email)
        if (found != null) throw UserException.UserAlreadyExists
        val user = userReq.toDomain().copy(password = encoder.encode(userReq.password))
        val userRes = repository.save(user)
        return userRes.toResponse()
    }
}