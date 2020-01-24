package com.szakdolgozat.mygrades.ui.login

import android.content.Context
import com.szakdolgozat.mygrades.base.BaseView

interface LoginView: BaseView {

    fun returnFromSignup()

    fun logInOK()

    fun logInFailed(message: String?)

    fun getContext(): Context
}