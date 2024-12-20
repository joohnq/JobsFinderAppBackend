package com.joohnq.jobsfinderbackend.controller.job

import com.joohnq.jobsfinderbackend.exception.InvalidParameterException
import com.joohnq.jobsfinderbackend.model.service.job.JobService
import com.joohnq.jobsfinderbackend.domain.entities.Job
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("jobs")
class JobController(
    @Autowired val service: JobService
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): Job = service.getById(id)

    @GetMapping
    fun getAll(
        @RequestParam(required = true) positionName: String?,
        @RequestParam(required = true) limit: Int = 20,
        @RequestParam(required = false) type: String?,
        @RequestParam(required = false) offset: Int = 0,
    ): List<Job> {
        if (positionName.isNullOrBlank())
            throw InvalidParameterException("The position name cannot be null or empty")

        if (type != null && type.isBlank())
            throw InvalidParameterException("The position type cannot be empty")

        val pageable: Pageable = PageRequest.of(offset, limit)

        val jobs = type?.run {
            service.getByPositionNameAndJobType(positionName, this, pageable)
        } ?: run {
            service.getByPositionName(positionName, pageable)
        }

        return jobs
    }
}
