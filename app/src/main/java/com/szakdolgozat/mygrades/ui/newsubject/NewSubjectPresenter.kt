package com.szakdolgozat.mygrades.ui.newsubject

import android.widget.Toast
import com.alamkanak.weekview.WeekViewEvent
import com.szakdolgozat.mygrades.model.*
import com.szakdolgozat.mygrades.util.CurrentDate
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class NewSubjectPresenter(var view: NewSubjectView) {

    var LessonDates=ArrayList<LessonDate>()
    var OnceLesson:OnceLesson?= null
    var FirstLessonDate: String=""
    var LastLessonDate: String=""
    var type=""
    var name=""

    fun newSubject(name: String, desc: String){
        this.name=name
        val lessons=ArrayList<WeekViewEvent>()
        lessons.addAll(getLessons())
        val newSubject=Subject(name, User.person as Teacher,lessons)
        newSubject.Description=desc
        User.person?.Subjects?.add(newSubject)
        view.SubjectAdded()
    }

    fun getLessons():ArrayList<WeekViewEvent>{

        var lessons=ArrayList<WeekViewEvent>()

        if(type.equals("Occasional")){
            if(OnceLesson?.From!=null && OnceLesson?.To!=null)
                lessons.add(WeekViewEvent(Subject.getLessonId(),name,
                      CurrentDate.getCalenderFromString(OnceLesson!!.From!!) , CurrentDate.getCalenderFromString(OnceLesson!!.To!!)))
        }
        else if(type.equals("Weekly")){

            var firstLesson= CurrentDate.getCalederFromDateString(FirstLessonDate)
            var lastLesson= CurrentDate.getCalederFromDateString(LastLessonDate)

            lessons.addAll(getWeeklyLessons(1, firstLesson, lastLesson, LessonDates))
        }

        else if(type.equals("Fortnightly")){

            var firstLesson= CurrentDate.getCalederFromDateString(FirstLessonDate)
            var lastLesson= CurrentDate.getCalederFromDateString(LastLessonDate)

            lessons.addAll(getWeeklyLessons(2, firstLesson, lastLesson, LessonDates))
        }

        return lessons
    }

    fun getWeeklyLessons(week: Int, firstLessonparam: Calendar, lastLessonparam: Calendar, LessonDates: ArrayList<LessonDate> ): ArrayList<WeekViewEvent>{

        var weeklyLessons=ArrayList<WeekViewEvent>()

        var firstLesson= firstLessonparam.clone() as Calendar
        var lastLesson= lastLessonparam.clone() as Calendar

        for(lessondate: LessonDate in LessonDates) {

            var currentEvent = CurrentDate.getNextDayEvent(firstLesson, lessondate, name, Subject.getLessonId())


            while (currentEvent.startTime.timeInMillis < lastLesson.timeInMillis) {
                weeklyLessons.add(currentEvent)
                var newFrom =currentEvent.startTime.clone() as Calendar
                newFrom.add(Calendar.DAY_OF_YEAR, (week*7))
                var newTo = currentEvent.endTime.clone() as Calendar
                newTo.add(Calendar.DAY_OF_YEAR, (week*7))
                val newEvent = WeekViewEvent(Subject.getLessonId(), name, newFrom, newTo)
                currentEvent=newEvent
            }
        }

        return weeklyLessons
    }

}