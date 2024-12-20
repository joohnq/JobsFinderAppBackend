package com.joohnq.jobsfinderbackend.controller.user

import com.joohnq.jobsfinderbackend.validator.EmailValidator
import com.joohnq.jobsfinderbackend.validator.NameValidator
import com.joohnq.jobsfinderbackend.validator.PasswordValidator
import com.joohnq.jobsfinderbackend.domain.entities.request.UserRequest
import com.joohnq.jobsfinderbackend.domain.entities.response.UserResponse
import com.joohnq.jobsfinderbackend.model.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController(
    @Autowired val service: UserService
) {
    @PostMapping
    fun create(@RequestBody userReq: UserRequest): UserResponse {
        NameValidator(userReq.name)
        EmailValidator(userReq.email)
        PasswordValidator(userReq.password)

        return service.create(userReq)
    }
}
