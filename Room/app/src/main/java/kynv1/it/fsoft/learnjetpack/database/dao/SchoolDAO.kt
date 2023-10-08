package kynv1.it.fsoft.learnjetpack.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kynv1.it.fsoft.learnjetpack.database.entities.EntitySchool
import kynv1.it.fsoft.learnjetpack.database.entities.SchoolAndStudents
import kynv1.it.fsoft.learnjetpack.utils.Constant

@Dao
interface SchoolDAO {
    @Insert
    suspend fun insertSchool(entitySchool: EntitySchool)

    @Delete
    suspend fun deleteSchool(entitySchool:EntitySchool)

    @Update
    suspend fun updateSchool(entitySchool :EntitySchool)

    @Transaction
    @Query("select * from ${Constant.SCHOOL_TABLE}")
    fun getSchoolAndStudents() : Flow<List<SchoolAndStudents>>
}