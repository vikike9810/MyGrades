package com.szakdolgozat.mygrades.model

import com.szakdolgozat.mygrades.util.CurrentDate
import java.util.*

class Message {
   lateinit var message: String
    lateinit var sender :Person

    var sendDate: Calendar = Calendar.getInstance().clone() as Calendar

    var Id: Int

     companion object{
         var num=0
     }

    constructor(message: String, sender: Person){
        this.message=message
        this.sender=sender
        Id=num
        num++
    }

    constructor(id :Int, message: String, sender: Person, date: Calendar){
        this.Id=id
        this.message=message
        this.sender=sender
        this.sendDate=date.clone() as Calendar
    }


    fun getMessageTime(): String{
        return CurrentDate.getTimeStringFromCalendar(sendDate)
    }
}