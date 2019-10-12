package com.szakdolgozat.mygrades.util

import com.google.android.gms.tasks.Task
import com.google.firebase.functions.FirebaseFunctions

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

}