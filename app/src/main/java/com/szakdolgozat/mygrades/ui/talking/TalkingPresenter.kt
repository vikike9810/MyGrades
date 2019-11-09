package com.szakdolgozat.mygrades.ui.talking

import com.szakdolgozat.mygrades.database.DatabaseHandler
import com.szakdolgozat.mygrades.model.Chat
import com.szakdolgozat.mygrades.model.Message
import com.szakdolgozat.mygrades.model.Talking
import com.szakdolgozat.mygrades.model.User

class TalkingPresenter(var view: TalkingView, var talking: Talking) {

    fun newMessage(message: String) {
        var index=Chat.talkings.indexOf(talking)
        var newMessage: Message?=null

        if(User.person!=null) {
            var talking=Chat.talkings[index]
            newMessage=Message(message, User.person!!)
            talking.messages.add(newMessage)
            DatabaseHandler.saveMessages(talking, newMessage, {newMessage ->  view.messageAdded(newMessage)},{message -> view.errorInSave(message)})
        }
        else{
            view.errorInSave("Error on save!")
        }
    }



}