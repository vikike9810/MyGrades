package com.szakdolgozat.mygrades.model

object User{
    var Name : String? = "Tesz Elek"
    var email :String? = "valami@teszt.hu"
    var avatar :String? = ""
    var loggedIn : Boolean=false

    fun getUser():User{
        loggedIn=true
        return this
    }

    fun LogIn(){
        loggedIn=true
    }
    fun LogOut(){
        loggedIn=false
    }
}