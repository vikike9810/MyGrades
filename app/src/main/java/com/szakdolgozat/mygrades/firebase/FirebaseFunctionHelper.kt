package com.szakdolgozat.mygrades.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.functions.FirebaseFunctions
import com.szakdolgozat.mygrades.model.*
import com.szakdolgozat.mygrades.util.CurrentDate
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.HashMap

object FirebaseFunctionHelper {

    private  var functions: FirebaseFunctions = FirebaseFunctions.getInstance()


    fun register(name: String, type: String, userid:String?) : Task<String> {
        val data = hashMapOf(
            "userid" to userid,
            "name" to name,
            "type" to type
        )

        return functions.getHttpsCallable("register")
            .call(data)
            .continueWith { task ->

                val result = task.result?.data as String
                result
            }
    }

    fun saveprofil() : Task<String> {
        val data = hashMapOf(
            "userid" to User.userId,
            "name" to User.Name,
            "type" to User.type,
            "birthday" to User.birthday,
            "city" to User.address?.city,
            "zip" to User.address?.zip,
            "street" to User.address?.street,
            "number" to User.address?.number
        )

        return functions.getHttpsCallable("saveprofile")
            .call(data)
            .continueWith { task ->

                val result = task.result?.data as String
                result
            }
    }

    fun getProfile(userid:String?) : Task<HashMap<String,String>>{
            val data = hashMapOf(
                "userid" to userid
            )

            return functions.getHttpsCallable("getProfile")
                .call(data)
                .continueWith { task ->

                    val result = task.result?.data as HashMap<String,String>
                    result
                }
    }

    fun getPersons(type:String): Task<ArrayList<HashMap<String, String>>>{
        val data = hashMapOf(
            "type" to type
        )

        return functions.getHttpsCallable("getPersons")
            .call(data)
            .continueWith { task ->

                val result = task.result?.data as ArrayList<HashMap<String,String>>
                result
            }
    }

    fun getPersonsSubjects(): Task<ArrayList<HashMap<String, String>>>{
        val data = HashMap<String, String>()

        return functions.getHttpsCallable("getPersonsSubjects")
            .call(data)
            .continueWith { task ->

                val result = task.result?.data as ArrayList<HashMap<String,String>>
                result
            }
    }

    fun getSubjects() : Task<ArrayList<HashMap<String, String>>> {
        val data = HashMap<String,String>()

        return functions.getHttpsCallable("getSubjects")
            .call(data)
            .continueWith { task ->

                val result = task.result?.data as ArrayList<HashMap<String,String>>
                result
            }
    }

    fun getLesson() : Task<ArrayList<HashMap<String, String>>> {
        val data = HashMap<String,String>()

        return functions.getHttpsCallable("getLesson")
            .call(data)
            .continueWith { task ->

                val result = task.result?.data as ArrayList<HashMap<String,String>>
                result
            }

    }

    fun saveSubject(subject: Subject) : Task<String> {

        val data = hashMapOf(
            "subjectId" to subject.subjectId,
            "name" to subject.Name,
            "Description" to subject.Description,
            "teacherId" to (subject.Teacher.getuserId()?:" ")
        )

        return functions.getHttpsCallable("saveSubject")
            .call(data)
            .continueWith { task ->

                val result = task.result?.data as String
                result
            }
    }

    fun saveLesson() : Task<String> {

        val list=ArrayList<HashMap<String,String>>()
        Diary.subjects.forEach { subjectitem ->
            subjectitem.Lessons.forEach {lessonitem ->
                list.add(hashMapOf(
                    "subjectId" to subjectitem.subjectId,
                    "begin" to CurrentDate.getTimeStringFromCalendar(lessonitem.startTime),
                    "end" to CurrentDate.getTimeStringFromCalendar(lessonitem.endTime)
                ))
            }
        }

        val data = hashMapOf(
            "Lessons" to list
        )



        return functions.getHttpsCallable("saveLesson")
            .call(data)
            .continueWith { task ->

                val result = task.result?.data as String
                result
            }
    }

    fun savePersonSubject( person: Person, subject :Subject) : Task<String> {

        val data = hashMapOf(
            "subjectId" to subject.subjectId,
            "userId" to person.getuserId()
        )

        return functions.getHttpsCallable("savePersonSubject")
            .call(data)
            .continueWith { task ->

                val result = task.result?.data as String
                result
            }
    }

    fun saveGrade(grade: Grade) : Task<String> {

        val data = hashMapOf(
            "subjectId" to grade.subject.subjectId,
            "studentId" to grade.student.getuserId(),
            "teacherId" to grade.teacher.getuserId(),
            "grade" to grade.grade,
            "comment" to grade.comment,
            "date" to CurrentDate.getStringFromCalendar(grade.date),
            "gradeId" to grade.Id
        )

        return functions.getHttpsCallable("saveGrade")
            .call(data)
            .continueWith { task ->

                val result = task.result?.data as String
                result
            }
    }

    fun getGrade(): Task<ArrayList<HashMap<String, String>>> {
        val data = HashMap<String, String>()

        return functions.getHttpsCallable("getGrades")
            .call(data)
            .continueWith { task ->

                val result = task.result?.data as ArrayList<HashMap<String,String>>
                result
            }

    }

    fun saveTalking(talking: Talking): Task<String> {

        val data = hashMapOf(
             "userId2" to talking.person2.getuserId(),
             "userId1" to talking.person1.getuserId(),
             "Id" to talking.Id,
             "lastMessage" to talking.getLastMessageDate()
        )

        return functions.getHttpsCallable("saveTalking")
            .call(data)
            .continueWith { task ->

                val result = task.result?.data as String
                result
            }
    }

    fun getTalkings() :Task<ArrayList<HashMap<String, String>>> {
        val data = HashMap<String, String>()

        return functions.getHttpsCallable("getTalkings")
            .call(data)
            .continueWith { task ->

                val result = task.result?.data as ArrayList<HashMap<String,String>>
                result
            }

    }

    fun saveMessage(talking: Talking, message: Message): Task<String> {

        val data = hashMapOf(
            "sender" to message.sender.getuserId(),
            "talkingId" to talking.Id,
            "Id" to message.Id,
            "message" to message.message,
            "date" to message.getMessageTime()
        )

        return functions.getHttpsCallable("saveMessage")
            .call(data)
            .continueWith { task ->

                val result = task.result?.data as String
                result
            }
    }

    fun getMessages() :Task<ArrayList<HashMap<String, String>>> {
        val data = HashMap<String, String>()

        return functions.getHttpsCallable("getMessages")
            .call(data)
            .continueWith { task ->

                val result = task.result?.data as ArrayList<HashMap<String,String>>
                result
            }

    }



}