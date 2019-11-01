package com.szakdolgozat.mygrades.ui.addsubject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.model.Subject
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.recyclerview.adapter.SubjectsRecyclerViewAdapter
import com.szakdolgozat.mygrades.ui.main.MainActivity
import kotlinx.android.synthetic.main.add_new_subject_fragment.*

class addSubjectFragment: Fragment(), addSubjectView, SubjectsRecyclerViewAdapter.SubjectClickListener {

    lateinit var addSubjectPresenter: addSubjectPresenter
    lateinit var mainActivity: MainActivity
    lateinit var add_subject_recycler: RecyclerView
    lateinit var add_subject_Adapter : SubjectsRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addSubjectPresenter= addSubjectPresenter(this)
        mainActivity= activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.add_new_subject_fragment, container, false)
        add_subject_recycler=view.findViewById(R.id.add_subject_recycler)
        initRecyclerView()
        view.findViewById<ImageButton>(R.id.button_search).setOnClickListener{
            onClickSearch(it)
        }
        return view
    }

    private fun initRecyclerView(){
        add_subject_recycler.layoutManager= LinearLayoutManager(this.mainActivity)
        add_subject_Adapter = SubjectsRecyclerViewAdapter()
        add_subject_Adapter.itemClickListener = this
        add_subject_Adapter.addAll(addSubjectPresenter.getSubjects())
        add_subject_recycler.adapter = add_subject_Adapter
        add_subject_Adapter.changeAddSubject(true)
    }

    fun onClickSearch(v: View){
        addSubjectPresenter.getSubjectFromSearch(new_sub_search.text.toString())
    }

    override fun refreshSubjects(subjects: ArrayList<Subject>) {
        add_subject_Adapter.addNewItems(subjects)
    }


    override fun onItemClick(subject: Subject) {
       Toast.makeText(activity,subject.Name, Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(position: Int, view: View): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemTaked(subject: Subject) {
       User.person?.Subjects?.add(subject)
        add_subject_Adapter.removeItem(subject)
        addSubjectPresenter.removeSubject(subject)
        mainActivity.refreshCalendar()
    }




}