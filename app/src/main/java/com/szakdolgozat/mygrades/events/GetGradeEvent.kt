package com.szakdolgozat.mygrades.events

import kotlinx.event.event

object GetGradeEvent {
    val event =event<String>()
}