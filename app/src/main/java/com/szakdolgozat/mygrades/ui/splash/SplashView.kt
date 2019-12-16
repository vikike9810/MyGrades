package com.szakdolgozat.mygrades.ui.splash

import android.app.Activity

interface SplashView {

    fun splashDone()

    fun loginUser()

    fun downloadError(message: String)

    fun getSplashActivity(): Activity

}