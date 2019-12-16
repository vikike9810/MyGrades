package com.szakdolgozat.mygrades.events

import kotlinx.event.event

object DatabaseReadDoneEvent {
    val event= event<String>()
}