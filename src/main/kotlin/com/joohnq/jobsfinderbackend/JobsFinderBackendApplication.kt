package com.joohnq.jobsfinderbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
class JobsFinderBackendApplication

fun main(args: Array<String>) {
	runApplication<JobsFinderBackendApplication>(*args)
}
