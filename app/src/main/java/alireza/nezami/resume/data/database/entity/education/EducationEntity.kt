package alireza.nezami.resume.data.database.entity.education

import alireza.nezami.resume.data.database.entity.education.EducationDetailEntity
import alireza.nezami.resume.data.database.entity.works.WorkSummaryEntity
import alireza.nezami.resume.data.database.entity.works.WorkSummaryEntityTypeConverter
import alireza.nezami.resume.data.database.entity.works.WorksEntity
import alireza.nezami.resume.domain.model.Education
import alireza.nezami.resume.domain.model.Works
import androidx.room.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

/**
 * Created by Alireza Nezami on 3/26/2022.
 */
@Entity(tableName = "education")
@TypeConverters(EducationEntityTypeConverter::class)
data class EducationEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,

    @ColumnInfo(name = "education_list")
    val educations: List<EducationDetailEntity>
)

fun EducationEntity.toDomainModel() =
    Education(
        this.educations.map { it.toDomainModel() },
    )

internal class EducationEntityTypeConverter {
    private val type = Types.newParameterizedType(List::class.java, EducationDetailEntity::class.java)
    private val adapter: JsonAdapter<List<EducationDetailEntity>> = Moshi.Builder().build().adapter(type)

    @TypeConverter
    fun stringToList(data: String?) =
        data?.let { adapter.fromJson(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<EducationDetailEntity>): String =
        adapter.toJson(someObjects)
}

