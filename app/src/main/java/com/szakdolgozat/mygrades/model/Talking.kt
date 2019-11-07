package com.szakdolgozat.mygrades.model

import java.util.*
import kotlin.collections.ArrayList

class Talking {
    lateinit var person1: Person
    lateinit var person2: Person
    var Id : Int
    var messages = ArrayList<Message>()

    companion object{
        var num=0
    }

    constructor( person1: Person, person2: Person, messages: ArrayList<Message> ){
        this.person1=person1
        this.person2=person2
        this.messages.addAll(messages)
        Id= num
        num++
        Chat.talkings.add(this)
    }

    constructor( id: Int, person1: Person, person2: Person ){
        this.person1=person1
        this.person2=person2
        Id= id
        setNum(id)
        Chat.talkings.add(this)
    }

    constructor( person1: Person, person2: Person){
        this.person1=person1
        this.person2=person2
        Id= num
        num++
        Chat.talkings.add(this)
    }

    constructor(person: Person){
        Id=0
        person1=person
        person2=person
    }

    private fun setNum(id: Int){
        if(num<=id){
            num=id
            num++
        }
    }

    fun getLastMessageDate():String{
        if(messages.size>0) {
            return messages[messages.size - 1].getMessageTime()
        }
        return ""
    }
}