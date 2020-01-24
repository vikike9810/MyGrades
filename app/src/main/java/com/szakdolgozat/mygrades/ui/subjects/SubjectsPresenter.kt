package com.szakdolgozat.mygrades.ui.subjects

import com.szakdolgozat.mygrades.base.BasePresenter
import com.szakdolgozat.mygrades.model.User


class SubjectsPresenter(view: SubjectsView):BasePresenter<SubjectsView>(view) {

    fun getUserSubject(){
        view?.showSubject(User.getSubjects())
    }


}