package com.joohnq.jobsfinderbackend.domain.entities

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails

typealias ApplicationUser = com.joohnq.jobsfinderbackend.domain.entities.User

fun ApplicationUser.mapToUserDetails(): UserDetails =
    User
        .builder()
        .username(this.email)
        .password(this.password)
        .build()
