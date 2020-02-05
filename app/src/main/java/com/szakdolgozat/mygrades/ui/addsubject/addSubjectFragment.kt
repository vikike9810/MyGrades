package com.szakdolgozat.mygrades.ui.addsubject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.base.BaseFragment
import com.szakdolgozat.mygrades.model.Subject
import com.szakdolgozat.mygrades.recyclerview.adapter.SubjectsRecyclerViewAdapter
import com.szakdolgozat.mygrades.ui.main.MainActivity
import kotlinx.android.synthetic.main.add_new_subject_fragment.*

class addSubjectFragment: BaseFragment<AddSubjectPresenter, addSubjectView>(), addSubjectView, SubjectsRecyclerViewAdapter.SubjectClickListener {

    lateinit var mainActivity: MainActivity
    lateinit var add_subject_recycler: RecyclerView
    lateinit var add_subject_Adapter : SubjectsRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    override fun createPresenter(): AddSubjectPresenter {
        return AddSubjectPresenter(this)
    }

    private fun initRecyclerView(){
        add_subject_recycler.layoutManager= LinearLayoutManager(this.mainActivity)
        add_subject_Adapter = SubjectsRecyclerViewAdapter()
        add_subject_Adapter.itemClickListener = this
        add_subject_Adapter.addAll(presenter!!.getSubjects())
        add_subject_recycler.adapter = add_subject_Adapter
        add_subject_Adapter.changeAddSubject(true)
    }

    fun onClickSearch(v: View){
        showLoading()
        presenter?.getSubjectFromSearch(new_sub_search.text.toString())
    }

    override fun refreshSubjects(subjects: ArrayList<Subject>) {
        hideLoading()
        add_subject_Adapter.addNewItems(subjects)
    }


    override fun onItemClick(subject: Subject) {
       showMessage(subject.Name)
    }

    override fun onItemLongClick(position: Int, view: View): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subjectAdded(subject: Subject) {
        hideLoading()
        add_subject_Adapter.removeItem(subject)
        mainActivity.refreshCalendar()
    }

    override fun subjectAddedError(message: String) {
        hideLoading()
        showMessage(message)
    }

    override fun onItemTaked(subject: Subject) {
        presenter?.takeSubject(subject)
        showLoading()
    }

}