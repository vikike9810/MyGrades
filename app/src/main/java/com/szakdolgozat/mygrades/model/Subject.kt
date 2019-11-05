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

    @ColumnInfo(name = "Description")
    var Description=""

    constructor(id: Long){
        this.id=id
    }

    constructor(id:String, name:String, teacher: Teacher){
        subjectId=id
        Name=name
        Teacher=teacher
        Lessons= ArrayList<WeekViewEvent>()
        Diary.subjects.add(this)
    }

    constructor(name:String, teacher: Teacher){
        Name=name
        Teacher=teacher
        Lessons= ArrayList<WeekViewEvent>()
        Diary.subjects.add(this)
    }

    constructor(name:String, teacher: Teacher, lessons: ArrayList<WeekViewEvent>){
        Name=name
        Teacher=teacher
        Lessons=ArrayList<WeekViewEvent>()
        Lessons?.addAll(lessons)
        Diary.subjects.add(this)
    }

    private fun generateId():String{
        val ret= "Sub$num"
        num++
        return ret
    }

}