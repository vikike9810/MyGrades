package com.szakdolgozat.mygrades.ui.addgrade

import com.szakdolgozat.mygrades.base.BaseView

interface AddGradeView: BaseView {
    fun gradeAdded()
    fun errorInSave(message: String)
}