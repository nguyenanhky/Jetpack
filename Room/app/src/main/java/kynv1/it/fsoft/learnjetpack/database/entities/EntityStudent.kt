package kynv1.it.fsoft.learnjetpack.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kynv1.it.fsoft.learnjetpack.utils.Constant


@Entity(tableName = Constant.STUDENT_TABLE ,foreignKeys = [
    ForeignKey(
        entity = EntitySchool::class ,
        parentColumns = [Constant.SCHOOL_ID],
        childColumns = [Constant.SCHOOL_ID],
        onDelete = ForeignKey.CASCADE
    )
])

data class EntityStudent (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name=Constant.STUDENT_ID)
    val studentID :Long ,
    @ColumnInfo(name=Constant.STUDENT_NAME)
    val studentName :String ,
    @ColumnInfo(name=Constant.STUDENT_GRADE)
    val studentGrade:Int ,
    @ColumnInfo(name = Constant.SCHOOL_ID)
    val schoolID :Long
)