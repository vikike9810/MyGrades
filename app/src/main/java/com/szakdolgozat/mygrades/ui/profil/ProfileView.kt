package com.szakdolgozat.mygrades.ui.profil

import android.app.Activity

interface ProfileView {

    fun dataSaveOK()
    fun dataSaveError(message : String)
    fun getFragmentActivity():Activity
    fun refreshAvatar()
}