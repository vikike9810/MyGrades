package com.szakdolgozat.mygrades.ui.newsubject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.ui.main.MainActivity

class NewSubjectFragment: Fragment(), NewSubjectView {
    lateinit var newSubjectPresenter: NewSubjectPresenter
    lateinit var mainActivity: MainActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newSubjectPresenter= NewSubjectPresenter(this)
        mainActivity= activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.create_new_subject_fragment, container, false)
        return view
    }
}