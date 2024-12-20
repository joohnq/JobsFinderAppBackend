package com.joohnq.jobsfinderbackend.controller.auth

import com.joohnq.jobsfinderbackend.domain.entities.request.AuthenticationRequest
import com.joohnq.jobsfinderbackend.domain.entities.response.AuthenticationResponse
import com.joohnq.jobsfinderbackend.model.service.auth.AuthenticationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping
    fun authenticate(@RequestBody authRequest: AuthenticationRequest): AuthenticationResponse =
        authenticationService.authentication(authRequest)
}