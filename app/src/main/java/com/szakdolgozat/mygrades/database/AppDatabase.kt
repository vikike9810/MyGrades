package com.szakdolgozat.mygrades.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.szakdolgozat.mygrades.database.dao.LessonDao
import com.szakdolgozat.mygrades.database.dao.SubjectDao
import com.szakdolgozat.mygrades.model.LessonSubjectJoin
import com.szakdolgozat.mygrades.model.Subject

@Database(
    entities = arrayOf(Subject::class, LessonSubjectJoin::class),
    version = 1
)

abstract class AppDatabase: RoomDatabase() {

    abstract fun subjectDao(): SubjectDao
    abstract fun LessonDao(): LessonDao


}