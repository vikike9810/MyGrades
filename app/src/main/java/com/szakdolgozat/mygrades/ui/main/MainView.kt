package com.szakdolgozat.mygrades.ui.main

import com.szakdolgozat.mygrades.base.BaseView
import com.szakdolgozat.mygrades.model.User

interface MainView: BaseView {

    fun setUserOnDrawer()

    fun userLoggedOut()

    fun showCreateNewSubjectFragment()

    fun showAddNewSubjectFragment()

}