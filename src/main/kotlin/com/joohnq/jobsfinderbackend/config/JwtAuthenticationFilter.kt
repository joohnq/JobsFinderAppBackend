package com.joohnq.jobsfinderbackend.config

import com.joohnq.jobsfinderbackend.model.service.user.CustomUserDetailService
import com.joohnq.jobsfinderbackend.model.service.auth.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val userDetailService: CustomUserDetailService,
    private val tokenService: TokenService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header: String? = request.getHeader("Authorization")

        if (header.doesNotContainBearerToken()) {
            filterChain.doFilter(request, response)
            return
        }

        val token = header!!.extractTokenValue()
        val email = tokenService.extractEmail(token)

        if (email == null && SecurityContextHolder.getContext().authentication != null) return@doFilterInternal

        val foundUser = userDetailService.loadUserByUsername(email!!)

        if (!tokenService.isValid(token, foundUser)) return@doFilterInternal
        
        updateContext(foundUser, request)

        filterChain.doFilter(request, response)
    }

    private fun updateContext(foundUser: UserDetails, request: HttpServletRequest) {
        val token = UsernamePasswordAuthenticationToken(foundUser, null, foundUser.authorities)
            .also {
                it.details = WebAuthenticationDetailsSource().buildDetails(request)
            }
        SecurityContextHolder.getContext().authentication = token
    }

    private fun String?.doesNotContainBearerToken(): Boolean =
        this == null || !this.startsWith("Bearer ")

    private fun String.extractTokenValue(): String = this.substringAfter("Bearer ")
}