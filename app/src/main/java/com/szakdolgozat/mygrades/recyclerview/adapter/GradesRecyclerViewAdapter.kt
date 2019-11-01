package com.szakdolgozat.mygrades.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.model.Grade
import com.szakdolgozat.mygrades.model.User
import java.util.*

import kotlin.collections.ArrayList

class GradesRecyclerViewAdapter: RecyclerView.Adapter<GradesRecyclerViewAdapter.ViewHolder>() {

     var grades = mutableListOf<Grade>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.grade_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val grade = grades[position]
        holder.grade = grade
        holder.textSubject.text=grade.subject.Name
        holder.textGrade.text=grade.grade.toString()
        holder.textDate.text=grade.date[Calendar.YEAR].toString()+"."+(grade.date[Calendar.MONTH]+1).toString()+"."+grade.date[Calendar.DATE].toString()
        holder.textComment.text=grade.comment
        if(User.type.equals("Student")){
            holder.textTeacher.text=grade.teacher.getName()
        }
        else{
            holder.teacherImage.setImageResource(R.drawable.student)
            holder.textTeacher.text=grade.student.getName()
        }
    }


    override fun getItemCount(): Int {
        return grades.size
    }

    fun addAll(grades: ArrayList<Grade>){
        var size=grades.size
        grades.addAll(grades)
        notifyItemRangeInserted(size, grades.size)
    }

    fun addNewItems(newGrades: ArrayList<Grade>){
        grades.clear()
        grades.addAll(newGrades)
        notifyDataSetChanged()
    }

    fun addGrade(grade: Grade){
        grades.add(grade)
        notifyDataSetChanged()
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var grade: Grade? = null
        var teacherImage = view.findViewById<ImageView>(R.id.grade_teacher_icon)
        val textSubject= view.findViewById<TextView>(R.id.grade_subject)
        val textGrade= view.findViewById<TextView>(R.id.grade_grade)
        val textTeacher= view.findViewById<TextView>(R.id.grade_teacher)
        val textDate =view.findViewById<TextView>(R.id.grade_date)
        val textComment= view.findViewById<TextView>(R.id.grade_comment)

    }
}