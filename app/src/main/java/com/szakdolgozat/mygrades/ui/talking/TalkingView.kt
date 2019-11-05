package com.szakdolgozat.mygrades.ui.talking

import com.szakdolgozat.mygrades.model.Message

interface TalkingView {

    fun messageAdded(message: Message?)

    fun errorInSave(message: String)
}