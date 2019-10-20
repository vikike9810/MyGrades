package com.szakdolgozat.mygrades.ui.main

import com.szakdolgozat.mygrades.model.User

interface MainView {

    fun setUserOnDrawer(user: User)

    fun userLoggedOut()

}