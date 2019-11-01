package com.szakdolgozat.mygrades.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.model.Chat
import com.szakdolgozat.mygrades.model.Talking
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.recyclerview.adapter.TalkingRecyclerViewAdapter
import com.szakdolgozat.mygrades.ui.main.MainActivity
import android.R.string.cancel
import android.content.DialogInterface
import android.app.AlertDialog
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.szakdolgozat.mygrades.model.Diary


class ChatFragment: Fragment(), ChatView, TalkingRecyclerViewAdapter.TalkingClickListener {

    lateinit var chatPresenter: ChatPresenter
    lateinit var mainActivity: MainActivity
    lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatRecyclerViewAdapter: TalkingRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chatPresenter= ChatPresenter(this)
        mainActivity= activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_chat, container, false)
        chatRecyclerView=view.findViewById(R.id.talking_recycler)
        initRecyclerView()
        val newTalkingButton= view.findViewById<FloatingActionButton>(R.id.add_new_talk)
        newTalkingButton.setOnClickListener {
            onclickNewTalk(view)
        }
        return view
    }

    private fun onclickNewTalk(v: View) {


    }

    fun createTalking(personName: String){

    }

    private fun initRecyclerView(){
        chatRecyclerView.layoutManager= LinearLayoutManager(this.mainActivity)
        chatRecyclerViewAdapter = TalkingRecyclerViewAdapter()
        chatRecyclerViewAdapter.itemClickListener = this
        chatRecyclerViewAdapter.addNewItems(Chat.getTalkingsByUser())
        chatRecyclerView.adapter = chatRecyclerViewAdapter
    }

    override fun onItemClick(talking: Talking){
        mainActivity.showTalkingFragment(talking)
    }

    override fun onItemLongClick(position: Int, view: View): Boolean{
        return true
    }

}