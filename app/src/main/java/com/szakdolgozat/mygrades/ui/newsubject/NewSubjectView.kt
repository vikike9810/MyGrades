package com.szakdolgozat.mygrades.ui.newsubject

import com.szakdolgozat.mygrades.ui.main.MainActivity

interface NewSubjectView {
    fun SubjectAdded()

    fun getMain_Activity(): MainActivity
}