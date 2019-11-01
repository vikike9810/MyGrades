package com.szakdolgozat.mygrades.ui.grades

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.recyclerview.adapter.GradesRecyclerViewAdapter
import com.szakdolgozat.mygrades.ui.main.MainActivity
import kotlinx.android.synthetic.main.grades_fragment.*

class GradesFragment:Fragment(),GradesView {

    lateinit var gradesPresenter: GradesPresenter
    lateinit var mainActivity: MainActivity
    lateinit var gradeRecyclerView: RecyclerView
    lateinit var recyclerViewAdapter: GradesRecyclerViewAdapter
    lateinit var filterArrow: ImageView
    lateinit var filterLayout: LinearLayout
    var filterClosed=true
    lateinit var SubjectSpinner: Spinner
    lateinit var TeacherSpinner: Spinner
    lateinit var  addButton : FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gradesPresenter= GradesPresenter(this)
        mainActivity= activity as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view= inflater.inflate(R.layout.grades_fragment, container, false)
        initRecyclerView(view)
        filterArrow=view.findViewById(R.id.Filter_arrow)
        filterLayout=view.findViewById(R.id.Filters)
        filterArrow.setOnClickListener {
            onClickArrow(it)
        }
        setViewByUserType(view)
        initFilters(view)
        return view
    }

    private fun setViewByUserType(view: View) {
        if(User.type.equals("Teacher")) {
            addButton = view.findViewById<FloatingActionButton>(R.id.grade_add)
            addButton.visibility = View.VISIBLE
            addButton.setOnClickListener {
                mainActivity.showAddGradeFragment()
            }

            val personText=view.findViewById<TextView>(R.id.grade_person_text)
            personText.text="Student"
            TeacherSpinner= view.findViewById<Spinner>(R.id.Teacher_spinner)
            val teacheradapter : ArrayAdapter<String> = ArrayAdapter(activity,android.R.layout.simple_spinner_item, gradesPresenter.getStudentList())
            TeacherSpinner.adapter=teacheradapter

        }

        else{
            TeacherSpinner= view.findViewById<Spinner>(R.id.Teacher_spinner)
            var teacheradapter : ArrayAdapter<String> = ArrayAdapter(activity,android.R.layout.simple_spinner_item, gradesPresenter.getTeacherList())
            TeacherSpinner.adapter=teacheradapter
        }
    }

    private fun initFilters(view: View) {
        SubjectSpinner= view.findViewById<Spinner>(R.id.Subject_spinner)
        var subjectadapter : ArrayAdapter<String> = ArrayAdapter(activity,android.R.layout.simple_spinner_item, gradesPresenter.getSubjectList())
        SubjectSpinner.adapter=subjectadapter


        var goButton = view.findViewById<Button>(R.id.grade_go_button)
        goButton.setOnClickListener {
            onClickGoButton(it)
        }

    }

    fun onClickGoButton(v :View){
        recyclerViewAdapter.addNewItems(
            gradesPresenter.getFilteredGrades(SubjectSpinner.selectedItem.toString(),
                TeacherSpinner.selectedItem.toString(),
                Grade_spinner.selectedItem.toString(),
                recyclerViewAdapter.grades))
    }

    private fun onClickArrow(v: View?) {
        if(filterClosed){
            filterLayout.visibility=View.VISIBLE
            filterArrow.setImageResource(R.drawable.up_white)
            filterClosed=false
        }
        else{
            filterLayout.visibility=View.GONE
            filterArrow.setImageResource(R.drawable.down_white)
            filterClosed=true
        }
    }

    private fun initRecyclerView(view: View) {
        gradeRecyclerView= view.findViewById(R.id.grade_recycler)
        gradeRecyclerView.layoutManager=LinearLayoutManager(activity)
        recyclerViewAdapter= GradesRecyclerViewAdapter()
        gradeRecyclerView.adapter=recyclerViewAdapter
        recyclerViewAdapter.addNewItems(gradesPresenter.getGradesByUser())
    }
}