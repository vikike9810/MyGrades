package com.szakdolgozat.mygrades.ui.addgrade

interface AddGradeView {
    fun gradeAdded()
    fun errorInSave(message: String)
}