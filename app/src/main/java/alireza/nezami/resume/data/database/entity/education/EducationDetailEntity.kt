package alireza.nezami.resume.data.database.entity.education

import alireza.nezami.resume.domain.model.EducationDetail

/**
 * Created by Alireza Nezami on 3/26/2022.
 */
data class EducationDetailEntity(
    val className: String,
    val year: Double,
    val percentage: Double,
)

fun EducationDetailEntity.toDomainModel() =
    EducationDetail(
        this.className,
        this.year,
        this.percentage,
    )
