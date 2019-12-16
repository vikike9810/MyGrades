package com.szakdolgozat.mygrades.ui.subjects

import com.szakdolgozat.mygrades.model.Subject
import com.szakdolgozat.mygrades.model.User


class SubjectsPresenter(var view: SubjectsView) {

    fun getUserSubject(){
        view.showSubject(User.getSubjects())
    }


}