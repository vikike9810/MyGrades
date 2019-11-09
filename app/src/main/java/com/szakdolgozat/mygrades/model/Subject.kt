package com.szakdolgozat.mygrades.model

import androidx.room.*
import com.alamkanak.weekview.WeekViewEvent

@Entity(tableName = "subject")
class Subject {
    companion object{
        var num=0
        var lessId: Long =0

        fun getLessonId():Long{
            lessId++
            return lessId
        }
    }

    @PrimaryKey(autoGenerate = true)
    var id: Long=0


    @ColumnInfo(name = "Name")
    var Name: String=""

    @Ignore
    var Lessons =ArrayList<WeekViewEvent>()

    @ColumnInfo(name = "subjectId")
    var subjectId: String=generateId()

    @Ignore
    lateinit var Teacher: Teacher

    @ColumnInfo(name="teacherName")
    lateinit var TeacherName: String

    @ColumnInfo(name = "Description")
    var Description=""

    constructor(id: Long){
        this.id=id
    }

    constructor(id:String, name:String, teacher: Teacher){
        subjectId=id
        setNum(id)
        Name=name
        Teacher=teacher
        TeacherName=teacher.getName()
        Lessons= ArrayList<WeekViewEvent>()
        Diary.subjects.add(this)
    }

    constructor(name:String, teacher: Teacher){
        Name=name
        Teacher=teacher
        TeacherName=teacher.getName()
        Lessons= ArrayList<WeekViewEvent>()
        Diary.subjects.add(this)
    }

    constructor(name:String, teacher: Teacher, lessons: ArrayList<WeekViewEvent>){
        Name=name
        Teacher=teacher
        TeacherName=teacher.getName()
        Lessons=ArrayList<WeekViewEvent>()
        Lessons?.addAll(lessons)
        Diary.subjects.add(this)
    }

    private fun setNum(id: String){
        val intId=Integer.parseInt(id.substring(3))
        if(num<=intId){
            num=intId
            num++
        }
    }

    private fun generateId():String{
        val ret= "Sub$num"
        num++
        return ret
    }

    object SubjectComparator :Comparator<Subject>{
        override fun compare(o1: Subject, o2: Subject): Int {
            return o1.Name.compareTo(o2.Name)
        }

    }

}