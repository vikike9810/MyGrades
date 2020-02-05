package com.szakdolgozat.mygrades.ui.splash

import com.google.firebase.auth.FirebaseAuth
import com.szakdolgozat.mygrades.database.DatabaseHandler
import com.szakdolgozat.mygrades.events.DatabaseReadDoneEvent
import com.szakdolgozat.mygrades.firebase.FirebaseAuthenticationHelper
import com.szakdolgozat.mygrades.internetconnection.InternetConnectionChecker
import com.szakdolgozat.mygrades.model.Student
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.model.UserType
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SplashPresenter(var view: SplashView?) {

    private lateinit var auth: FirebaseAuth

    init {

        DatabaseReadDoneEvent.event += {
            view?.splashDone()
        }

    }

    fun loadUser() {
        DatabaseHandler.getDataBase(view!!.getSplashActivity())
        if (InternetConnectionChecker.internetIsAvailable(view!!.getSplashActivity())) {
            auth = FirebaseAuth.getInstance()
            val currentUser = auth.currentUser

            if (currentUser == null) {
                view?.loginUser()

            } else {
                User.setUser(currentUser)
                FirebaseAuthenticationHelper.getProfile(this::errorInGetProfile)
            }
        } else {
            getOfflineDatas()
        }
    }

    fun errorInGetProfile(message: String){
        view?.downloadError(message)
    }


    fun getOfflineDatas() {
        User.type = UserType.Offline
        User.person = Student("Offline User", "offline")
        GlobalScope.launch {
            DatabaseHandler.getOfflineDatas()
        }
    }

    fun destroyView(){
        view=null
    }

}