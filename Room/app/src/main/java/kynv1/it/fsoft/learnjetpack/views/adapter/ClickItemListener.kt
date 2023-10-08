package kynv1.it.fsoft.learnjetpack.views.adapter

import kynv1.it.fsoft.learnjetpack.database.model.School
import kynv1.it.fsoft.learnjetpack.database.model.Student

interface ClickItemListener {
    fun onClickDeleteSchool(school: School)

    fun onClickDeleteStudent(student: Student)

    fun onClickUpdateSchool(school: School)

    fun onClickUpdateStudent(student: Student)
}