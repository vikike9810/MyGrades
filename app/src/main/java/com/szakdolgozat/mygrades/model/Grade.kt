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
   init{
       Diary.grades.add(this)
   }
}