package com.szakdolgozat.mygrades.ui.addgrade

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.base.BaseFragment
import com.szakdolgozat.mygrades.ui.main.MainActivity
import kotlinx.android.synthetic.main.add_grade_fragment.*

class AddGradeFragment : BaseFragment<AddGradePresenter, AddGradeView>(), AddGradeView,
    AdapterView.OnItemSelectedListener {

    lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.add_grade_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    override fun createPresenter(): AddGradePresenter {
        return AddGradePresenter(this)
    }

    private fun initView() {
        add_new_grade.setOnClickListener {
            onClickAdd(it)
        }

        val subjectAdapter: ArrayAdapter<String> =
            ArrayAdapter(activity, android.R.layout.simple_spinner_item, presenter?.getSubjectList())

        add_grade_subject_spinner.adapter = subjectAdapter

        add_grade_subject_spinner.setOnItemSelectedListener(this)
    }

    fun onClickAdd(v: View) {
        if (add_grade_student_spinner.selectedItem.equals("") || add_grade_subject_spinner.selectedItem.equals("") || add_grade_spinner.selectedItem.equals(
                ""
            )
        ) {
            showMessage(getString(R.string.addgradefields))
        } else {
            showLoading()
            add_new_grade.isClickable = false
            presenter?.addGrade(
                add_grade_subject_spinner.selectedItem.toString(),
                add_grade_student_spinner.selectedItem.toString(),
                add_grade_spinner.selectedItem.toString(),
                grade_comment.text.toString()
            )
        }
    }

    override fun gradeAdded() {
        hideLoading()
        add_new_grade.isClickable = true
        mainActivity.returnFromAddGradeFragment()
    }

    override fun errorInSave(message: String) {
        hideLoading()
        add_new_grade.isClickable = true
       showMessage(message)
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val personList = presenter?.getStudentListBySubjectName(parent?.getItemAtPosition(position).toString())
        val studentAdapter: ArrayAdapter<String> =
            ArrayAdapter(activity, android.R.layout.simple_spinner_item, personList)
        add_grade_student_spinner.adapter = studentAdapter
    }

}