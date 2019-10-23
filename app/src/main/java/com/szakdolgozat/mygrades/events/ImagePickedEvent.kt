package com.szakdolgozat.mygrades.events

import kotlinx.event.event

object ImagePickedEvent {
    val event = event<String>()
}