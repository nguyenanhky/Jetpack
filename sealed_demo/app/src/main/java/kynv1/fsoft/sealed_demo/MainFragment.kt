package kynv1.fsoft.sealed_demo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import kynv1.fsoft.sealed_demo.model.TaskState
import kynv1.fsoft.sealed_demo.viewmodel.TaskViewModel


class MainFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var performTask:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        taskViewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        performTask = view.findViewById(R.id.performTaskButton)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerData()
        performTask.setOnClickListener {
            taskViewModel.performTask()
        }
    }

    private fun observerData() {
        taskViewModel.taskState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is TaskState.Loading -> {
                    // Hiển thị trạng thái đang tải
                    Log.d(TAG, "Loading: ")
                }

                is TaskState.Success -> {
                    // Hiển thị kết quả thành công
                    Log.d(TAG, "Success: ")
                }

                is TaskState.Error -> {
                    // Hiển thị kết quả lỗi
                    Log.d(TAG, "Success: ")
                }
            }
        }
    }


    companion object {
        const val TAG = "MainFragment"
    }
}