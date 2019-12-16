package com.szakdolgozat.mygrades.ui.newtalking

import com.szakdolgozat.mygrades.database.DatabaseHandler
import com.szakdolgozat.mygrades.model.Chat
import com.szakdolgozat.mygrades.model.Talking
import com.szakdolgozat.mygrades.model.User

class NewTalkingPresenter(var view : NewTalkingView) {

    fun addTalking(talking: Talking) {
            if(User.person!=null) {
                val newtalking=Talking(talking.person1, User.person!!)
                DatabaseHandler.saveTalkings(newtalking, { view.talkingAdded() }, { message -> view.Error(message) })
            }
        else{
                view.Error("No current User")
            }
    }

}