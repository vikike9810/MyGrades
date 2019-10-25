package com.szakdolgozat.mygrades.model

abstract class Person {

    private var Name : String?=null
    private var UserId: String?=null
    var Subjects: ArrayList<Subject>? =null

    constructor(name: String, id: String){
        this.Name=name
        this.UserId=id
        Subjects=ArrayList<Subject>()
    }
    constructor(){
        Subjects= ArrayList<Subject>()
    }

    fun getName(): String{
        return this.Name?: ""
    }

    fun getuserId(): String? {
        return this.UserId
    }

    fun setName(newName: String){
        this.Name=newName
    }

    fun setUserId(uId: String){
        this.UserId=uId
    }
}