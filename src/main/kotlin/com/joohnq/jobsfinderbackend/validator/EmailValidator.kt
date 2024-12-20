package com.joohnq.jobsfinderbackend.validator

import com.joohnq.jobsfinderbackend.exception.EmailException

object EmailValidator {
    operator fun invoke(email: String): Boolean {
        if (email.trim().isEmpty()) throw EmailException.EmailEmpty
        if (!Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$").matches(email)) throw EmailException.EmailInvalid
        return true
    }
}