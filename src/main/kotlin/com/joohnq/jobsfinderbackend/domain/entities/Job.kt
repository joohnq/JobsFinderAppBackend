package com.joohnq.jobsfinderbackend.domain.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("jobs")
data class Job(
    @Id val id: String,
    val positionName: String,
    val salary: String?,
    val jobType: List<String>?,
    val company: String,
    val location: String,
    val rating: String?,
    val reviewsCount: String?,
    val urlInput: String?,
    val url: String,
    val postedAt: String,
    val scrapedAt: String,
    val postingDateParsed: String?,
    val description: String,
    val descriptionHTML: String,
    val externalApplyLink: String?,
    val searchInput: Map<String, String>,
    val isExpired: Boolean
)