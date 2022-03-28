package alireza.nezami.resume.data.repository

import alireza.nezami.resume.data.database.dao.ProjectsDao
import alireza.nezami.resume.data.database.entity.project.ProjectDetailEntity
import alireza.nezami.resume.data.database.entity.project.ProjectEntity
import alireza.nezami.resume.data.database.entity.project.toDomainModel
import alireza.nezami.resume.data.database.entity.works.WorkSummaryEntity
import alireza.nezami.resume.data.database.entity.works.WorksEntity
import alireza.nezami.resume.domain.model.Project
import alireza.nezami.resume.domain.model.Works
import alireza.nezami.resume.domain.repository.ProjectRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
class ProjectRepositoryImpl(
    private val projectsDao: ProjectsDao
) : ProjectRepository {

    override suspend fun saveData(projects: List<ProjectDetailEntity>) {
        projectsDao.insertCareer(
            ProjectEntity(
                projects = projects
            )
        )
    }

    override suspend fun getData(): Project {
        return projectsDao.getAll().toDomainModel()
    }


}