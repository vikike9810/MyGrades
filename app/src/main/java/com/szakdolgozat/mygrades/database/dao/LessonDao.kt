package com.szakdolgozat.mygrades.database.dao

import androidx.room.*
import com.szakdolgozat.mygrades.model.LessonSubjectJoin

@Dao
interface LessonDao {

    @Query("Select * FROM lessons where subjectId= :id")
    fun getAllBySubject(id: String):List<LessonSubjectJoin>

    @Insert
    fun insertAll(vararg lesson: LessonSubjectJoin)

    @Delete
    fun delete(lesson: LessonSubjectJoin)

    @Update
    fun updateLesson(vararg lesson: LessonSubjectJoin)

    @Query("DELETE FROM lessons")
    fun deleteAll()

}