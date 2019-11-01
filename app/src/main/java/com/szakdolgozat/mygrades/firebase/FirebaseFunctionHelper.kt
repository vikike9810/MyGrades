package com.szakdolgozat.mygrades.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.functions.FirebaseFunctions
import com.szakdolgozat.mygrades.model.LessonSubjectJoin
import com.szakdolgozat.mygrades.model.Person
import com.szakdolgozat.mygrades.model.Subject
import com.szakdolgozat.mygrades.model.User
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

    fun saveLesson(lesson: LessonSubjectJoin, subject :Subject) : Task<String> {

        val data = hashMapOf(
            "subjectId" to subject.subjectId,
            "begin" to lesson.lessonBegin,
            "end" to lesson.lessonEnd,
            "id" to lesson.lessonId
        )

        return functions.getHttpsCallable("saveLesson")
            .call(data)
            .continueWith { task ->

                val result = task.result?.data as String
                result
            }
    }

    fun savePersonSubject(type: String, person: Person, subject :Subject) : Task<String> {

        val data = hashMapOf(
            "subjectId" to subject.subjectId,
            "userId" to person.getuserId(),
            "type" to type
        )

        return functions.getHttpsCallable("savePersonSubject")
            .call(data)
            .continueWith { task ->

                val result = task.result?.data as String
                result
            }
    }

}