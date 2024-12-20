package com.joohnq.jobsfinderbackend.model.repository

import com.joohnq.jobsfinderbackend.domain.entities.Job
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface JobsRepository : MongoRepository<Job, String> {
    @Query("{ 'positionName': { \$regex: ?0, \$options: 'i' } }")
    fun getBy(positionName: String, pageable: Pageable): List<Job>

    @Query("{\$and: [ { 'positionName' : { \$regex: ?0, \$options: 'i' } }, { 'jobType' : { \$in : [ ?1 ] } } ] }")
    fun getBy(positionName: String, jobType: String, pageable: Pageable): List<Job>
}
