package alireza.nezami.resume.data.repository

import alireza.nezami.resume.data.database.dao.WorksDao
import alireza.nezami.resume.data.database.entity.works.WorkSummaryEntity
import alireza.nezami.resume.data.database.entity.works.WorksEntity
import alireza.nezami.resume.data.database.entity.works.toDomainModel
import alireza.nezami.resume.domain.model.Works
import alireza.nezami.resume.domain.repository.WorkRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
class WorkRepositoryImpl(
    private val worksDao: WorksDao
) : WorkRepository {


    override suspend fun saveData(works: List<WorkSummaryEntity>) {
        worksDao.insertWorks(
            WorksEntity(
                works = works,
            )
        )
    }

    override suspend fun getData(): Works {
        return worksDao.getAll().toDomainModel()
    }


}