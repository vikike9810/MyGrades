package com.szakdolgozat.mygrades.database

import com.alamkanak.weekview.WeekViewEvent
import com.szakdolgozat.mygrades.model.*
import com.szakdolgozat.mygrades.util.CurrentDate

object DatabaseHandler {

    fun getDatas(){
        getPersons()
        var lessons=ArrayList<WeekViewEvent>()
        var teacher= Teacher("Gajdos Sándor", "423h7d")
        lessons.add(
            WeekViewEvent(3,"Adatbázisok",
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay()-1,
                8,0,
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay()-1,
                10,0)
        )
        lessons.add(
            WeekViewEvent(4,"Adatbázisok2",
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay()+1,
                12,0,
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay()+1,
                14,0)
        )

        var lessons2=ArrayList<WeekViewEvent>()
        var teacher2= Teacher("Szirmay Geci", "423h7d")
        lessons2.add(
            WeekViewEvent(5,"Grafika",
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay(),
                10,0,
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay(),
                12,0)
        )

        var subject1 = Subject("Adatbázisok",teacher,lessons)
        var subject2 = Subject("Grafika",teacher2,lessons2)
    }

    fun getPersons(){
        getCurrentUsersDatas()
    }

    fun getCurrentUsersDatas(){
        if(User.type.equals("Student")){
            getStudentDatas()
        }
        else{
            getTeacherDatas()
        }
    }

    private fun getTeacherDatas() {
        User.person = Teacher(User.Name ?:"Jipsz Jakab", User.userId ?:"uid")
        var lessons=ArrayList<WeekViewEvent>()
        lessons.add(
            WeekViewEvent(2,"Ujtargy",
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay(),
                15,0,
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay(),
                16,0)
        )
        //1 subject
        var subject = Subject("Ujtargy", User.person as Teacher,lessons)
        User.person?.Subjects?.add(subject)
    }

    private fun getStudentDatas() {
        User.person = Student(User.Name ?:"Jipsz Jakab", User.userId ?:"uid")
        var teacher= Teacher("Tanar Pelda", "42342gfd")
        var lessons=ArrayList<WeekViewEvent>()
        lessons.add(
            WeekViewEvent(2,"Ujtargy",
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay(),
                15,0,
                CurrentDate.getYear(), CurrentDate.getMonth(), CurrentDate.getDay(),
                16,0)
        )
        //1 subject
        var subject = Subject("Ujtargy",teacher,lessons)
        User.person?.Subjects?.add(subject)
    }
}