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
    var counter :Int=0
    var counter2 :Int=0


    fun getDataBase(context: Context){
        if(db==null) {
            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "myGrades5.db"
            ).build()
        }
    }

    fun getOfflineDatas(){
        GlobalScope.launch {
            getSubjectsFromLocal()
            DatabaseReadDoneEvent.event("ok")
        }
    }

    fun clearOfflineDatas(){
        GlobalScope.launch {
            clearLocalSubject()
        }
    }


    fun getDatas(){

       // getCurrentUsersDatas()
        /*getChat()
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
        getStaticGrade()*/

        //SAVES-----------------------------------------------------------

  /*     saveSubject()
        savePersonsSubjects(Diary.students as ArrayList<Person>, "Student")
        savePersonsSubjects(Diary.teachers as ArrayList<Person>, "Teacher")
        saveGrades(Diary.grades)
        saveTalkings(Chat.talkings)

        Chat.talkings.forEach {
            saveMessages(it)
        }
        */
        //----------------------------------------------------------------

        getPersons()

    }

fun clearLocalSubject(){
    if(db!=null){
        db!!.subjectDao().deleteAll()
        db!!.LessonDao().deleteAll()
    }

}

fun getSubjectsFromLocal() {

        if (db != null) {
            val readedSubject = db!!.subjectDao().getAll()
            readedSubject.forEach {
                val lessons = db!!.LessonDao().getAllBySubject(it.subjectId)
                lessons.forEach { lesson: LessonSubjectJoin ->
                    it.Lessons.add(DatabaseHelper.getEventFromLesson(it.Name, lesson))
                }
            }
            User.person!!.Subjects.addAll(readedSubject)
        }

    }

    fun writeSubjectsToLocal(){
        clearLocalSubject()
            if (db != null) {
                for (subject: Subject in User.person!!.Subjects) {
                    db!!.subjectDao().insertAll(subject)
                   val lessons=DatabaseHelper.getLessonsBySubject(subject)
                    lessons.forEach {
                        db!!.LessonDao().insertAll(it)
                    }
                }
            }

    }

    fun saveNewSubjectToLocal(subject: Subject){

            if (db != null) {
                db!!.subjectDao().insertAll(subject)
                val lessons = DatabaseHelper.getLessonsBySubject(subject)
                lessons.forEach {
                    db!!.LessonDao().insertAll(it)
                }
            }

    }

    fun saveUsersSubjectsToLocal(){
        GlobalScope.launch {
            writeSubjectsToLocal()
        }
    }


    fun getPersons(){

        Diary.students.clear()
        Diary.teachers.clear()

        if(User.type.equals("Student")){
            Diary.students.add(User.person as Student)
        }
        else{
            Diary.teachers.add(User.person as Teacher)
        }
        User.person?.Subjects?.clear()

        FirebaseFunctionHelper.getPersons("Student").addOnCompleteListener {task ->
            if (task.isSuccessful) {
                task.result?.forEach { student ->
                    DatabaseHelper.getStudentFromStringHash(student)
                }
                println("Student")
            }

            FirebaseFunctionHelper.getPersons("Teacher").addOnCompleteListener {
                if (it.isSuccessful) {
                    it.result?.forEach { teacher ->
                        DatabaseHelper.getTeacherFromStringHash(teacher)
                    }
                    println("Teachers")
                }
                getSubjects()
            }
        }

    }

    fun getSubjects(){
        FirebaseFunctionHelper.getSubjects().addOnCompleteListener {
            if(it.isSuccessful){
                Diary.subjects.clear()
                it.result?.forEach { i ->
                    val subject=DatabaseHelper.getSubjectFromStringHash(i)
                }
            }
            println("Subject")
            getLessons()
        }
    }

    fun getLessons(){
        FirebaseFunctionHelper.getLesson().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.forEach { j ->
                    val event = DatabaseHelper.getEventFromStringHash(j)
                }
            }
            println("Lessons")
            getPersonsSubjects()

            }
        }



    fun getPersonsSubjects(){
        FirebaseFunctionHelper.getPersonsSubjects().addOnCompleteListener { result ->
            if (result.isSuccessful) {
                result.result?.forEach { subjectId ->
                    DatabaseHelper.getPersonsSubjectFromStringHash(subjectId)
                }
            }
            else{
                println(result.exception?.message)
            }
            println("PerSubject")
                getGrades()
                saveUsersSubjectsToLocal()

        }
    }

    fun getGrades() {
        Diary.grades.clear()
        FirebaseFunctionHelper.getGrade().addOnCompleteListener {task ->
            if(task.isSuccessful){
                task.result?.forEach {
                    DatabaseHelper.getGradeFromStringHash(it)
                }
            }
            println("Grades")
            getTalkings()
        }
    }

    fun getTalkings(){
        FirebaseFunctionHelper.getTalkings().addOnCompleteListener {task ->
            if(task.isSuccessful){
                task.result?.forEach {
                    DatabaseHelper.getTalkingsFromStringHash(it)
                }
                getMessages()
            }
        }
    }

    fun getMessages(){
        FirebaseFunctionHelper.getMessages().addOnCompleteListener {task ->
            if(task.isSuccessful){
                task.result?.forEach {
                    DatabaseHelper.getMessagesFromStringHash(it)
                }
            }
            DatabaseReadDoneEvent.event("ok")
        }
    }


    fun saveProfil(succes:() -> Unit, error:(String) -> Unit){
        FirebaseFunctionHelper.saveprofil()
            .addOnCompleteListener{
                if(it.isSuccessful){
                    succes()
                }
                else{
                    error(it.exception?.message?: "error while saving profil")
                }
            }

    }


    fun saveSubject(subject: Subject, succes: (Subject) -> Unit, error: (String) -> Unit){
            FirebaseFunctionHelper.saveSubject(subject).addOnCompleteListener {
                if(it.isSuccessful){
                    val lessons=DatabaseHelper.getLessonsBySubject(subject)

                    FirebaseFunctionHelper.saveLesson().addOnCompleteListener {
                            if(it.isSuccessful){
                                succes(subject)
                            }
                            else{
                                error(it.exception?.message?:"Error in saving")
                            }
                        }
                }
                else{
                    error(it.exception?.message?:"Error in saving")
                }
            }
    }

    fun savePersonsSubjects(person : Person, subject: Subject, succes: (Subject) -> Unit, error: (String) -> Unit){

             FirebaseFunctionHelper.savePersonSubject(person, subject).addOnCompleteListener {
                 if(it.isSuccessful) {
                     succes(subject)
                     GlobalScope.launch {
                         saveNewSubjectToLocal(subject)
                     }
                 }
                 else
                    error(it.exception?.message?: "Error in save")
             }
    }

    fun saveGrades(grade :Grade, succes: () -> Unit, error: (String) -> Unit){
            FirebaseFunctionHelper.saveGrade(grade).addOnCompleteListener {
                if(it.isSuccessful){
                    succes()
                }
                else{
                    error(it.exception?.message?: "Error in Save")
                }
            }
    }


    fun saveTalkings(talking: Talking, succes: () -> Unit, error: (String) -> Unit){
         FirebaseFunctionHelper.saveTalking(talking).addOnCompleteListener {
             if(it.isSuccessful){
                 succes()
             }
             else{
                 error(it.exception?.message ?: "Error in save")
             }
         }

     }


    fun saveMessages(talking: Talking, message: Message, succes: (Message) -> Unit, error: (String) -> Unit){
            FirebaseFunctionHelper.saveMessage(talking, message).addOnCompleteListener {
                if(it.isSuccessful){
                    succes(message)
                }
                else{
                    error(it.exception?.message?:"Error in save")
                }
        }
    }



/*
    private fun getChat() {
        var messages =ArrayList<Message>()
        var talkings=ArrayList<Talking>()
        messages.add(Message("Hi lorem ipsum dolor lorem ipsum!",Diary.teachers[0]))
        messages.add(Message("Hi lorem ipsum dolor!",Diary.students[0]))
        talkings.add(Talking(Diary.teachers[0],Diary.students[0], messages))
        talkings.add(Talking(Diary.students[0],Diary.teachers[0], messages))
        Chat.talkings.addAll(talkings)
    }*/




/*    fun getCurrentUsersDatas(){
        if(User.type.equals("Student")){
            getStudentDatas()
        }
        else{
            getTeacherDatas()
        }
    }*/

  /*  fun getStaticGrade(){
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

    }*/


/*    private fun getTeacherDatas() {
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
    }*/
}