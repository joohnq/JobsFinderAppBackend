package com.joohnq.jobsfinderbackend.model.service.user

import com.joohnq.jobsfinderbackend.domain.entities.mapToUserDetails
import com.joohnq.jobsfinderbackend.model.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findOneByEmail(username)
            ?.mapToUserDetails()
            ?: throw IllegalArgumentException("User not found")
}