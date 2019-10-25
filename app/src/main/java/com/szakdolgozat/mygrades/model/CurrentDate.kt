package com.szakdolgozat.mygrades.model

import java.util.*

object CurrentDate {

    fun getDay(): Int{
        return Calendar.getInstance().get(Calendar.DATE)
    }

    fun getMonth(): Int{
        return Calendar.getInstance().get(Calendar.MONTH)+1
    }

    fun getYear(): Int{
        return Calendar.getInstance().get(Calendar.YEAR)
    }

    fun formatTime(time: Int):String{
        if(time<10){
            return "0"+time.toString()
        }
        return time.toString()
    }
}