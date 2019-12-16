package com.szakdolgozat.mygrades.ui.grades

import com.szakdolgozat.mygrades.model.*

class GradesPresenter(var view :GradesView) {

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
        var filteredGrades= ArrayList<Grade>()
        filteredGrades.clear()
        filteredGrades.addAll(getGradesByUser())

        if(!(subject.equals(""))){
            var filteredBySubGrades=filteredListbySubject(subject, filteredGrades)
            filteredGrades.clear()
            filteredGrades.addAll(filteredBySubGrades)
        }
        if(!(teacher.equals(""))){
            var filteredByTeacherGrades =ArrayList<Grade>()
                if(User.type.equals("Teacher")) {
                    filteredByTeacherGrades = filteredListbyStudent(teacher, filteredGrades)
                }
                else{
                    filteredByTeacherGrades = filteredListbyTeacher(teacher, filteredGrades)
                }
            filteredGrades.clear()
            filteredGrades.addAll(filteredByTeacherGrades)
        }
        if(!(grade.equals(""))){
            var filteredByGradeGrades=filteredListbyGrade(grade, filteredGrades)
            filteredGrades.clear()
            filteredGrades.addAll(filteredByGradeGrades)
        }

        return filteredGrades
    }

    fun filteredListbySubject(subject: String,  grades: ArrayList<Grade>): ArrayList<Grade>{
        var filteredBySubjectGrades= ArrayList<Grade>()
        for(grade: Grade in grades){
            if(grade.subject.Name.equals(subject)){
                filteredBySubjectGrades.add(grade)
            }
        }
        return filteredBySubjectGrades
    }

    fun filteredListbyTeacher(teacher: String,  grades: ArrayList<Grade>): ArrayList<Grade>{
        var filteredByTeacherGrades= ArrayList<Grade>()
        for(grade: Grade in grades){
            if(grade.teacher.getName().equals(teacher)){
                filteredByTeacherGrades.add(grade)
            }
        }
        return filteredByTeacherGrades
    }

    fun filteredListbyStudent(student: String,  grades: ArrayList<Grade>): ArrayList<Grade>{
        var filteredByStudentGrades= ArrayList<Grade>()
        for(grade: Grade in grades){
            if(grade.student.getName().equals(student)){
                filteredByStudentGrades.add(grade)
            }
        }
        return filteredByStudentGrades
    }

    fun filteredListbyGrade(gradeNum: String,  grades: ArrayList<Grade>): ArrayList<Grade>{
        var filteredByGradeGrades= ArrayList<Grade>()
        for(grade: Grade in grades){
            if(grade.grade.toString().equals(gradeNum)){
                filteredByGradeGrades.add(grade)
            }
        }
        return filteredByGradeGrades
    }

    fun getGradesByUser(): ArrayList<Grade> {
        var grades = ArrayList<Grade>()
        for (grade: Grade in Diary.grades) {
            if (User.type.equals("Teacher")) {
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