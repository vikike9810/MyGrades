package com.szakdolgozat.mymessages.recyclerview.adapter

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.text.Layout
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.model.Message
import com.szakdolgozat.mygrades.model.User
import java.util.*
import kotlin.collections.ArrayList





class MessageRecyclerViewAdapter : RecyclerView.Adapter<MessageRecyclerViewAdapter.ViewHolder>() {

    var messages = mutableListOf<Message>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messages[position]
        holder.message = message
        holder.message_text.text=message.message
        holder.message_date.text =message.getMessageTime()

        if(message.sender.equals(User.person)){
            holder.message_text.gravity=Gravity.END
            holder.message_date.gravity=Gravity.END

            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.gravity = Gravity.END
            holder.message_layout.layoutParams=params

            holder.message_text.setTextColor(Color.parseColor("#003300"))
            holder.message_date.setTextColor(Color.parseColor("#003300"))
            holder.message_layout.setBackgroundResource(R.drawable.rounded_edittext)
            }

    }


    override fun getItemCount(): Int {
        return messages.size
    }

    fun addAll(newMessages: ArrayList<Message>) {
        var size = messages.size
        for(message: Message in newMessages){
            messages.add(0,message)
        }
        notifyItemRangeInserted(size, messages.size)
    }

    fun addNewItems(newMessages: ArrayList<Message>) {
        messages.clear()
        for(message: Message in newMessages){
            messages.add(0,message)
        }
        sortingMessages()
        notifyDataSetChanged()
    }

    fun addMessage(message: Message) {
        messages.add(message)
        sortingMessages()
        notifyDataSetChanged()
    }

    fun sortingMessages(){
        messages.sortWith(Message.MessageComparator)
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var message: Message? = null
        var message_text = view.findViewById<TextView>(R.id.message_text)
        var message_date = view.findViewById<TextView>(R.id.message_date)
        var message_layout = view.findViewById<LinearLayout>(R.id.message_layout)
    }

}