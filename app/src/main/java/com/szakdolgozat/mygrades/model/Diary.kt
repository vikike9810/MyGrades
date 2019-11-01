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
    fun getPersonList():ArrayList<String> {
        var newperson = ArrayList<String>()
        newperson.addAll(getTeacherList())
        newperson.addAll(getStudentList())
        return newperson
    }


    fun getTeacherList():ArrayList<String> {
        var newteachers = ArrayList<String>()
        newteachers.add("")
        for (teacher: Teacher in teachers) {
            newteachers.add(teacher.getName())
        }
        return newteachers
    }

    fun getStudentList():ArrayList<String> {
        var newstudents = ArrayList<String>()
        newstudents.add("")
        for (student: Student in students) {
            newstudents.add(student.getName())
        }
        return newstudents
    }

    fun getSubjectList():ArrayList<String> {
        var newsubjects = ArrayList<String>()
        newsubjects.add("")
        for (subjects: Subject in subjects) {
            newsubjects.add(subjects.Name)
        }
        return newsubjects
    }

    fun getStudentListBySubjectName(name: String): ArrayList<String>{
        var newstudents = ArrayList<String>()
        for (student: Student in students) {
                for (subject: Subject in student.Subjects) {
                    if (subject.Name.equals(name)) {
                        newstudents.add(student.getName())
                        break
                }
            }
        }
        return newstudents
    }

    fun getSubjectByName(name:String): Subject?{
        for(subject: Subject in subjects){
            if(subject.Name.equals(name)){
                return subject
            }
        }
        return null
    }

    fun getStudentByName(name:String): Student?{
        for(student: Student in students){
            if(student.getName().equals(name)){
                return student
            }
        }
        return null
    }

}