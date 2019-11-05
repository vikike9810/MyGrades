package com.szakdolgozat.mygrades.model

class Teacher: Person {


    constructor(name:String, id: String):super(name,id ){
        Diary.teachers.add(this)
    }

}