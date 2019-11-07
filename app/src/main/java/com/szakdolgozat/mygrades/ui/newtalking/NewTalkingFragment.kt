package com.szakdolgozat.mygrades.ui.newtalking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.model.*
import com.szakdolgozat.mygrades.recyclerview.adapter.TalkingRecyclerViewAdapter
import com.szakdolgozat.mygrades.ui.main.MainActivity
import com.szakdolgozat.mygrades.ui.talking.TalkingView
import kotlinx.android.synthetic.main.fragment_add_new_talking.*

class NewTalkingFragment:Fragment(), NewTalkingView, TalkingRecyclerViewAdapter.TalkingClickListener {


    lateinit var mainActivity: MainActivity
    lateinit var personsRecyclerView: RecyclerView
    lateinit var presenter: NewTalkingPresenter
    private lateinit var personsRecyclerViewAdapter: TalkingRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity= activity as MainActivity
        presenter=NewTalkingPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_add_new_talking, container, false)
        personsRecyclerView=view.findViewById(R.id.new_talking_recycler)
        initRecyclerView()
        return view
    }


    private fun initRecyclerView(){
        personsRecyclerView.layoutManager= LinearLayoutManager(this.mainActivity)
        personsRecyclerViewAdapter = TalkingRecyclerViewAdapter()
        personsRecyclerViewAdapter.itemClickListener = this
        personsRecyclerViewAdapter.talkingsClear()
        personsRecyclerViewAdapter.addPeoples(Diary.teachers as ArrayList<Person>)
        personsRecyclerViewAdapter.addPeoples(Diary.students as ArrayList<Person>)
        personsRecyclerView.adapter = personsRecyclerViewAdapter
    }

    override fun onItemClick(talking: Talking){
        new_talking_progress.visibility=View.VISIBLE
        presenter.addTalking(talking)
    }

    override fun onItemLongClick(position: Int, view: View): Boolean{
        return true
    }

    override fun Error(message: String){
        new_talking_progress.visibility=View.INVISIBLE
        Toast.makeText(activity,message,Toast.LENGTH_LONG).show()
    }


    override fun talkingAdded() {
        new_talking_progress.visibility=View.INVISIBLE
        mainActivity.returnFromNewTalkingFragment()
    }


}