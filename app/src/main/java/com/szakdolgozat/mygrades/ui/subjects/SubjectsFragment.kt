package com.szakdolgozat.mygrades.ui.subjects

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.model.Subject
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.recyclerview.adapter.SubjectsRecyclerViewAdapter
import com.szakdolgozat.mygrades.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_subjects.*


class SubjectsFragment: Fragment(), SubjectsView, SubjectsRecyclerViewAdapter.SubjectClickListener {

    lateinit var subjectsPresenter: SubjectsPresenter
    lateinit var mainActivity: MainActivity
    lateinit var subjectsRecyclerView: RecyclerView
    private lateinit var subjectRecyclerViewAdapter: SubjectsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subjectsPresenter= SubjectsPresenter(this)
        mainActivity= activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_subjects, container, false)
        subjectsRecyclerView=view.findViewById(R.id.Subject_list)
        initRecyclerView()
        return view
    }

    @SuppressLint("RestrictedApi")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(User.person!!.getuserId()!="offline") {
            add_subject_button.setOnClickListener {
                onClickAddSubject(it)
            }
        }
        else{
            add_subject_button.visibility=View.INVISIBLE
        }
    }

    fun onClickAddSubject(v: View){
        mainActivity.showNewSubjectFragment()
    }

    private fun initRecyclerView(){
        subjectsRecyclerView.layoutManager= LinearLayoutManager(this.mainActivity)
        subjectRecyclerViewAdapter = SubjectsRecyclerViewAdapter()
        subjectRecyclerViewAdapter.itemClickListener = this
        subjectsRecyclerView.adapter = subjectRecyclerViewAdapter
        subjectsPresenter.getUserSubject()
    }

    override fun showSubject(subjects: ArrayList<Subject>) {
        (subjectsRecyclerView.adapter as SubjectsRecyclerViewAdapter).addAll(subjects)
    }


    override fun onItemClick(subject: com.szakdolgozat.mygrades.model.Subject) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemLongClick(position: Int, view: View): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemTaked(subject: Subject) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}