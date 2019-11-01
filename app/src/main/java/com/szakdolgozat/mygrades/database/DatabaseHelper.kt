package com.szakdolgozat.mygrades.database

import com.alamkanak.weekview.WeekViewEvent
import com.szakdolgozat.mygrades.model.LessonSubjectJoin
import com.szakdolgozat.mygrades.model.Subject
import com.szakdolgozat.mygrades.util.CurrentDate
import java.util.*
import kotlin.collections.ArrayList

object DatabaseHelper {

    fun getLessonsBySubject(subject :Subject): ArrayList<LessonSubjectJoin>{
        var lessons =ArrayList<LessonSubjectJoin>()

        subject.Lessons?.forEach {
            var lessonSubjectJoin=LessonSubjectJoin(0,subject.subjectId?: "",CurrentDate.getStringFromCalendar(it.startTime), CurrentDate.getStringFromCalendar(it.endTime))
            lessons.add(lessonSubjectJoin)
        }
        return lessons
    }

    fun getEventFromLesson(name:String, lessonSubjectJoin: LessonSubjectJoin): WeekViewEvent{
        val time=Calendar.getInstance().clone() as Calendar
        val event=WeekViewEvent(time.timeInMillis,name,CurrentDate.getCalenderFromString(lessonSubjectJoin.lessonBegin),CurrentDate.getCalenderFromString(lessonSubjectJoin.lessonEnd))
        return event
    }

}