package com.szakdolgozat.mygrades.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.messaging.FirebaseMessaging
import com.szakdolgozat.mygrades.model.*
import com.szakdolgozat.mygrades.util.CurrentDate
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.HashMap

object FirebaseFunctionHelper {

    private  var functions: FirebaseFunctions = FirebaseFunctions.getInstance()
    private var messaging= FirebaseMessaging.getInstance()


    fun register(name: String, type: String, userid:String?) : Task<String> {
        val data = hashMapOf(
            "userid" to userid!!,
            "name" to name,
            "type" to type
        )

        return  callfunction("register", data)

    }

    fun saveprofil() : Task<String> {
        val data :HashMap<String, String> = hashMapOf(
            "userid" to User.userId,
            "name" to (User.Name?: ""),
            "type" to (User.type?: ""),
            "birthday" to (User.birthday?: ""),
            "city" to (User.address.city?: ""),
            "zip" to (User.address.zip?: ""),
            "street" to (User.address.street?: ""),
            "number" to (User.address.number?: "")
        )

        return  callfunction("saveprofile", data)

    }

    fun getProfile(userid:String?) : Task<HashMap<String,String>>{
            val data = hashMapOf(
                "userid" to userid!!
            )

        return  callfunction("getProfile", data)

    }

    fun getPersons(type:String): Task<ArrayList<HashMap<String, String>>>{
        val data = hashMapOf(
            "type" to type
        )

        return  callfunction("getPersons", data)

    }

    fun getPersonsSubjects(): Task<ArrayList<HashMap<String, String>>>{
        val data = HashMap<String, String>()

        return  callfunction("getPersonsSubjects", data)

    }

    fun getSubjects() : Task<ArrayList<HashMap<String, String>>> {
        val data = HashMap<String,String>()

        return  callfunction("getSubjects", data)

    }

    fun getLesson() : Task<ArrayList<HashMap<String, String>>> {
        val data = HashMap<String,String>()

        return  callfunction("getLesson", data)

    }

    fun saveSubject(subject: Subject) : Task<String> {

        val data = hashMapOf(
            "subjectId" to subject.subjectId,
            "name" to subject.Name,
            "Description" to subject.Description,
            "teacherId" to (subject.Teacher.getuserId()?:" ")
        )

        return  callfunction("saveSubject", data)

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
            "userId" to person.getuserId()!!
        )
        return  callfunction("savePersonSubject", data)

    }

    fun saveGrade(grade: Grade) : Task<String> {

        val data:HashMap<String, String> = hashMapOf(
            "subjectId" to grade.subject.subjectId,
            "studentId" to grade.student.getuserId()!!,
            "teacherId" to grade.teacher.getuserId()!!,
            "grade" to grade.grade.toString(),
            "comment" to grade.comment,
            "date" to CurrentDate.getStringFromCalendar(grade.date),
            "gradeId" to grade.Id.toString()
        )

        return  callfunction("saveGrade", data)

    }

    fun getGrade(): Task<ArrayList<HashMap<String, String>>> {
        val data = HashMap<String, String>()

        return  callfunction("getGrades", data)

    }

    fun saveTalking(talking: Talking): Task<String> {

        val data: HashMap<String,String> = hashMapOf(
             "userId2" to talking.person2.getuserId()!!,
             "userId1" to talking.person1.getuserId()!!,
             "Id" to talking.Id.toString(),
             "lastMessage" to talking.getLastMessageDate()
        )

        return  callfunction("saveTalking", data)
    }

    fun getTalkings() :Task<ArrayList<HashMap<String, String>>> {
        val data = HashMap<String, String>()

        return  callfunction("getTalkings", data)


    }

    fun saveMessage(talking: Talking, message: Message): Task<String> {
        var target =talking.person1
        if(User.userId.equals(talking.person1.getuserId())) {
            target =talking.person2
        }

        val data: HashMap<String, String> = hashMapOf(
            "sender" to (message.sender.getuserId()?: ""),
            "talkingId" to talking.Id.toString(),
            "Id" to message.Id.toString(),
            "message" to message.message,
            "targetId" to (target.getuserId() ?: ""),
            "date" to message.getMessageTime()
        )

        println(target.getuserId())

        return  callfunction("saveMessage", data)

    }

    fun getMessages() :Task<ArrayList<HashMap<String, String>>> {
        val data = HashMap<String, String>()

        return  callfunction("getMessages", data)

    }

    fun getMessage(id: String):Task<HashMap<String, String>>{
        val data= hashMapOf<String,String>(
            "id" to id
        )
        return  callfunction("getMessage", data)
    }

    fun getGrade(id: String):Task<HashMap<String, String>>{
        val data= hashMapOf<String,String>(
            "id" to id
        )
        return  callfunction("getGrade", data)
    }

    fun <T> callfunction(name:String, data: HashMap<String,String>): Task<T>{
        return functions.getHttpsCallable(name)
            .call(data)
            .continueWith{task ->
                val result= task.result?.data as T
                result
            }
    }


    fun subscribe(){
        messaging.subscribeToTopic(User.userId)
    }

    fun unSubscribe(){
        messaging.unsubscribeFromTopic(User.userId)
    }



}