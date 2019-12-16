package com.szakdolgozat.mygrades.ui.signup

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity

interface SignUpView {

    fun getFragmentActivity(): FragmentActivity?
    fun signUpOk()
    fun signUpError(message: String)
}