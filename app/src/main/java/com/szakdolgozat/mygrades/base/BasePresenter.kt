package com.szakdolgozat.mygrades.base

abstract class BasePresenter<T: BaseView>(protected var view : T?) {

    fun destroyView(){
        view=null
    }
    
}