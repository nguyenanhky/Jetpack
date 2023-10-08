package kynv1.it.fsoft.learnjetpack.database.entities

import androidx.room.Embedded
import androidx.room.Relation
import kynv1.it.fsoft.learnjetpack.utils.Constant

data class SchoolAndStudents(
    @Embedded val entitySchool: EntitySchool,
    @Relation(
        parentColumn = Constant.SCHOOL_ID,
        entityColumn = Constant.SCHOOL_ID
    )
    val students: List<EntityStudent>
)