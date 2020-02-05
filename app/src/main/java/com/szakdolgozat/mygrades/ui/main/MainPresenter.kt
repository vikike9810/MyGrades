package com.szakdolgozat.mygrades.ui.main

import android.graphics.Color
import com.alamkanak.weekview.WeekViewEvent
import com.szakdolgozat.mygrades.base.BasePresenter
import com.szakdolgozat.mygrades.database.DatabaseHandler
import com.szakdolgozat.mygrades.firebase.FirebaseAuthenticationHelper
import com.szakdolgozat.mygrades.model.Subject
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.model.UserType
import com.szakdolgozat.mygrades.ui.login.LoginPresenter
import java.util.*
import kotlin.collections.ArrayList

class MainPresenter(view: MainView): BasePresenter<MainView>(view) {

    var user : User? = null
    var events = ArrayList<WeekViewEvent>()

    fun getUser(){
            user=User
            view?.setUserOnDrawer()
    }

    fun UserLogOut(){
        FirebaseAuthenticationHelper.logOut()
        user?.LogOut()
        DatabaseHandler.clearOfflineDatas()
        view?.userLoggedOut()
    }

    fun getEvents(newYear: Int, newMonth: Int): List<WeekViewEvent> {
        val tempEvents = ArrayList<WeekViewEvent>()
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
                    for (event: WeekViewEvent in subject.Lessons) {
                        event.color = Color.parseColor("#1b5e20")
                        events.add(event)
                    }
                }
            }
        }
    }

    fun addSubjectbyUserType(){
        if(User.type.equals(UserType.Student)){
            view?.showAddNewSubjectFragment()
        }
        else{
            view?.showCreateNewSubjectFragment()
        }
    }

    fun refreshEvent(){
        events.clear()
        newEvent()
    }

}