package com.szakdolgozat.mygrades.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "lessons")
class LessonSubjectJoin (

    @ColumnInfo(name = "subjectId")
    var subjectId: String,

    @ColumnInfo(name = "lessonBegin")
    var lessonBegin: String,

    @ColumnInfo(name = "lessonEnd")
    var lessonEnd: String
)

{

companion object{
    var num=0
    }

    @PrimaryKey(autoGenerate = true)
    var id: Long=0

    var lessonId :Int

    init {
        lessonId= num
        num++
    }
}
