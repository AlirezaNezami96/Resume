package alireza.nezami.resume.domain.model

/**
 * Created by Alireza Nezami on 3/18/2022.
 */

data class Career(
    val objective: String,
    val totalYears: Double,
    val skills: List<Skill>

)