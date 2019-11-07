package com.szakdolgozat.mygrades.database

import com.alamkanak.weekview.WeekViewEvent
import com.szakdolgozat.mygrades.model.*
import com.szakdolgozat.mygrades.util.CurrentDate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object DatabaseHelper {

    fun getLessonsBySubject(subject :Subject): ArrayList<LessonSubjectJoin>{
        var lessons =ArrayList<LessonSubjectJoin>()

        subject.Lessons?.forEach {
            var lessonSubjectJoin=LessonSubjectJoin(subject.subjectId?: "",CurrentDate.getTimeStringFromCalendar(it.startTime), CurrentDate.getTimeStringFromCalendar(it.endTime))
            lessons.add(lessonSubjectJoin)
        }
        return lessons
    }

    fun getEventFromLesson(name:String, lessonSubjectJoin: LessonSubjectJoin): WeekViewEvent{
        val time=Calendar.getInstance().clone() as Calendar
        val event=WeekViewEvent(time.timeInMillis,name,CurrentDate.getCalenderFromString(lessonSubjectJoin.lessonBegin),CurrentDate.getCalenderFromString(lessonSubjectJoin.lessonEnd))
        return event
    }

    fun getSubjectFromStringHash(result: HashMap<String, String>): Subject?{
        val name= result["name"]
        val teacherId= result["teacherid"]
        val subjectId= result["subjectId"]
        val teacher= teacherId?.let { Diary.getTeacherById(it) }
        if(name!=null && teacher!=null && subjectId!=null) {
            val newSubject = Subject(subjectId ,name, teacher)
            newSubject.Description=result["Description"]?:""
            teacher.Subjects.add(newSubject)
            return newSubject
        }
        return null
    }

    fun getEventFromStringHash(result: HashMap<String, String>){
        val begin= result["begin"]
        val end= result["end"]
        val subjectid= result["subjectId"]

        if(begin!=null && end!=null && subjectid!=null) {
            val newLesson = LessonSubjectJoin(subjectid, begin, end)
            val subject=Diary.getSubjectById(subjectid)
            if(subject?.Name!=null) {
                val event = getEventFromLesson(subject.Name, newLesson)
                Diary.subjects.get(Diary.subjects.indexOf(subject)).Lessons.add(event)
            }
        }

    }

    fun getTalkingsFromStringHash(result: HashMap<String, String>){
        val talkingId  = Integer.parseInt(result["talkingId"]?: "-1")
        val lastMessage = result["lastMessage"]
        val userId1 = Diary.getStudentById(result["userId1"]?:"") ?: Diary.getTeacherById(result["userId1"]?:"")
        val userId2 = Diary.getStudentById(result["userId2"]?:"") ?: Diary.getTeacherById(result["userId2"]?:"")

        if(talkingId!=-1 && userId1!=null && userId2!=null){
            Talking(talkingId, userId1, userId2)
        }

    }

    fun getMessagesFromStringHash(result: HashMap<String, String>){
        val messageId = Integer.parseInt(result["messageId"]?:"-1")
        val talkingId = Integer.parseInt(result["talkingId"]?:"-1")
        val message= result["message"]
        val date= CurrentDate.getCalenderFromString(result["date"]?:"")
        val sender=  Diary.getStudentById(result["senderId"]?:"") ?: Diary.getTeacherById(result["senderId"]?:"")
        if(messageId!=-1 && talkingId!=-1 && message !=null && sender!=null){
           Chat.talkings.forEach {
               if(it.Id==talkingId){
                   it.messages.add(Message(messageId,message,sender,date))
               }
           }
        }
    }

    fun getStudentFromStringHash(result: HashMap<String, String>){
        val name=result["name"]
        val id= result["personId"]

        if(name!=null && id!=null && !(User.userId.equals(id))){
            val student=Student(name, id)
        }
    }

    fun getTeacherFromStringHash(result: HashMap<String, String>){
        val name=result["name"]
        val id= result["personId"]

        if(name!=null && id!=null && !(User.userId.equals(id))){
            val teacher=Teacher(name, id)
        }
    }
    fun getPersonsSubjectFromStringHash(result: HashMap<String, String>){
        var subjectId= result["subjectId"]
        var personId= result["peopleId"]
        if(personId!=null && subjectId!=null) {
            var person = Diary.getStudentById(personId)?: Diary.getTeacherById(personId)
            Diary.getSubjectById(subjectId)?.let { person?.Subjects?.add(it) }
        }
    }

    fun getGradeFromStringHash(result: HashMap<String, String>){
        val id=result["gradeId"]
        val subjectId= result["subjectId"]
        val studentId= result["studentId"]
        val teacherId= result["teacherId"]
        val grade= result["grade"]
        val date= result["date"]
        val comment= result["comment"]

        if(id!=null && subjectId!=null && studentId!=null && teacherId!=null && grade!=null && date!=null ){
            val teacher=Diary.getTeacherById(teacherId)
            val student=Diary.getStudentById(studentId)
            val subject=Diary.getSubjectById(subjectId)
            if(teacher!=null && student!=null && subject!=null) {
                var gradeItem = Grade(
                    Integer.parseInt(grade),
                    subject,
                    student,
                    CurrentDate.getCalederFromDateString(date),
                    teacher,
                    (comment ?: "")
                )
            }
        }
    }

}