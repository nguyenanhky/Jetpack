package kynv1.it.fsoft.learnjetpack.base

import kynv1.it.fsoft.learnjetpack.database.model.School

sealed  class SchoolViewState(
    val school :List<School>? = null,
    val message :String? = null
) {
    class GetSchoolSuccess(schools :List<School>) :SchoolViewState(schools)
    class SuccessOperator :SchoolViewState()
    class FailOperator(msg :String) :SchoolViewState(message = msg)
}