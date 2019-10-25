package com.szakdolgozat.mygrades.ui.addsubject

import com.szakdolgozat.mygrades.model.Diary
import com.szakdolgozat.mygrades.model.Subject
import com.szakdolgozat.mygrades.model.User

class addSubjectPresenter(var view: addSubjectView) {

    var subjectsToShow = ArrayList<Subject>()

    fun getSubjects() :ArrayList<Subject> {
        subjectsToShow.addAll(Diary.subjects)
        for (subject: Subject in Diary.subjects){
            if(User.person?.Subjects != null){
                    for (userSubject: Subject in User.person?.Subjects!!) {
                        if (userSubject.equals(subject)){
                            subjectsToShow.remove(userSubject)
                        }
                    }
                }
        }
        return subjectsToShow
    }

    fun removeSubject(subject: Subject){
        subjectsToShow.remove(subject)
    }

    fun getSubjectFromSearch(name: String){
        var newSubjects= ArrayList<Subject>()
        for (subject: Subject in subjectsToShow){
            if(subject.Name.toUpperCase().contains(name.toUpperCase())){
                newSubjects.add(subject)
            }
        }
        view.refreshSubjects(newSubjects)
    }
}