package com.szakdolgozat.mygrades.ui.main

import com.alamkanak.weekview.WeekViewEvent
import com.szakdolgozat.mygrades.model.User
import java.util.*
import kotlin.collections.ArrayList

class MainPresenter(private var view: MainView) {

    var user : User? = null
    var events = ArrayList<WeekViewEvent>()

        fun getUser(){
            user=User("Tesz Elek", "valami@teszt.hu")
            view.setUserOnDrawer(user!!)
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