package com.szakdolgozat.mygrades.ui.login

interface LoginView {

    fun returnFromSignup()

    fun logInOK()

    fun logInFailed(message: String?)
}