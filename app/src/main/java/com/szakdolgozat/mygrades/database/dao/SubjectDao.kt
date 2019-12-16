package com.szakdolgozat.mygrades.database.dao

import androidx.room.*
import com.szakdolgozat.mygrades.model.Subject

@Dao
interface SubjectDao {

    @Query("Select * FROM subject")
    fun getAll():List<Subject>

    @Insert
    fun insertAll(vararg subject: Subject)

    @Delete
    fun delete(subject: Subject)

    @Update
    fun updateSubject(vararg subject: Subject)

    @Query("DELETE FROM subject")
    fun deleteAll()

}