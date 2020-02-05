package com.szakdolgozat.mygrades.database

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.room.Room
import com.szakdolgozat.mygrades.events.DatabaseReadDoneEvent
import com.szakdolgozat.mygrades.events.GetGradeEvent
import com.szakdolgozat.mygrades.events.GetMessageEvent
import com.szakdolgozat.mygrades.firebase.FirebaseFunctionHelper
import com.szakdolgozat.mygrades.model.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


object DatabaseHandler {

    var db: AppDatabase? = null


    fun getDataBase(context: Context) {
        if (db == null) {
            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "myGrades5.db"
            ).build()
        }
    }

    suspend fun getOfflineDatas() {
        coroutineScope {
            val reader = async { getSubjectsFromLocal() }
            reader.await()
            DatabaseReadDoneEvent.event("ok")
        }
    }

    fun clearOfflineDatas() {
        GlobalScope.launch {
            clearLocalSubject()
        }
    }


    fun getDatas() {
        getPersons()
    }

    private fun clearLocalSubject() {
        if (db != null) {
            db!!.subjectDao().deleteAll()
            db!!.LessonDao().deleteAll()
        }

    }

    private fun clearPersons() {
        Diary.students.clear()
        Diary.teachers.clear()

        if (User.type.equals(UserType.Student)) {
            Diary.students.add(User.person as Student)
        } else {
            Diary.teachers.add(User.person as Teacher)
        }
        User.person?.Subjects?.clear()
    }

    private fun getSubjectsFromLocal() {

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

    private fun writeSubjectsToLocal() {
        clearLocalSubject()
        if (db != null) {
            for (subject: Subject in User.person!!.Subjects) {
                db!!.subjectDao().insertAll(subject)
                val lessons = DatabaseHelper.getLessonsBySubject(subject)
                lessons.forEach {
                    db!!.LessonDao().insertAll(it)
                }
            }
        }

    }

    private fun saveNewSubjectToLocal(subject: Subject) {

        if (db != null) {
            db!!.subjectDao().insertAll(subject)
            val lessons = DatabaseHelper.getLessonsBySubject(subject)
            lessons.forEach {
                db!!.LessonDao().insertAll(it)
            }
        }

    }

    private fun saveUsersSubjectsToLocal() {
        GlobalScope.launch {
            writeSubjectsToLocal()
        }
    }


    private fun getPersons() {

        clearPersons()

        FirebaseFunctionHelper.getPersons("Student").addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.forEach { student ->
                    DatabaseHelper.getStudentFromStringHash(student)
                }
            }

            FirebaseFunctionHelper.getPersons("Teacher").addOnCompleteListener {
                if (it.isSuccessful) {
                    it.result?.forEach { teacher ->
                        DatabaseHelper.getTeacherFromStringHash(teacher)
                    }
                }
                getSubjects()
            }
        }

    }

    private fun getSubjects() {
        FirebaseFunctionHelper.getSubjects().addOnCompleteListener {
            if (it.isSuccessful) {
                Diary.subjects.clear()
                it.result?.forEach { i ->
                    val subject = DatabaseHelper.getSubjectFromStringHash(i)
                }
            }
            getLessons()
        }
    }

    private fun getLessons() {
        FirebaseFunctionHelper.getLesson().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.forEach { j ->
                    val event = DatabaseHelper.getEventFromStringHash(j)
                }
            }
            getPersonsSubjects()

        }
    }


    private fun getPersonsSubjects() {
        FirebaseFunctionHelper.getPersonsSubjects().addOnCompleteListener { result ->
            if (result.isSuccessful) {
                result.result?.forEach { subjectId ->
                    DatabaseHelper.getPersonsSubjectFromStringHash(subjectId)
                }
            }
            getGrades()
            saveUsersSubjectsToLocal()

        }
    }

    private fun getGrades() {
        Diary.grades.clear()
        FirebaseFunctionHelper.getGrade().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.forEach {
                    DatabaseHelper.getGradeFromStringHash(it)
                }
            }

            getTalkings()
        }
    }

    fun getGrade(id: String, builder: NotificationCompat.Builder, succes: (NotificationCompat.Builder) -> Unit) {
        FirebaseFunctionHelper.getGrade(id).addOnCompleteListener {
            if (it.isSuccessful) {
                if (it.result != null) {
                    DatabaseHelper.getGradeFromStringHash(it.result!!)
                    GetGradeEvent.event("Grade")
                    succes(builder)
                }
            }
        }
    }

    private fun getTalkings() {
        FirebaseFunctionHelper.getTalkings().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.forEach {
                    DatabaseHelper.getTalkingsFromStringHash(it)
                }
                getMessages()
            }
        }
    }

    private fun getMessages() {
        FirebaseFunctionHelper.getMessages().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.forEach {
                    DatabaseHelper.getMessagesFromStringHash(it)
                }
            }
            DatabaseReadDoneEvent.event("ok")
        }
    }

    fun getMessage(id: String, builder: NotificationCompat.Builder, succes: (NotificationCompat.Builder) -> Unit) {
        FirebaseFunctionHelper.getMessage(id).addOnCompleteListener {
            if (it.isSuccessful) {
                if (it.result != null) {
                    DatabaseHelper.getMessagesFromStringHash(it.result!!)
                    GetMessageEvent.event("Message")
                    succes(builder)
                }
            }
        }
    }


    fun saveProfil(succes: () -> Unit, error: (String) -> Unit) {
        FirebaseFunctionHelper.saveprofil()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    succes()
                } else {
                    error(it.exception?.message ?: "error while saving profil")
                }
            }

    }


    fun saveSubject(subject: Subject, succes: (Subject) -> Unit, error: (String) -> Unit) {
        FirebaseFunctionHelper.saveSubject(subject).addOnCompleteListener {
            if (it.isSuccessful) {
                val lessons = DatabaseHelper.getLessonsBySubject(subject)

                FirebaseFunctionHelper.saveLesson().addOnCompleteListener {
                    if (it.isSuccessful) {
                        succes(subject)
                    } else {
                        error(it.exception?.message ?: "Error in saving")
                    }
                }
            } else {
                error(it.exception?.message ?: "Error in saving")
            }
        }
    }

    fun savePersonsSubjects(person: Person, subject: Subject, succes: (Subject) -> Unit, error: (String) -> Unit) {

        FirebaseFunctionHelper.savePersonSubject(person, subject).addOnCompleteListener {
            if (it.isSuccessful) {
                succes(subject)
                GlobalScope.launch {
                    saveNewSubjectToLocal(subject)
                }
            } else
                error(it.exception?.message ?: "Error in save")
        }
    }

    fun saveGrades(grade: Grade, succes: () -> Unit, error: (String) -> Unit) {
        FirebaseFunctionHelper.saveGrade(grade).addOnCompleteListener {
            if (it.isSuccessful) {
                succes()
            } else {
                error(it.exception?.message ?: "Error in Save")
            }
        }
    }


    fun saveTalkings(talking: Talking, succes: () -> Unit, error: (String) -> Unit) {
        FirebaseFunctionHelper.saveTalking(talking).addOnCompleteListener {
            if (it.isSuccessful) {
                succes()
            } else {
                error(it.exception?.message ?: "Error in save")
            }
        }

    }


    fun saveMessages(talking: Talking, message: Message, succes: (Message) -> Unit, error: (String) -> Unit) {
        FirebaseFunctionHelper.saveMessage(talking, message).addOnCompleteListener {
            if (it.isSuccessful) {
                succes(message)
            } else {
                error(it.exception?.message ?: "Error in save")
            }
        }
    }


}