package alireza.nezami.resume.data.database.entity.project

import alireza.nezami.resume.domain.model.ProjectDetail

data class ProjectDetailEntity(
    val name: String,
    val teamSize: Int,
    val summary: String,
    val technologyUsed: String,
    val Role: String,
)

fun ProjectDetailEntity.toDomainModel() =
    ProjectDetail(
        name,
        teamSize,
        summary,
        technologyUsed,
        Role
    )
