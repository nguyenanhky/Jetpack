package kynv1.it.fsoft.learnjetpack.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kynv1.it.fsoft.learnjetpack.database.model.School
import kynv1.it.fsoft.learnjetpack.databinding.ItemSchoolBinding

class SchoolAdapter (
    private val clickListener :ClickItemListener
) : ListAdapter<School, SchoolAdapter.SchoolViewHolder>(diffCallback) {

    inner class SchoolViewHolder(
        private val layoutManager: LinearLayoutManager,
        private val schoolBinding: ItemSchoolBinding
    ) : RecyclerView.ViewHolder(schoolBinding.root) {
        fun bind(school: School) = with(schoolBinding) {
            initView(school)
            initAction()
        }


        private fun initView(school: School) = with(schoolBinding) {
            schoolBinding.school = school
            rvStudent.visibility = View.GONE
            rvStudent.layoutManager = layoutManager


            val studentAdapter = StudentAdapter(clickListener)
            schoolBinding.rvStudent.adapter = studentAdapter
            studentAdapter.submitList(school.students)
        }

        private fun initAction() = with(schoolBinding) {
            deleteSchool.setOnClickListener {
                clickListener.onClickDeleteSchool(currentList[adapterPosition])
            }

            expandList.setOnClickListener {
                val expanded = currentList[adapterPosition].expanded
                currentList[adapterPosition].expanded = !expanded
                rvStudent.visibility = if (expanded) View.GONE else View.VISIBLE
                submitList(currentList)
            }

            itemView.setOnClickListener {
                clickListener.onClickUpdateSchool(currentList[adapterPosition])
            }
        }

    }
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<School>() {
            override fun areItemsTheSame(oldItem: School, newItem: School): Boolean {
                return oldItem.schoolID == newItem.schoolID
            }

            override fun areContentsTheSame(oldItem: School, newItem: School): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        return SchoolViewHolder(
            LinearLayoutManager(parent.context, RecyclerView.VERTICAL, false),
            ItemSchoolBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}



