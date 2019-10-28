package com.szakdolgozat.mygrades.model

import com.alamkanak.weekview.WeekViewEvent

class Subject {
    companion object{
        var num=0
        var lessId: Long =0

        fun getLessonId():Long{
            lessId++
            return lessId
        }
    }

    var Name: String=""
    var Lessons: ArrayList<WeekViewEvent>? = null
    var Id: String? =null
    var Teacher: Teacher? =null
    var Description=""

    constructor(name:String, teacher: Teacher){
        Id= generateId()
        num++
        Name=name
        Teacher=teacher
        Lessons= ArrayList<WeekViewEvent>()
        Diary.subjects.add(this)
    }

    constructor(name:String, teacher: Teacher, lessons: ArrayList<WeekViewEvent>){
        Id= generateId()
        Name=name
        Teacher=teacher
        Lessons=ArrayList<WeekViewEvent>()
        Lessons?.addAll(lessons)
        Diary.subjects.add(this)
    }

    private fun generateId():String{
        val ret= "Sub$num"
        num++
        return ret
    }

}