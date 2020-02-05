package com.szakdolgozat.mygrades.ui.newsubject

import com.alamkanak.weekview.WeekViewEvent
import com.szakdolgozat.mygrades.base.BasePresenter
import com.szakdolgozat.mygrades.database.DatabaseHandler
import com.szakdolgozat.mygrades.model.*
import com.szakdolgozat.mygrades.util.FormatDate
import java.util.*
import kotlin.collections.ArrayList

class NewSubjectPresenter(view: NewSubjectView): BasePresenter<NewSubjectView>(view) {

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
        saveSubject(newSubject)
    }

    fun getLessons():ArrayList<WeekViewEvent>{

        val lessons=ArrayList<WeekViewEvent>()

        if(type == LessonType.Occasional.toString()){
            if(OnceLesson?.From!=null && OnceLesson?.To!=null)
                lessons.add(WeekViewEvent(Subject.getLessonId(),name,
                      FormatDate.getCalenderFromString(OnceLesson!!.From!!) , FormatDate.getCalenderFromString(OnceLesson!!.To!!)))
        }
        else if(type == LessonType.Weekly.toString()){

            val firstLesson= FormatDate.getCalederFromDateString(FirstLessonDate)
            val lastLesson= FormatDate.getCalederFromDateString(LastLessonDate)

            lessons.addAll(getWeeklyLessons(1, firstLesson, lastLesson, LessonDates))
        }

        else if(type == LessonType.Fortnightly.toString()){

            val firstLesson= FormatDate.getCalederFromDateString(FirstLessonDate)
            val lastLesson= FormatDate.getCalederFromDateString(LastLessonDate)

            lessons.addAll(getWeeklyLessons(2, firstLesson, lastLesson, LessonDates))
        }

        return lessons
    }

    fun getWeeklyLessons(week: Int, firstLessonparam: Calendar, lastLessonparam: Calendar, LessonDates: ArrayList<LessonDate> ): ArrayList<WeekViewEvent>{

        val weeklyLessons=ArrayList<WeekViewEvent>()

        val firstLesson= firstLessonparam.clone() as Calendar
        val lastLesson= lastLessonparam.clone() as Calendar

        for(lessondate: LessonDate in LessonDates) {

            var currentEvent = FormatDate.getNextDayEvent(firstLesson, lessondate, name, Subject.getLessonId())


            while (currentEvent.startTime.timeInMillis < lastLesson.timeInMillis) {
                weeklyLessons.add(currentEvent)
                val newFrom =currentEvent.startTime.clone() as Calendar
                newFrom.add(Calendar.DAY_OF_YEAR, (week*7))
                val newTo = currentEvent.endTime.clone() as Calendar
                newTo.add(Calendar.DAY_OF_YEAR, (week*7))
                val newEvent = WeekViewEvent(Subject.getLessonId(), name, newFrom, newTo)
                currentEvent=newEvent
            }
        }
        return weeklyLessons
    }

    fun saveSubject(subject: Subject){
        view?.showLoading()
        DatabaseHandler.saveSubject(subject,{subject -> subjectSaved(subject)},{message-> error(message)})
    }

    fun subjectSaved(subject:Subject){
        DatabaseHandler.savePersonsSubjects(User.person!!,subject,{saveDone()},{message-> error(message)})
    }

    fun saveDone(){
        view?.hideLoading()
        view?.SubjectAdded()
    }

    fun error(message: String){
        view?.hideLoading()
        view?.showMessage(message)
    }

}