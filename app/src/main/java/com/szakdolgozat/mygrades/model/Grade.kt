package com.szakdolgozat.mygrades.model

import java.util.*

class Grade(
    var grade: Int,
    var subject: Subject,
    var student: Student,
    var date: Calendar= Calendar.getInstance(),
    var teacher: Teacher,
    var comment :String="")
{
    companion object{
        var num=0
    }

    var Id: Int

    init{
       Diary.grades.add(this)
        Id= num
        num++
   }
}