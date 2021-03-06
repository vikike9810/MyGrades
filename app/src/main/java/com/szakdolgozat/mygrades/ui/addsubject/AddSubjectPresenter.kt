package com.szakdolgozat.mygrades.ui.addsubject

import com.szakdolgozat.mygrades.base.BasePresenter
import com.szakdolgozat.mygrades.database.DatabaseHandler
import com.szakdolgozat.mygrades.model.Diary
import com.szakdolgozat.mygrades.model.Subject
import com.szakdolgozat.mygrades.model.User

class AddSubjectPresenter(view: addSubjectView): BasePresenter<addSubjectView>(view) {

    var subjectsToShow = ArrayList<Subject>()

    fun takeSubject(subject: Subject){
        User.person?.Subjects?.add(subject)
        removeSubject(subject)
        saveTakenSubject(subject)
    }

    fun saveTakenSubject(subject:Subject){
        if(User.person!=null) {
            DatabaseHandler.savePersonsSubjects(User.person!!, subject, {subject -> subjectAdded(subject)},{message -> view?.subjectAddedError(message )})
        }
    }
    fun subjectAdded(subject: Subject){
        view?.subjectAdded(subject)
    }

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
        val newSubjects= ArrayList<Subject>()
        for (subject: Subject in subjectsToShow){
            if(subject.Name.toUpperCase().contains(name.toUpperCase())){
                newSubjects.add(subject)
            }
        }
        view?.refreshSubjects(newSubjects)
    }
}