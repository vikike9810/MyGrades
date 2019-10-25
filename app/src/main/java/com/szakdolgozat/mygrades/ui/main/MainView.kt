package com.szakdolgozat.mygrades.ui.main

import com.szakdolgozat.mygrades.model.User

interface MainView {

    fun setUserOnDrawer()

    fun userLoggedOut()

    fun showCreateNewSubjectFragment()

    fun showAddNewSubjectFragment()

}