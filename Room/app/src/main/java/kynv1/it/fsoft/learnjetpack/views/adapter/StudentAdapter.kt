package kynv1.it.fsoft.learnjetpack.views.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kynv1.it.fsoft.learnjetpack.database.model.Student
import kynv1.it.fsoft.learnjetpack.databinding.ItemStudentBinding

class StudentAdapter (
    private val clickListener:ClickItemListener
) : ListAdapter<Student, StudentAdapter.StudentViewHolder>(diffCallback) {


    inner class StudentViewHolder(private val studentBinding: ItemStudentBinding) :
        RecyclerView.ViewHolder(studentBinding.root) {

        fun bind(student: Student) = with(studentBinding) {
            studentBinding.student = student
            deleteStudent.setOnClickListener {
                clickListener.onClickDeleteStudent(student)
            }

            itemView.setOnClickListener {
                clickListener.onClickUpdateStudent(student)
                Log.d("Student Adapter", "${student.studentID}")
            }
        }


    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.studentID == newItem.studentID
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem == newItem
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(
            ItemStudentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}
