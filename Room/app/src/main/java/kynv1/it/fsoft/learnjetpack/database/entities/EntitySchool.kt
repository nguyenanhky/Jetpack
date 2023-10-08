package kynv1.it.fsoft.learnjetpack.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kynv1.it.fsoft.learnjetpack.utils.Constant

@Entity(tableName = Constant.SCHOOL_TABLE )
data class EntitySchool (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constant.SCHOOL_ID)
    val schoolID :Long ,
    @ColumnInfo(name =Constant.SCHOOL_NAME)
    val schoolName :String ,
    @ColumnInfo(name =Constant.SCHOOL_ADRESS)
    val schoolAddress :String
)