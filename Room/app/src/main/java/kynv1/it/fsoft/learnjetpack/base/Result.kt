package kynv1.it.fsoft.learnjetpack.base

sealed class Result<T>(val data :T? = null ,val message :String? = null){
    class Success<T>(data:T?) :Result<T>(data)
    class Error<T>(message: String? = null ,data:T? = null) :Result<T>(data,message)
}