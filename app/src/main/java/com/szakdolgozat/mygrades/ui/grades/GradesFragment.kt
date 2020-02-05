package com.szakdolgozat.mygrades.ui.grades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.base.BaseFragment
import com.szakdolgozat.mygrades.events.GetGradeEvent
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.model.UserType
import com.szakdolgozat.mygrades.recyclerview.adapter.GradesRecyclerViewAdapter
import com.szakdolgozat.mygrades.ui.main.MainActivity
import kotlinx.android.synthetic.main.grades_fragment.*

class GradesFragment : BaseFragment<GradesPresenter, GradesView>(), GradesView {

    lateinit var mainActivity: MainActivity
    lateinit var recyclerViewAdapter: GradesRecyclerViewAdapter
    var filterClosed = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity

        GetGradeEvent.event += {
            getNewGrade(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.grades_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()

        Filter_arrow.setOnClickListener {
            onClickArrow()
        }

        setViewByUserType()
        initFilters()
    }

    override fun createPresenter(): GradesPresenter {
        return GradesPresenter(this)
    }

    private fun getNewGrade(it: String) {
        onClickGoButton(null)
    }

    private fun setViewByUserType() {
        if (User.type.equals(UserType.Teacher)) {
            grade_add.visibility = View.VISIBLE
            grade_add.setOnClickListener {
                mainActivity.showAddGradeFragment()
            }

            grade_person_text.text = getString(R.string.student)
            val teacheradapter: ArrayAdapter<String> =
                ArrayAdapter(activity, android.R.layout.simple_spinner_item, presenter!!.getStudentList())
            Teacher_spinner.adapter = teacheradapter

        } else {
            val teacheradapter: ArrayAdapter<String> =
                ArrayAdapter(activity, android.R.layout.simple_spinner_item, presenter!!.getTeacherList())
            Teacher_spinner.adapter = teacheradapter
        }
    }

    private fun initFilters() {
        val subjectadapter: ArrayAdapter<String> =
            ArrayAdapter(activity, android.R.layout.simple_spinner_item, presenter!!.getSubjectList())
        Subject_spinner.adapter = subjectadapter


        grade_go_button.setOnClickListener {
            onClickGoButton(it)
        }

    }

    fun onClickGoButton(v: View?) {
        var teacher = ""
        teacher = Teacher_spinner.selectedItem.toString()

        var subject = ""
        subject = Subject_spinner.selectedItem.toString()

        var grade = ""
        if (Grade_spinner != null) {
            grade = Grade_spinner.selectedItem.toString()
        }
        recyclerViewAdapter.addNewItems(
            presenter!!.getFilteredGrades(
                subject,
                teacher,
                grade,
                recyclerViewAdapter.grades
            )
        )
    }

    private fun onClickArrow() {
        if (filterClosed) {
            Filters.visibility = View.VISIBLE
            Filter_arrow.setImageResource(R.drawable.up_white)
            filterClosed = false
        } else {
            Filters.visibility = View.GONE
            Filter_arrow.setImageResource(R.drawable.down_white)
            filterClosed = true
        }
    }

    private fun initRecyclerView() {
        grade_recycler.layoutManager=LinearLayoutManager(activity)
        recyclerViewAdapter= GradesRecyclerViewAdapter()
        grade_recycler.adapter=recyclerViewAdapter
        recyclerViewAdapter.addNewItems(presenter!!.getGradesByUser())
    }
}