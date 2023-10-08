package kynv1.it.fsoft.learnjetpack.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import kynv1.it.fsoft.learnjetpack.database.entities.EntityStudent

@Dao
interface StudentDAO {
    @Insert
    suspend fun insertStudent(entityStudent: EntityStudent)

    @Delete
    suspend fun deleteStudent(entityStudent: EntityStudent)

    @Update
    suspend fun updateStudent(entityStudent: EntityStudent)
}