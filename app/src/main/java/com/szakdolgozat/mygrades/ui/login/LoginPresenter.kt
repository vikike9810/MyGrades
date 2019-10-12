package com.szakdolgozat.mygrades.ui.login

import android.app.Activity
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.szakdolgozat.mygrades.model.User
import java.lang.reflect.Constructor

class LoginPresenter(view: LoginView) {
    companion object{ var auth = FirebaseAuth.getInstance()}
    private lateinit var view: LoginView
    private lateinit var user: User
    private var userId : FirebaseUser?=null

    init {
        this.view=view
        user = User.getUser()
    }

    fun userLogin(email:String, passw:String){
        auth.signInWithEmailAndPassword(email, passw)
            .addOnCompleteListener(view as Activity) { task ->
            if (task.isSuccessful) {
                    userId = auth.currentUser
                    user.email=userId?.email
                    view.logInOK()

                } else {
                    view.logInFailed(task.exception?.message)
                }


            }
    }



}