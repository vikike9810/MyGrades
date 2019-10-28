package com.szakdolgozat.mygrades.model

object Address{
    var city: String?=null
    var zip: String?=null
    var street: String?=null
    var number: String?=null

    fun clearAddres(){
        city=null
        zip=null
        street=null
        number=null
    }
}