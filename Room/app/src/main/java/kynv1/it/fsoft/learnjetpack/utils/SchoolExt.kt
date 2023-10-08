package kynv1.it.fsoft.learnjetpack.utils

import kynv1.it.fsoft.learnjetpack.database.model.School

fun List<School>.studentNumbers():Int{
    if(this.isEmpty()) return  0
    return map{
            school -> school.students?.size?: 0
    }.reduce{acc,studentNum ->acc+studentNum}
}