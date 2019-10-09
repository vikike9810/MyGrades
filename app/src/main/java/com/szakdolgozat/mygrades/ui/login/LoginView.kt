package com.szakdolgozat.mygrades.ui.login

interface LoginView {

    fun logInOK()

    fun logInFailed(message: String?)
}