package kynv1.fsoft.sealed_demo.model

sealed class TaskState {
    data class Success(val data:String):TaskState()
    data class Error(val message:String):TaskState()
    object Loading: TaskState()
}