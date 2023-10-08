package kynv1.it.fsoft.learnjetpack.repository

import kynv1.it.fsoft.learnjetpack.database.entities.EntitySchool
import kynv1.it.fsoft.learnjetpack.database.entities.EntityStudent
import kynv1.it.fsoft.learnjetpack.database.entities.SchoolAndStudents
import kynv1.it.fsoft.learnjetpack.database.model.School
import kynv1.it.fsoft.learnjetpack.database.model.Student


fun SchoolAndStudents.toSchool(): School {
    return this.run {
        School(
            entitySchool.schoolID,
            entitySchool.schoolName,
            entitySchool.schoolAddress,
            students.toStudents()
        )
    }
}

fun List<EntityStudent>.toStudents() :List<Student>{
    return this.map{
            studentEntity-> Student(
        studentEntity.studentID ,
        studentEntity.studentName ,
        studentEntity.studentGrade,
        studentEntity.schoolID
    )
    }
}

fun School.toSchoolEntity() : EntitySchool {
    return this.run {
        EntitySchool(schoolID,schoolName,schoolAddress)
    }
}

fun Student.toStudentEntity() :EntityStudent{
    return this.run {
        EntityStudent(studentID,studentName,studentGrade,schoolID)
    }
}