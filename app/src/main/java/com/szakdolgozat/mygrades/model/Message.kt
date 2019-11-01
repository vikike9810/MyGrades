package com.szakdolgozat.mygrades.model

import java.util.*

class Message( var message: String,
               var sender :Person) {
    var sendDate: Calendar = Calendar.getInstance().clone() as Calendar

    fun getMessageTime(): String{
        return sendDate[Calendar.YEAR].toString()+"."+ (sendDate[Calendar.MONTH]+1).toString()+"."+sendDate[Calendar.DATE].toString()+" "+sendDate[Calendar.HOUR_OF_DAY].toString()+":"+ sendDate[Calendar.MINUTE].toString()
    }
}