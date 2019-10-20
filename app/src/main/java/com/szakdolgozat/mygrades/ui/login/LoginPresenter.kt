package com.szakdolgozat.mygrades.ui.login

import android.app.Activity
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.szakdolgozat.mygrades.model.User
import java.lang.reflect.Constructor

class LoginPresenter(private var view: LoginView) {
    companion object{ var auth = FirebaseAuth.getInstance()}


    fun userLogin(email:String, passw:String){
        auth.signInWithEmailAndPassword(email, passw)
            .addOnCompleteListener(view as Activity) { task ->
            if (task.isSuccessful) {
                auth.currentUser?.let { User.setUser(it) }
                    view.logInOK()

                } else {
                    view.logInFailed(task.exception?.message)
                }


            }
    }



}