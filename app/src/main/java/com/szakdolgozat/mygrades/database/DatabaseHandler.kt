package com.szakdolgozat.mygrades.database

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.room.Room
import com.alamkanak.weekview.WeekViewEvent
import com.szakdolgozat.mygrades.events.DatabaseReadDoneEvent
import com.szakdolgozat.mygrades.firebase.FirebaseFunctionHelper
import com.szakdolgozat.mygrades.model.*
import com.szakdolgozat.mygrades.ui.splash.SplashActivity
import com.szakdolgozat.mygrades.util.CurrentDate
import java.util.*
import kotlin.collections.ArrayList
import kotlinx.coroutines.*


object DatabaseHandler {

    var db : AppDatabase? =null

    fun getDataBase(context: Context){
       db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "myGrades3.db"
        ).build()
    }

    fun getDatas(){

    GlobalScope.launch {
            db!!.LessonDao().deleteAll()
            db!!.subjectDao().deleteAll()
        }

        getPersons()
        getChat()
        var lessons=ArrayList<WeekViewEvent>()
        var teacher= Teacher("Gajdos Sándor", "423h7d")
        lessons.add(
            WeekViewEvent(3,"Adatbázisok",
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay()-1,
                8,0,
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay()-1,
                10,0)
        )
        lessons.add(
            WeekViewEvent(4,"Adatbázisok2",
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay()+1,
                12,0,
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay()+1,
                14,0)
        )

        var lessons2=ArrayList<WeekViewEvent>()
        var teacher2= Teacher("Szirmay Geci", "423h7d")
        lessons2.add(
            WeekViewEvent(5,"Grafika",
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay(),
                10,0,
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay(),
                12,0)
        )

        var subject1 = Subject("Adatbázisok",teacher,lessons)
        var subject2 = Subject("Grafika",teacher2,lessons2)
        getGrades()

        saveSubject()
        savePersonsSubjects(Diary.students as ArrayList<Person>, "Student")
        savePersonsSubjects(Diary.teachers as ArrayList<Person>, "Teacher")


        DatabaseReadDoneEvent.event("ok")

    }

    fun getSubjectsToDiary() {
            if(db!=null){
                val readedSubject = db!!.subjectDao().getAll()
                readedSubject.forEach {
                    val lessons = db!!.LessonDao().getAllBySubject(it.subjectId)
                        lessons.forEach { lesson:LessonSubjectJoin ->
                            it.Lessons.add(DatabaseHelper.getEventFromLesson(it.Name,lesson))
                        }
                }
                 Diary.subjects.addAll(readedSubject)
            }

    }

    fun writeSubjectsFromDiary(){
            if (db != null) {
                for (subject: Subject in Diary.subjects) {
                    db!!.subjectDao().insertAll(subject)
                   var lessons=DatabaseHelper.getLessonsBySubject(subject)
                    lessons.forEach {
                        db!!.LessonDao().insertAll(it)
                    }
                }
            }
    }

    fun saveSubject(){
        for (subject: Subject in Diary.subjects) {
            FirebaseFunctionHelper.saveSubject(subject)
            val lessons=DatabaseHelper.getLessonsBySubject(subject)
            lessons.forEach {
                FirebaseFunctionHelper.saveLesson(it, subject)
            }
        }
    }

    fun savePersonsSubjects(persons : ArrayList<Person>, type: String){
        for (person: Person in persons) {
         person.Subjects.forEach{
             FirebaseFunctionHelper.savePersonSubject(type, person, it).addOnCompleteListener {
                 writeMessage(it.result.toString())
             }
         }
        }
    }

    fun writeMessage(message: String){
        println(message)
    }







    private fun getChat() {
        var messages =ArrayList<Message>()
        var talkings=ArrayList<Talking>()
        messages.add(Message("Hi lorem ipsum dolor lorem ipsum!",Diary.teachers[0]))
        messages.add(Message("Hi lorem ipsum dolor!",Diary.students[0]))
        talkings.add(Talking(Diary.teachers[0],Diary.students[0], messages))
        talkings.add(Talking(Diary.students[0],Diary.teachers[0], messages))
        Chat.talkings.addAll(talkings)
    }

    fun getGrades() {
       Diary.grades.clear()
       var grade=Grade(
           5,
           Diary.subjects[0],
           Diary.students[0],
           Calendar.getInstance().clone() as Calendar,
           Diary.teachers[0],
           "Grade comment"
       )

       var grade2=Grade(
           3,
           Diary.subjects[0],
           Diary.students[0],
           Calendar.getInstance().clone() as Calendar,
           Diary.teachers[1],
           "Grade comment"
       )

       var grade3=Grade(
           1,
           Diary.subjects[0],
           Diary.students[0],
           Calendar.getInstance().clone() as Calendar,
           Diary.teachers[2],
           "fjklasf kljfsalj klauirew iurewozqé oqowz"
       )

    }

    fun getPersons(){
        getCurrentUsersDatas()
    }

    fun getCurrentUsersDatas(){
        if(User.type.equals("Student")){
            getStudentDatas()
        }
        else{
            getTeacherDatas()
        }
    }

    private fun getTeacherDatas() {
        User.person = Teacher(User.Name ?:"Jipsz Jakab", User.userId ?:"uid")
        var lessons=ArrayList<WeekViewEvent>()
        lessons.add(
            WeekViewEvent(2,"Ujtargy",
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay(),
                15,0,
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay(),
                16,0)
        )
        //1 subject
        var subject = Subject("Ujtargy", User.person as Teacher,lessons)
        User.person?.Subjects?.add(subject)
        var student=Student("Jipsz Jakab", "uid")
        var student2=Student("Jipsz Jakab2", "uid")
        var student3=Student("Jipsz Jakab3", "uid")
        student.Subjects.add(subject)
        student2.Subjects.add(subject)
    }

    private fun getStudentDatas() {
        User.person = Student(User.Name ?:"Jipsz Jakab", User.userId ?:"uid")
        var teacher= Teacher("Tanar Pelda", "42342gfd")
        var lessons=ArrayList<WeekViewEvent>()
        lessons.add(
            WeekViewEvent(2,"Ujtargy",
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay(),
                15,0,
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay(),
                16,0)
        )
        //1 subject
        var subject = Subject("Ujtargy",teacher,lessons)
        User.person?.Subjects?.add(subject)
        Diary.teachers.add(Teacher(User.Name ?:"Jipsz Jakab", User.userId ?:"uid"))
    }
}