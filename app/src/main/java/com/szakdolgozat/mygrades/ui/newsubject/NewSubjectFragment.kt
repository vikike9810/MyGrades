package com.szakdolgozat.mygrades.ui.newsubject

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.base.BaseFragment
import com.szakdolgozat.mygrades.model.LessonDate
import com.szakdolgozat.mygrades.model.OnceLesson
import com.szakdolgozat.mygrades.ui.main.MainActivity
import com.szakdolgozat.mygrades.util.FormatDate
import kotlinx.android.synthetic.main.create_new_subject_fragment.*


class NewSubjectFragment: BaseFragment<NewSubjectPresenter, NewSubjectView>(), NewSubjectView, AdapterView.OnItemSelectedListener {

    lateinit var mainActivity: MainActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity= activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.create_new_subject_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setOnclickListeners()
    }

    override fun createPresenter(): NewSubjectPresenter {
        return NewSubjectPresenter(this)
    }

    fun setOnclickListeners(){

        subject_save.setOnClickListener {
            saveOnclick(it)
        }
        lesson_type.setOnItemSelectedListener(this)
        new_less_monday.setOnCheckedChangeListener { buttonView, isChecked ->
            onClickCheck(buttonView,isChecked)
        }
        new_less_thursday.setOnCheckedChangeListener { buttonView, isChecked ->
            onClickCheck(buttonView,isChecked)
        }
        new_less_wednesday.setOnCheckedChangeListener { buttonView, isChecked ->
            onClickCheck(buttonView,isChecked)
        }
        new_less_tuesday.setOnCheckedChangeListener { buttonView, isChecked ->
            onClickCheck(buttonView,isChecked)
        }
        new_less_friday.setOnCheckedChangeListener { buttonView, isChecked ->
            onClickCheck(buttonView,isChecked)
        }
        begin_lesson.setOnClickListener{
            selectDate(it)
        }
        end_lesson.setOnClickListener{
            selectDate(it)
        }

        first_lesson.setOnClickListener{
            selectDate(it)
        }
        last_lesson.setOnClickListener{
            selectDate(it)
        }

        setOnclickToChildrens(less_from)
        setOnclickToChildrens(less_to)
    }

    fun setOnclickToChildrens(layout: LinearLayout){
        for(i : Int in 0..(layout.childCount-1)){
            layout.getChildAt(i).setOnClickListener{
                selectTime(it)
            }
        }
    }

    fun saveOnclick(v: View){
        if(!((new_sub_Name.text?.isEmpty())?:true)) {
            if( !((new_sub_desc.text?.isEmpty())?:true)) {
                if (getDates()) {
                    subject_save.isClickable=false
                    presenter?.newSubject(new_sub_Name.text.toString(), new_sub_desc.text.toString())
                }
            }
            else{
                new_sub_desc.setError("Field is required!")
            }
        }
        else{
            new_sub_Name.setError("Field is required!")
        }
    }

    fun onClickCheck(v:View, checked: Boolean){
        if(checked) {
            when (v.tag) {
                "Monday" -> {
                    monday_from.visibility = View.VISIBLE
                    monday_to.visibility = View.VISIBLE
                }
                "Tuesday" -> {
                    tuesday_from.visibility = View.VISIBLE
                    tueday_to.visibility = View.VISIBLE
                }
                "Wednesday" -> {
                    wednesday_from.visibility = View.VISIBLE
                    wednesday_to.visibility = View.VISIBLE
                }
                "Thursday" -> {
                    thursday_from.visibility = View.VISIBLE
                    thursday_to.visibility = View.VISIBLE
                }
                "Friday" -> {
                    friday_from.visibility = View.VISIBLE
                    friday_to.visibility = View.VISIBLE
                }
            }
        }
        else{
            when (v.tag) {
                "Monday" -> {
                    monday_from.visibility = View.INVISIBLE
                    monday_to.visibility = View.INVISIBLE
                }
                "Tuesday" -> {
                    tuesday_from.visibility = View.INVISIBLE
                    tueday_to.visibility = View.INVISIBLE
                }
                "Wednesday" -> {
                    wednesday_from.visibility = View.INVISIBLE
                    wednesday_to.visibility = View.INVISIBLE
                }
                "Thursday" -> {
                    thursday_from.visibility = View.INVISIBLE
                    thursday_to.visibility = View.INVISIBLE
                }
                "Friday" -> {
                    friday_from.visibility = View.INVISIBLE
                    friday_to.visibility = View.INVISIBLE
                }
            }
        }
    }

    fun selectDate(v: View){
        DatePickerDialog(mainActivity, DatePickerDialog.OnDateSetListener(){view, year, month, dayOfMonth ->
            val real_month=month+1
            var currentTextView=end_lesson

            when(v.tag){
                "begin" ->{ currentTextView=begin_lesson}
                "first" ->{currentTextView=first_lesson }
                "last" ->{currentTextView=last_lesson }
            }

            currentTextView.text="$year.$real_month.$dayOfMonth"
            if(v.tag!="first" && v.tag!="last") {
                TimePickerDialog(mainActivity, TimePickerDialog.OnTimeSetListener() { view, hour, minutes ->
                    val currentText =  currentTextView.text.toString()
                    currentTextView.text = "$currentText $hour:$minutes"
                }, FormatDate.getHour(), FormatDate.getMinute(), true).show()
            }
        }, FormatDate.getYear(), FormatDate.getMonth()-1, FormatDate.getDay()).show()
    }

    fun selectTime(v:View){
        TimePickerDialog(mainActivity, TimePickerDialog.OnTimeSetListener(){ view,hour, minutes->
            (v as TextView).text="$hour:$minutes"
        },FormatDate.getHour(),FormatDate.getMinute(),true).show()
    }

    fun getDates(): Boolean{

        presenter?.type=lesson_type.selectedItem.toString()
        if(lesson_type.selectedItemPosition==0){
            val begin = begin_lesson.text.toString()
            val end = end_lesson.text.toString()

            if(!(begin.equals("Begin:")) && !(end.equals("End:"))){
                presenter?.OnceLesson= OnceLesson(begin, end)}

            else{
                showMessage( "Fields Begin and End are required!")
                return false
            }
        }
        else {
            val first = first_lesson.text.toString()
            val last = last_lesson.text.toString()

            if(!(first.equals("First lesson:")) && !(last.equals("Last lesson:"))) {

                presenter?.FirstLessonDate=first
                presenter?.LastLessonDate=last

                for (i: Int in 0..4) {
                    if ((Check_boxes.getChildAt(i) as CheckBox).isChecked) {
                        val from = (less_from.getChildAt(i) as TextView).text.toString()
                        val to = (less_to.getChildAt(i) as TextView).text.toString()

                        if (!(from.equals("from:")) && !(to.equals("to:"))) {
                            presenter?.LessonDates?.add(LessonDate(i + 1, from, to))
                        } else {
                            showMessage( "Fields From and To are required!")
                            return false
                        }
                    }
                }
            }
            else{
                showMessage("Fields First and Lastlesson are required!")
                return false
            }
        }

        return true
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0 -> {once_less.visibility=View.VISIBLE
                      weekly_less.visibility=View.GONE}
                1,2 ->{weekly_less.visibility=View.VISIBLE
                        once_less.visibility=View.GONE }
            }
    }

    override fun SubjectAdded() {
        mainActivity.refreshCalendar()
        mainActivity.returnFromNewSubjectFragment()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}