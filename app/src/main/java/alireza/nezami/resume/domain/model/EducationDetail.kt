package alireza.nezami.resume.domain.model

import alireza.nezami.resume.data.database.entity.works.WorkSummaryEntity
import alireza.nezami.resume.domain.model.WorkSummary
import alireza.nezami.resume.domain.model.Works

/**
 * Created by Alireza Nezami on 3/26/2022.
 */
data class EducationDetail(
    val className: String,
    val year: Double,
    val percentage: Double,
)
