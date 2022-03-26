package alireza.nezami.resume.domain.model

import alireza.nezami.resume.data.database.entity.education.EducationDetailEntity
import alireza.nezami.resume.data.database.entity.education.EducationEntity
import alireza.nezami.resume.data.database.entity.works.WorkSummaryEntity
import alireza.nezami.resume.data.database.entity.works.WorksEntity
import alireza.nezami.resume.domain.model.Works
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

/**
 * Created by Alireza Nezami on 3/26/2022.
 */
data class Education(
    val educations: List<EducationDetail>
)
