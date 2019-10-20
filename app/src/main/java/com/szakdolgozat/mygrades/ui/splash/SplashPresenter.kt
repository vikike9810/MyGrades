package com.szakdolgozat.mygrades.ui.splash

import com.google.firebase.auth.FirebaseAuth
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.util.FirebaseFunctionHelper

class SplashPresenter(var view: SplashView) {

    private lateinit var auth: FirebaseAuth

    fun loadUser(){
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if(currentUser==null){
            view.loginUser()
        }
        else{
            User.setUser(currentUser)
            getProfile()
        }
    }

    fun getProfile(){
        FirebaseFunctionHelper.getProfile(auth.currentUser?.uid)
            .addOnCompleteListener { result ->

                if (result.isSuccessful && !(result?.result?.get("name").equals("Error")) ) {

                    User.Name=result.result?.get("name")
                    User.birthday=result.result?.get("birthday")
                    User.address.city=result.result?.get("city")
                    User.address.zip=Integer.parseInt(result.result?.get("zip"))
                    User.address.street=result.result?.get("street")
                    User.address.number=result.result?.get("number")
                    view.splashDone()
                } else {
                    view.splashDone()
                }
            }
    }

}