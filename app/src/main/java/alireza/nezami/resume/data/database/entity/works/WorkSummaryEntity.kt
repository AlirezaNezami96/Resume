package alireza.nezami.resume.data.database.entity.works

import alireza.nezami.resume.domain.model.WorkSummary
import alireza.nezami.resume.domain.model.Works

/**
 * Created by Alireza Nezami on 3/26/2022.
 */
data class WorkSummaryEntity(
    val companyName: String,
    val duration: Double
)

fun WorkSummaryEntity.toDomainModel() =
    WorkSummary(
        this.companyName,
        this.duration
    )
