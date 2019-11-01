package com.szakdolgozat.mygrades.ui.addgrade

import com.szakdolgozat.mygrades.model.Diary
import com.szakdolgozat.mygrades.model.Grade
import com.szakdolgozat.mygrades.model.Teacher
import com.szakdolgozat.mygrades.model.User
import java.util.*
import kotlin.collections.ArrayList

class AddGradePresenter(var view: AddGradeView) {

    fun  getSubjectList(): ArrayList<String>{
            return User.getSubjectList()
    }

    fun getStudentListBySubjectName(subjectName :String): ArrayList<String> {
        return Diary.getStudentListBySubjectName(subjectName)
    }

    fun addGrade(subject: String, student: String, grade: String, comment:String){
        var student=Diary.getStudentByName(student)
        var subject= Diary.getSubjectByName(subject)
        if(student!=null && student!=null) {
            Grade(Integer.parseInt(grade), subject!!, student, Calendar.getInstance().clone() as Calendar, User.person as Teacher, comment)
        }
        view.gradeAdded()
    }

}