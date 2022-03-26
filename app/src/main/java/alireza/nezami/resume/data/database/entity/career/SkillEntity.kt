package alireza.nezami.resume.data.database.entity.career

import alireza.nezami.resume.domain.model.Skill

/**
 * Created by Alireza Nezami on 3/26/2022.
 */
data class SkillEntity(
    val name: String,
)

fun SkillEntity.toDomainModel() =
    Skill(
        this.name,
    )
