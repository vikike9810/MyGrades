package com.szakdolgozat.mygrades.util

import com.google.android.gms.tasks.Task
import com.google.firebase.functions.FirebaseFunctions
import com.szakdolgozat.mygrades.model.User
import java.util.*
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

}