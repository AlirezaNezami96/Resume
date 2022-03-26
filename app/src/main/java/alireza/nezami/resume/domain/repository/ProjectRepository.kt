package alireza.nezami.resume.domain.repository

import alireza.nezami.resume.data.database.entity.project.ProjectDetailEntity
import alireza.nezami.resume.domain.model.Project
import alireza.nezami.resume.domain.model.ProjectDetail

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
interface ProjectRepository {
    suspend fun saveData(
        projects: List<ProjectDetailEntity>
    )

    suspend fun getData(): Project?
}