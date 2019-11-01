package com.szakdolgozat.mygrades.ui.addgrade

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.ui.main.MainActivity
import kotlinx.android.synthetic.main.add_grade_fragment.*

class AddGradeFragment: Fragment(), AddGradeView, AdapterView.OnItemSelectedListener {

    lateinit var addGradePresenter: AddGradePresenter
   lateinit var studentSpinner: Spinner
    lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addGradePresenter= AddGradePresenter(this)
        mainActivity=activity as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view= inflater.inflate(R.layout.add_grade_fragment, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        var addNewGrade = view.findViewById<Button>(R.id.add_new_grade).setOnClickListener {
            onClickAdd(view)
        }
        var subjectSpinner= view.findViewById<Spinner>(R.id.add_grade_subject_spinner)

        var subjectAdapter : ArrayAdapter<String> = ArrayAdapter(activity,android.R.layout.simple_spinner_item, addGradePresenter.getSubjectList())
        subjectSpinner.adapter=subjectAdapter

        studentSpinner= view.findViewById<Spinner>(R.id.add_grade_student_spinner)

        subjectSpinner.setOnItemSelectedListener(this)
    }

    fun onClickAdd(v: View){
        if(add_grade_student_spinner.selectedItem.equals("") || add_grade_subject_spinner.selectedItem.equals("") || add_grade_spinner.selectedItem.equals("")){
            Toast.makeText(activity,"Subject, Student and Grade fields are required!", Toast.LENGTH_SHORT).show()
        }
        else{
            addGradePresenter.addGrade(add_grade_subject_spinner.selectedItem.toString(), add_grade_student_spinner.selectedItem.toString(), add_grade_spinner.selectedItem.toString(), grade_comment.text.toString())
        }
    }

    override fun gradeAdded() {
       mainActivity.returnFromAddGradeFragment()
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun  onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var personList= addGradePresenter.getStudentListBySubjectName(parent?.getItemAtPosition(position).toString())
        var studentAdapter : ArrayAdapter<String> = ArrayAdapter(activity,android.R.layout.simple_spinner_item, personList)
        studentSpinner.adapter=studentAdapter
    }

}