package alireza.nezami.resume.domain.repository

import alireza.nezami.resume.data.database.entity.works.WorkSummaryEntity
import alireza.nezami.resume.domain.model.Career
import alireza.nezami.resume.domain.model.Skill
import alireza.nezami.resume.domain.model.WorkSummary
import alireza.nezami.resume.domain.model.Works

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
interface WorkRepository {

    suspend fun saveData(
        works: List<WorkSummaryEntity>
    )

    suspend fun getData(): Works?
}