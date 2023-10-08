package kynv1.it.fsoft.learnjetpack.database.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class School(
    val schoolID :Long = 0,
    val schoolName :String ,
    val schoolAddress :String ,
    val students:List<Student>?= null ,
    var expanded :Boolean =false
):Parcelable