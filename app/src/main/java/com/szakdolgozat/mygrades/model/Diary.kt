package com.szakdolgozat.mygrades.model

object Diary {
    var subjects : ArrayList<Subject> = ArrayList<Subject>()
    var teachers: ArrayList<Teacher> =ArrayList<Teacher>()
    var students: ArrayList<Student> =ArrayList<Student>()
    var grades: ArrayList<Grade> =ArrayList<Grade>()


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

    fun getSubjectById(id :String):Subject?{
        subjects.forEach {
            if(it.subjectId.equals(id)){
                return it
            }
        }
        return null
    }

    fun getStudentById(id :String):Student?{
        students.forEach {
            if(it.getuserId().equals(id)){
                return it
            }
        }
        return null
    }

    fun getTeacherById(id : String): Teacher?{
        teachers.forEach {
            if(it.getuserId().equals(id)){
                return it
            }
        }
        return null
    }

}