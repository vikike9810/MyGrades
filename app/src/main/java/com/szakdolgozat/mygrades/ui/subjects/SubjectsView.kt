package com.szakdolgozat.mygrades.ui.subjects

import com.szakdolgozat.mygrades.base.BaseView
import com.szakdolgozat.mygrades.model.Subject


interface SubjectsView :BaseView{

    fun showSubject(subjects: ArrayList<Subject>)

}