package kynv1.it.fsoft.learnjetpack.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kynv1.it.fsoft.learnjetpack.database.model.School
import kynv1.it.fsoft.learnjetpack.databinding.ItemSchoolSpinnerBinding

class SchoolSpinnerAdapter (
    context: Context, schools :List<School>
) : ArrayAdapter<School>(context,0,schools){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding=convertView?.let{
            ItemSchoolSpinnerBinding.bind(convertView)
        } ?: ItemSchoolSpinnerBinding.inflate(LayoutInflater.from(context),parent,false)
        binding.tvSchoolName.text=(getItem(position)?.schoolName ?:"")
        return binding.root
    }

}