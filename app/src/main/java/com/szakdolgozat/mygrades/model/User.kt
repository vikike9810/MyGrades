package com.szakdolgozat.mygrades.model

import android.graphics.Bitmap
import android.net.Uri
import com.alamkanak.weekview.WeekViewEvent
import com.google.firebase.auth.FirebaseUser
import java.util.*
import kotlin.collections.ArrayList

object User{
    var Name : String? = ""
    var email :String? = ""
    var avatar : Bitmap? = null
    var avatarPath : Uri?= null
    var loggedIn : Boolean=false
    var userId: String?=""
    var address:Address=Address
    var type: String? =""
    var birthday: String?=""
    var person: Person?=null


    fun setUser(user: FirebaseUser){
        userId=user.uid
        email=user.email
        loggedIn=true
    }


    fun LogOut(){
        loggedIn=false
    }

}
