package com.szakdolgozat.mygrades.ui.signup

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.szakdolgozat.mygrades.base.BaseView

interface SignUpView: BaseView {

    fun getFragmentActivity(): FragmentActivity?
    fun signUpOk()
    fun signUpError(message: String)
}