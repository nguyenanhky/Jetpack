package kynv1.it.fsoft.learnjetpack.database.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val studentID:Long = 0,
    val studentName:String,
    val studentGrade:Int,
    val schoolID:Long = 0
):Parcelable