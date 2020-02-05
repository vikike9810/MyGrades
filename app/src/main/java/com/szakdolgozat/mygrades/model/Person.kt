package com.szakdolgozat.mygrades.model

abstract class Person(name: String, id: String) {

    private var Name : String?= name

    private var UserId: String= id

    var Subjects= ArrayList<Subject>()

    fun getName(): String{
        return this.Name?: ""
    }

    fun getuserId(): String {
        return this.UserId
    }

}