package com.szakdolgozat.mygrades.base

interface BaseView {

    fun showLoading()
    fun hideLoading()
    fun showMessage(message: String)
}