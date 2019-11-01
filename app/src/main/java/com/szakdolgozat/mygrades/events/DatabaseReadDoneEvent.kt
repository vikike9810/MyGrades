package com.szakdolgozat.mygrades.events

import kotlinx.event.event

object DatabaseReadDoneEvent {
    val event= kotlinx.event.event<String>()
}