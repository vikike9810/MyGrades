package com.szakdolgozat.mygrades.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.alamkanak.weekview.WeekViewEvent
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.util.CurrentDate
import com.szakdolgozat.mygrades.model.Subject
import com.szakdolgozat.mygrades.model.User
import java.util.*
import kotlin.collections.ArrayList


class SubjectsRecyclerViewAdapter : RecyclerView.Adapter<SubjectsRecyclerViewAdapter.ViewHolder>() {

    private val subjects = mutableListOf<Subject>()

    var itemClickListener: SubjectClickListener? = null
    private var subjectAddable: Boolean=false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.subjects_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subject = subjects[position]
        holder.subject = subject

        holder.subName.text = subject.Name
        holder.subTeacher.text = subject.TeacherName
        holder.subId.text=subject.subjectId
        subject.Lessons?.let { holder.setDate(it) }
        if(subjectAddable){
            holder.take_subject.visibility=View.VISIBLE
        }
    }

    fun addItem(subject: Subject) {
        val size = subjects.size
        subjects.add(subject)
        notifyItemInserted(size)
    }

    fun changeAddSubject(addable: Boolean){
        subjectAddable=addable
    }

    fun addAll(newSubjects: ArrayList<Subject>) {
        val size = subjects.size
        subjects.addAll(newSubjects)
        subjects.sortWith(Subject.SubjectComparator)
        notifyDataSetChanged()
    }

    fun deleteRow(position: Int) {
        subjects.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeItem(subject: Subject){
        val pos= subjects.indexOf(subject)
        subjects.remove(subject)
        notifyItemRemoved(pos)
    }

    fun addNewItems(newSubjects: ArrayList<Subject>){
        subjects.clear()
        subjects.addAll(newSubjects)
        subjects.sortWith(Subject.SubjectComparator)
        notifyDataSetChanged()
    }

    override fun getItemCount() = subjects.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var subject: Subject? = null

        val subName: TextView =view.findViewById(R.id.Subject_Name)
        val subTeacher: TextView = view.findViewById(R.id.Subject_teacher)
        val subId: TextView= view.findViewById(R.id.Subject_id)
        val day1: TextView = view.findViewById(R.id.day1)
        val day2: TextView = view.findViewById(R.id.day2)
        val day3: TextView = view.findViewById(R.id.day3)
        val day4: TextView = view.findViewById(R.id.day4)
        val day5: TextView = view.findViewById(R.id.day5)

        val date1:TextView = view.findViewById(R.id.date1)
        val date2:TextView = view.findViewById(R.id.date2)
        val date3:TextView = view.findViewById(R.id.date3)
        val date4:TextView = view.findViewById(R.id.date4)
        val date5:TextView = view.findViewById(R.id.date5)

        val take_subject : Button =view.findViewById(R.id.take_subject)

        val lessons_card: CardView = view.findViewById(R.id.Sub_cardview)

        init {
            itemView.setOnClickListener {
                subject?.let { subject -> itemClickListener?.onItemClick(subject) }
            }

            take_subject.setOnClickListener{
                subject?.let {subject ->itemClickListener?.onItemTaked(subject)}
            }

            itemView.setOnLongClickListener { view ->
                itemClickListener?.onItemLongClick(adapterPosition, view)
                true
            }
        }

        fun setDate(events: ArrayList<WeekViewEvent>){
            for(event: WeekViewEvent in events){
                when(event.startTime[Calendar.DAY_OF_WEEK]){
                    2 -> { setDay(day1, date1, event) }
                    3 -> { setDay(day2, date2, event) }
                    4 -> { setDay(day3, date3,event) }
                    5 -> { setDay(day4, date4,event) }
                    6 -> { setDay(day5, date5,event) }
                }
            }
        }

        fun setDay(day: TextView, date: TextView, event: WeekViewEvent){
            lessons_card.visibility=View.VISIBLE
            day.visibility=View.VISIBLE
            date.visibility=View.VISIBLE
            date.text=CurrentDate.getEventDateString(event)
        }


    }

    interface SubjectClickListener {
        fun onItemTaked(subject: Subject)
        fun onItemClick(subject: Subject)
        fun onItemLongClick(position: Int, view: View): Boolean
    }

}