package com.joohnq.jobsfinderbackend.model.service.auth

import com.joohnq.jobsfinderbackend.config.JwtProperties
import com.joohnq.jobsfinderbackend.domain.entities.request.AuthenticationRequest
import com.joohnq.jobsfinderbackend.domain.entities.response.AuthenticationResponse
import com.joohnq.jobsfinderbackend.exception.InvalidParameterException
import com.joohnq.jobsfinderbackend.model.service.user.CustomUserDetailService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService(
    private val tokenService: TokenService,
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailService,
    private val jwtProperties: JwtProperties
) {
    fun authentication(authRequest: AuthenticationRequest): AuthenticationResponse = try {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authRequest.email)

        val token = tokenService.generate(
            user = user,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration),
        )

        AuthenticationResponse(token)
    } catch (e: Exception) {
        throw InvalidParameterException("Invalid credentials, please check and try again")
    }
}
