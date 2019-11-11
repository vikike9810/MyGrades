package com.szakdolgozat.mygrades.ui.talking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.events.GetMessageEvent
import com.szakdolgozat.mygrades.model.Message
import com.szakdolgozat.mygrades.model.Talking
import com.szakdolgozat.mygrades.model.User

import com.szakdolgozat.mygrades.ui.main.MainActivity
import com.szakdolgozat.mymessages.recyclerview.adapter.MessageRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_talking.*

class TalkingFragment(var talking: Talking): Fragment(), TalkingView {

    lateinit var talkingPresenter: TalkingPresenter
    lateinit var mainActivity: MainActivity
    lateinit var talkingRecyclerView: RecyclerView
    private lateinit var messageRecyclerViewAdapter: MessageRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        talkingPresenter= TalkingPresenter(this, talking)
        mainActivity= activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_talking, container, false)
        talkingRecyclerView=view.findViewById(R.id.messages_recycler)
        initRecyclerView()
        val sendButton= view.findViewById<ImageView>(R.id.send_button)
        sendButton.setOnClickListener {
            onClickSend(view)
        }
        val text_Label=view.findViewById<TextView>(R.id.talking_name_label)
        if(talking.person1.equals(User.person)){

            text_Label.text=talking.person2.getName()
        }
        else{
            text_Label.text=talking.person1.getName()
        }
        return view
    }


    fun onClickSend(v: View){
        talkingPresenter.newMessage(talking_editText.text.toString())
    }

    override fun errorInSave(message: String) {
        Toast.makeText(activity,message,Toast.LENGTH_LONG).show()
    }

    private fun initRecyclerView(){
        talkingRecyclerView.layoutManager= LinearLayoutManager(this.mainActivity)
        messageRecyclerViewAdapter = MessageRecyclerViewAdapter()
        messageRecyclerViewAdapter.addNewItems(talking.messages)
        talkingRecyclerView.adapter = messageRecyclerViewAdapter
        GetMessageEvent.event+={
            refereshRecyclerView(it)
        }
    }

    fun refereshRecyclerView(mess: String ){
        messageRecyclerViewAdapter.addNewItems(talking.messages)
    }

    override fun messageAdded(message: Message?) {

        if(message!=null){
            messageRecyclerViewAdapter.addMessage(message)
        }
        talking_editText.text.clear()
    }
}