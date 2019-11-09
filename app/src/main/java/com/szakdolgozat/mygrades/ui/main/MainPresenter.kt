package com.szakdolgozat.mygrades.ui.main

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import com.alamkanak.weekview.WeekViewEvent
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.database.DatabaseHandler
import com.szakdolgozat.mygrades.events.ImagePickedEvent.event
import com.szakdolgozat.mygrades.model.Subject
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
            view.setUserOnDrawer()
    }

    fun UserLogOut(){
        LoginPresenter.auth.signOut()
        user?.LogOut()
        DatabaseHandler.clearOfflineDatas()
        view.userLoggedOut()
    }

    fun getEvents(newYear: Int, newMonth: Int): List<WeekViewEvent> {
        var tempEvents = ArrayList<WeekViewEvent>()
        for(event in events){
            if(event.startTime[Calendar.MONTH]==newMonth){
                tempEvents.add(event)
            }
        }
        return tempEvents
    }

    fun newEvent(){
        if(User.person?.Subjects != null) {
            for (subject: Subject in User.person?.Subjects!!) {
                if(subject.Lessons != null) {
                    for (event: WeekViewEvent in subject.Lessons!!) {
                        event.setColor(Color.parseColor("#1b5e20"))
                        events.add(event)
                    }
                }
            }
        }
    }

    fun addSubjectbyUserType(){
        if(User.type.equals("Student")){
            view.showAddNewSubjectFragment()
        }
        else{
            view.showCreateNewSubjectFragment()
        }
    }

    fun refreshEvent(){
        events.clear()
        newEvent()
    }

}