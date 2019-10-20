package com.szakdolgozat.mygrades.ui.main

import android.app.Activity
import android.content.Intent
import com.alamkanak.weekview.WeekViewEvent
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.ui.login.LoginActivity
import com.szakdolgozat.mygrades.ui.login.LoginPresenter
import java.util.*
import kotlin.collections.ArrayList

class MainPresenter(private var view: MainView) {

    var user : User? = null
    var events = ArrayList<WeekViewEvent>()

    fun getUser(){
            user=User
            view.setUserOnDrawer(user!!)
    }
    fun UserLogIn(){
        user?.LogIn()
        view.setUserOnDrawer(user!!)
    }
    fun UserLogOut(){
        LoginPresenter.auth.signOut()
        user?.LogOut()
        view.userLoggedOut()
    }

    fun getEvents(newYear: Int, newMonth: Int): List<WeekViewEvent> {
        var tempEvents = ArrayList<WeekViewEvent>()
        for(event in events){
            if(event.startTime.time.month==newMonth){
                tempEvents.add(event)
            }
        }
        return tempEvents
    }

    fun newEvent(newYear: Int, newMonth: Int){
        val startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 10)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        val endTime = startTime.clone() as Calendar
        endTime.add(Calendar.HOUR, 1)
        endTime.set(Calendar.MONTH, newMonth - 1)
        val event = WeekViewEvent(1, "Event1", startTime, endTime)
        events.add(event)
    }

}