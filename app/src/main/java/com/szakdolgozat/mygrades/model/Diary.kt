package com.szakdolgozat.mygrades.model

object Diary {
    var subjects : ArrayList<Subject> = ArrayList<Subject>()
    var teachers: ArrayList<Teacher> =ArrayList<Teacher>()
    var students: ArrayList<Student> =ArrayList<Student>()
    var grades: ArrayList<Grade> =ArrayList<Grade>()

    fun findTeacherbyPerson(person:Person?):Teacher?{
        for(teacher: Teacher in teachers){
            if(teacher.getuserId().equals(person?.getuserId())){
                return teacher
            }
        }
        return null
    }

    fun findStudentbyPerson(person:Person?):Student?{
        for(student: Student in students){
            if(student.getuserId().equals(person?.getuserId())){
                return student
            }
        }
        return null
    }
}