package com.szakdolgozat.mygrades.ui.profil

import android.app.Activity
import com.szakdolgozat.mygrades.base.BaseView

interface ProfileView: BaseView {

    fun dataSaveOK()
    fun getFragmentActivity():Activity
    fun refreshAvatar()
}