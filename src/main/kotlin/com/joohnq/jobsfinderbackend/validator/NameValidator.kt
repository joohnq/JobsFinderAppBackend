package com.joohnq.jobsfinderbackend.validator

import com.joohnq.jobsfinderbackend.exception.NameException

object NameValidator {
    operator fun invoke(name: String): Boolean {
        if (name.trim().isEmpty()) throw NameException.EmptyName
        return true
    }
}