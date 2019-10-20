package com.szakdolgozat.mygrades.model

import com.google.firebase.auth.FirebaseUser
import java.util.*

object User{
    var Name : String? = "Tesz Elek"
    var email :String? = "valami@teszt.hu"
    var avatar :String? = ""
    var loggedIn : Boolean=false
    var userId: String?=null
    var address:Address=Address
    var type: String? ="Student"
    var birthday: String?=null



    fun setUser(user: FirebaseUser){
        userId=user.uid
        email=user.email
        loggedIn=true
    }

    fun LogIn(){
        loggedIn=true
    }
    fun LogOut(){
        loggedIn=false
    }

}

object Address{
    var city: String?=null
    var zip: Int?=null
    var street: String?=null
    var number: String?=null
}