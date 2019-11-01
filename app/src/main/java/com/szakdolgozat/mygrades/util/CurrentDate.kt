package com.szakdolgozat.mygrades.util

import com.alamkanak.weekview.WeekViewEvent
import com.szakdolgozat.mygrades.model.LessonDate
import java.text.SimpleDateFormat
import java.time.Month
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

    fun getHour(): Int{
        return Calendar.getInstance().get(Calendar.HOUR)
    }

    fun getMinute(): Int{
        return Calendar.getInstance().get(Calendar.MINUTE)
    }

    fun formatTime(time: Int):String{
        if(time<10){
            return "0"+time.toString()
        }
        return time.toString()
    }

    fun getCalenderFromString(date: String): Calendar{
        val sdf = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ENGLISH)
        var calen= Calendar.getInstance()
        calen.time=sdf.parse(date)
        //calen.add(Calendar.MONTH,-1)
        return calen
    }

    fun getCalederFromDateString(date: String): Calendar{
        val sdf = SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH)
        var calen= Calendar.getInstance()
        calen.time=sdf.parse(date)
        //calen.add(Calendar.MONTH,-1)
        return calen.clone() as Calendar
    }

    fun getStringFromCalendar(calendar: Calendar): String{
        return calendar[Calendar.YEAR].toString()+"."+ formatTime(calendar[Calendar.MONTH]+1)+"."+ formatTime(calendar[Calendar.DATE])
    }

    fun dayBetween(first: Calendar, last: Calendar):Long{
      var between=last.timeInMillis-first.timeInMillis
      return between/86400
    }

    fun getNextDayEvent( calendar: Calendar, lesson: LessonDate, name: String, id:Long): WeekViewEvent{
        var newfrom=Calendar.getInstance()
        var newto=Calendar.getInstance()
        var numberOfDay= lesson.dayOftheWeek+1
        var date=calendar.clone() as Calendar


        if(date[Calendar.DAY_OF_WEEK]<numberOfDay){
                date.add(Calendar.DAY_OF_YEAR,numberOfDay-date[Calendar.DAY_OF_WEEK])
        }
        else if(date[Calendar.DAY_OF_WEEK]>numberOfDay){
                date.add(Calendar.DAY_OF_YEAR,(7-date[Calendar.DAY_OF_WEEK])+numberOfDay)
        }

        newfrom.set(date[Calendar.YEAR], date[Calendar.MONTH], date[Calendar.DATE], getHourfromString(lesson.From), getMinfromString(lesson.From))
        newto.set(date[Calendar.YEAR], date[Calendar.MONTH], date[Calendar.DATE], getHourfromString(lesson.To), getMinfromString(lesson.To))

        return WeekViewEvent(id,name,newfrom.clone()  as Calendar,newto.clone() as Calendar)
    }

    fun getHourfromString(time: String):Int{
        return Integer.parseInt(time.split(":")[0])
    }

    fun getMinfromString(time: String):Int{
        return Integer.parseInt(time.split(":")[1])
    }
}