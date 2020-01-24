package com.szakdolgozat.mygrades.ui.login

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.base.BasePresenter
import com.szakdolgozat.mygrades.database.DatabaseHandler
import com.szakdolgozat.mygrades.events.DatabaseReadDoneEvent
import com.szakdolgozat.mygrades.firebase.FirebaseAuthenticationHelper
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.firebase.FirebaseFunctionHelper
import com.szakdolgozat.mygrades.firebase.FirebaseStorageProvider
import com.szakdolgozat.mygrades.model.Student
import com.szakdolgozat.mygrades.model.Teacher
import com.szakdolgozat.mygrades.model.UserType
import com.szakdolgozat.mygrades.util.ImageProvider
import java.io.File

class LoginPresenter(view: LoginView) : BasePresenter<LoginView>(view) {


    init {
        DatabaseReadDoneEvent.event += {
            view.logInOK()
        }
    }

    fun userLogin(email: String, passw: String) {
        User.clearUser()
        FirebaseAuthenticationHelper.userLogin(email, passw, this::loginReturned)
    }

    fun loginReturned(task: Task<AuthResult>) {
        if (task.isSuccessful) {
            if (FirebaseAuthenticationHelper.auth.currentUser != null) {
                User.setUser(FirebaseAuthenticationHelper.auth.currentUser!!)
                FirebaseAuthenticationHelper.getProfile(this::errorInGetProfile)
            } else {
                view?.logInFailed(view?.getContext()?.getString(R.string.login_failed))
            }
        } else {
            view?.logInFailed(task.exception?.message)
        }
    }

    fun errorInGetProfile(message: String){
        view?.logInFailed(message)
    }




}