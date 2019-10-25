package com.szakdolgozat.mygrades.ui.addsubject

import com.szakdolgozat.mygrades.model.Subject


interface addSubjectView {

    fun refreshSubjects(subjects: ArrayList<Subject>)
}