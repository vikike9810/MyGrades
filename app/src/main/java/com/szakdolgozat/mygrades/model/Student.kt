package com.szakdolgozat.mygrades.model

class Student: Person {

    constructor(name:String, id: String):super(name,id ){
        Diary.students.add(this)
    }

}