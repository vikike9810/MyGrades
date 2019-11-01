package com.szakdolgozat.mygrades.model

import java.util.*
import kotlin.collections.ArrayList

class Talking {
    lateinit var person1: Person
    lateinit var person2: Person
    var messages = ArrayList<Message>()

    constructor( person1: Person, person2: Person, messages: ArrayList<Message> ){
        this.person1=person1
        this.person2=person2
        this.messages.addAll(messages)
    }

    constructor( person1: Person, person2: Person){
        this.person1=person1
        this.person2=person2
    }

    fun getLastMessageDate():String{
        if(messages.size>0) {
            return messages[messages.size - 1].getMessageTime()
        }
        return ""
    }
}