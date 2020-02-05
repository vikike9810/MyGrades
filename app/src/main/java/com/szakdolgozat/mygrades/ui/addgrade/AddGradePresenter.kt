package com.szakdolgozat.mygrades.ui.addgrade

import com.szakdolgozat.mygrades.base.BasePresenter
import com.szakdolgozat.mygrades.database.DatabaseHandler
import com.szakdolgozat.mygrades.model.Diary
import com.szakdolgozat.mygrades.model.Grade
import com.szakdolgozat.mygrades.model.Teacher
import com.szakdolgozat.mygrades.model.User
import java.util.*

class AddGradePresenter(view: AddGradeView) : BasePresenter<AddGradeView>(view) {

    fun  getSubjectList(): ArrayList<String>{
            return User.getSubjectList()
    }

    fun getStudentListBySubjectName(subjectName :String): ArrayList<String> {
        return Diary.getStudentListBySubjectName(subjectName)
    }

    fun addGrade(subject: String, student: String, grade: String, comment:String){
        val student=Diary.getStudentByName(student)
        val subject= Diary.getSubjectByName(subject)
        if(student!=null && subject!=null) {
            val grade=Grade(Integer.parseInt(grade), subject, student, Calendar.getInstance().clone() as Calendar, User.person as Teacher, comment)
            DatabaseHandler.saveGrades(grade,{view?.gradeAdded()},{message -> view?.errorInSave(message)})
        }
        else{
            view?.errorInSave("Error in Save")
        }
    }

}