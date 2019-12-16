package com.szakdolgozat.mygrades.events

import kotlinx.event.event

object GetMessageEvent {
    val event = event<String>()
}