package com.szakdolgozat.mygrades.model

import android.graphics.Bitmap
import android.net.Uri
import com.alamkanak.weekview.WeekViewEvent
import com.google.firebase.auth.FirebaseUser
import com.szakdolgozat.mygrades.firebase.FirebaseFunctionHelper
import java.util.*
import kotlin.collections.ArrayList

object User{
    var Name : String? = ""
    var email :String? = ""
    var avatar : Bitmap? = null
    var avatarPath : Uri?= null
    var loggedIn : Boolean=false
    var userId: String=""
    var address:Address=Address
    var type: String? =""
    var birthday: String?=""
    var person: Person?=null


    fun setUser(user: FirebaseUser){
        userId=user.uid
        email=user.email
        FirebaseFunctionHelper.subscribe()
        loggedIn=true
    }


    fun LogOut(){
        loggedIn=false
        FirebaseFunctionHelper.unSubscribe()
    }

    fun clearUser(){
        Name=""
        email=""
        avatar=null
        avatarPath=null
        loggedIn=false
        userId=""
        address.clearAddres()
        type=""
        birthday=""
        person=null
    }

    fun getSubjectList():ArrayList<String> {
        var subjects = ArrayList<String>()
        subjects.add("")
        if (person?.Subjects != null) {
            for (subject: Subject in person!!.Subjects) {
                subjects.add(subject.Name)
            }
        }
        return subjects
    }

}
