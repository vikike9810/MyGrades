package com.szakdolgozat.mygrades.ui.talking

import com.szakdolgozat.mygrades.model.Chat
import com.szakdolgozat.mygrades.model.Message
import com.szakdolgozat.mygrades.model.Talking
import com.szakdolgozat.mygrades.model.User

class TalkingPresenter(var view: TalkingView, var talking: Talking) {

    fun newMessage(message: String) {
        var index=Chat.talkings.indexOf(talking)
        var newMessage: Message?=null

        if(User.person!=null) {
            newMessage=Message(message, User.person!!)
            Chat.talkings[index].messages.add(newMessage)
        }
        view.messageAdded(newMessage)
    }
}