package kynv1.fsoft.sealed_demo.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kynv1.fsoft.sealed_demo.model.TaskState
import kotlin.random.Random

class TaskViewModel : ViewModel(){
    private val _taskState = MutableLiveData<TaskState>()
    val taskState: LiveData<TaskState> = _taskState

    fun performTask(){
        _taskState.value = TaskState.Loading

        // Simulate a delay
        Handler(Looper.getMainLooper()).postDelayed({
            // Thực hiện tác vụ và kiểm tra kết quả
            val result = performActualTask()
            _taskState.value = result
        }, 2000)

    }

    private fun performActualTask(): TaskState {
        // Thực hiện tác vụ thực tế và trả về kết quả tương ứng
        return if (Random.nextBoolean()) {
            TaskState.Success("Task completed successfully.")
        } else {
            TaskState.Error("An error occurred during the task.")
        }
    }
}