package com.szakdolgozat.mygrades.ui.grades

import com.szakdolgozat.mygrades.base.BasePresenter
import com.szakdolgozat.mygrades.model.*

class GradesPresenter(view :GradesView): BasePresenter<GradesView>(view) {

    fun getSubjectList():ArrayList<String> {
       return User.getSubjectList()
    }

    fun getTeacherList():ArrayList<String> {
        return Diary.getTeacherList()
    }

    fun getStudentList():ArrayList<String> {
        return Diary.getStudentList()
    }

    fun getFilteredGrades(subject: String, teacher: String, grade: String, grades: MutableList<Grade>): ArrayList<Grade>{
        val filteredGrades= ArrayList<Grade>()
        filteredGrades.clear()
        filteredGrades.addAll(getGradesByUser())

        if(!(subject.equals(""))){
            val filteredBySubGrades=filteredListbySubject(subject, filteredGrades)
            filteredGrades.clear()
            filteredGrades.addAll(filteredBySubGrades)
        }
        if(!(teacher.equals(""))){
            var filteredByTeacherGrades =ArrayList<Grade>()
                if(User.type.equals(UserType.Teacher)) {
                    filteredByTeacherGrades = filteredListbyStudent(teacher, filteredGrades)
                }
                else{
                    filteredByTeacherGrades = filteredListbyTeacher(teacher, filteredGrades)
                }
            filteredGrades.clear()
            filteredGrades.addAll(filteredByTeacherGrades)
        }
        if(!(grade.equals(""))){
            val filteredByGradeGrades=filteredListbyGrade(grade, filteredGrades)
            filteredGrades.clear()
            filteredGrades.addAll(filteredByGradeGrades)
        }

        return filteredGrades
    }

    fun filteredListbySubject(subject: String,  grades: ArrayList<Grade>): ArrayList<Grade>{
        val filteredBySubjectGrades= ArrayList<Grade>()
        for(grade: Grade in grades){
            if(grade.subject.Name.equals(subject)){
                filteredBySubjectGrades.add(grade)
            }
        }
        return filteredBySubjectGrades
    }

    fun filteredListbyTeacher(teacher: String,  grades: ArrayList<Grade>): ArrayList<Grade>{
        val filteredByTeacherGrades= ArrayList<Grade>()
        for(grade: Grade in grades){
            if(grade.teacher.getName().equals(teacher)){
                filteredByTeacherGrades.add(grade)
            }
        }
        return filteredByTeacherGrades
    }

    fun filteredListbyStudent(student: String,  grades: ArrayList<Grade>): ArrayList<Grade>{
        val filteredByStudentGrades= ArrayList<Grade>()
        for(grade: Grade in grades){
            if(grade.student.getName().equals(student)){
                filteredByStudentGrades.add(grade)
            }
        }
        return filteredByStudentGrades
    }

    fun filteredListbyGrade(gradeNum: String,  grades: ArrayList<Grade>): ArrayList<Grade>{
        val filteredByGradeGrades= ArrayList<Grade>()
        for(grade: Grade in grades){
            if(grade.grade.toString().equals(gradeNum)){
                filteredByGradeGrades.add(grade)
            }
        }
        return filteredByGradeGrades
    }

    fun getGradesByUser(): ArrayList<Grade> {
        val grades = ArrayList<Grade>()
        for (grade: Grade in Diary.grades) {
            if (User.type.equals(UserType.Teacher)) {
                if (grade.teacher == User.person) {
                    grades.add(grade)
                }
            } else {
                if (grade.student == User.person) {
                    grades.add(grade)
                }
            }
        }
        return grades
    }
}