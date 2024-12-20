package com.joohnq.jobsfinderbackend.model.repository

import com.joohnq.jobsfinderbackend.domain.entities.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String>{
    fun findOneByEmail(email: String): User?
}