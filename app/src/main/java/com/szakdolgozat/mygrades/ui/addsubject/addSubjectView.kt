package com.szakdolgozat.mygrades.ui.addsubject

import com.szakdolgozat.mygrades.model.Message
import com.szakdolgozat.mygrades.model.Subject


interface addSubjectView {

    fun subjectAdded(subject: Subject)

    fun subjectAddedError(message: String)

    fun refreshSubjects(subjects: ArrayList<Subject>)
}