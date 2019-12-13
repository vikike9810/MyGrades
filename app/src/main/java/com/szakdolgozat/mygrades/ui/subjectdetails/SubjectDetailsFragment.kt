package com.szakdolgozat.mygrades.ui.subjectdetails

import android.R.attr.dialogTheme
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.model.Diary
import com.szakdolgozat.mygrades.model.Subject
import kotlinx.android.synthetic.main.fragment_subject_details.*
import android.view.Gravity
import android.view.WindowManager
import android.R.attr.x
import android.view.Display
import android.graphics.Point
import android.widget.TextView
import com.alamkanak.weekview.WeekViewEvent
import com.szakdolgozat.mygrades.util.CurrentDate
import java.util.*


class SubjectDetailsFragment(var subjectName: String): DialogFragment(){
    var subject :Subject?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subject = Diary.getSubjectByName(subjectName?: "")
        setStyle(STYLE_NO_TITLE, R.style.Base_ThemeOverlay_AppCompat_Dialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_subject_details,container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        frag_back.setOnClickListener { dismiss() }
        initView()
    }

    override fun onResume() {
        super.onResume()
        val window = dialog.window

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
    }

    private fun initView() {
        frag_Subject_Name.text=subjectName
        frag_Subject_teacher.text=subject?.TeacherName
        frag_Subject_Desc.text=subject?.Description
        frag_Subject_id.text=subject?.subjectId

        subject?.Lessons?.forEach {
            when(it.startTime[Calendar.DAY_OF_WEEK]){
                2 -> { setDay(frag_day1, frag_date1,  it) }
                3 -> { setDay(frag_day2, frag_date2,  it) }
                4 -> { setDay(frag_day3, frag_date3, it) }
                5 -> { setDay(frag_day4, frag_date4, it) }
                6 -> { setDay(frag_day5, frag_date5, it) }
            }
        }
    }

    fun setDay(day: TextView, date: TextView, event: WeekViewEvent){
        frag_Sub_cardview.visibility=View.VISIBLE
        day.visibility=View.VISIBLE
        date.visibility=View.VISIBLE
        date.text=CurrentDate.getEventDateString(event)
    }


}