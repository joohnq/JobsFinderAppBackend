package com.joohnq.jobsfinderbackend.validator

import com.joohnq.jobsfinderbackend.exception.PasswordException

object PasswordValidator {
    operator fun invoke(password: String): Boolean {
        if (password.trim().isEmpty()) throw PasswordException.PasswordEmpty
        if (password.length < 8) throw PasswordException.PasswordTooShort
        return true
    }
}