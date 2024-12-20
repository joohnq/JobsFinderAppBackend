package com.joohnq.jobsfinderbackend.model.service.job

import com.joohnq.jobsfinderbackend.exception.NotFoundException
import com.joohnq.jobsfinderbackend.domain.entities.Job
import com.joohnq.jobsfinderbackend.model.repository.JobsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class JobService(
    @Autowired private val repository: JobsRepository
) {
    fun getByPositionNameAndJobType(positionName: String, jobType: String, pageable: Pageable): List<Job> =
        repository.getBy(positionName = positionName, jobType = jobType, pageable = pageable)

    fun getByPositionName(positionName: String, pageable: Pageable): List<Job> =
        repository.getBy(positionName = positionName, pageable = pageable)

    fun getById(id: String): Job =
        repository.findById(id).orElseThrow {
           NotFoundException("Not found a job with this ID")
        }
}