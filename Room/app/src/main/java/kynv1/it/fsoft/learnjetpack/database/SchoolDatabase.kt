package kynv1.it.fsoft.learnjetpack.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kynv1.it.fsoft.learnjetpack.database.dao.SchoolDAO
import kynv1.it.fsoft.learnjetpack.database.dao.StudentDAO
import kynv1.it.fsoft.learnjetpack.database.entities.EntitySchool
import kynv1.it.fsoft.learnjetpack.database.entities.EntityStudent
import kynv1.it.fsoft.learnjetpack.utils.Constant

@Database(entities = [EntitySchool::class , EntityStudent::class],version = 1,exportSchema = false)
abstract  class SchoolDatabase : RoomDatabase() {
    abstract fun schoolDAO() : SchoolDAO
    abstract fun studentDAO() : StudentDAO

    companion object{
        private var INSTANCE :SchoolDatabase ?= null

        fun getInstance(context: Context) :SchoolDatabase = INSTANCE ?: synchronized(this){
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext ,
                SchoolDatabase::class.java ,
                Constant.DATABASE_NAME).build()
                .also {
                    INSTANCE = it
                }
        }
    }
}