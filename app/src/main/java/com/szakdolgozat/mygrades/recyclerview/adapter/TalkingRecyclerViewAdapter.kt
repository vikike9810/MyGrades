package com.szakdolgozat.mygrades.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.model.Talking
import com.szakdolgozat.mygrades.model.User
import java.util.*
import kotlin.collections.ArrayList

class TalkingRecyclerViewAdapter: RecyclerView.Adapter<TalkingRecyclerViewAdapter.ViewHolder>() {

        private val talkings = mutableListOf<Talking>()

        var itemClickListener: TalkingClickListener? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.talking_row, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val talking = talkings[position]
            holder.talking = talking

            if(talking.person1.equals(User.person)){
                holder.name.text=talking.person2.getName()
            }
            else{
                holder.name.text=talking.person1.getName()
            }
           holder.date.text=talking.getLastMessageDate()

        }

        fun addItem(talking: Talking) {
            val size = talkings.size
            talkings.add(talking)
            notifyItemInserted(size)
        }


        fun addAll(newTalkings: ArrayList<Talking>) {
            val size = talkings.size
            talkings.addAll(newTalkings)
            notifyItemRangeInserted(size, talkings.size)
        }

        fun deleteRow(position: Int) {
            talkings.removeAt(position)
            notifyItemRemoved(position)
        }

        fun removeItem(talking: Talking){
            val pos= talkings.indexOf(talking)
            talkings.remove(talking)
            notifyItemRemoved(pos)
        }

        fun addNewItems(newTalkings: ArrayList<Talking>){
            talkings.clear()
            talkings.addAll(newTalkings)
            notifyDataSetChanged()
        }

        override fun getItemCount() = talkings.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var talking: Talking? = null

            val name: TextView =view.findViewById(R.id.talking_name)
            val date: TextView =view.findViewById(R.id.talking_last_date)
            val avatar: ImageView =view.findViewById(R.id.talking_avatar)



            init {
                itemView.setOnClickListener {
                    talking?.let { talking -> itemClickListener?.onItemClick(talking) }
                }

                itemView.setOnLongClickListener { view ->
                    itemClickListener?.onItemLongClick(adapterPosition, view)
                    true
                }
            }



        }

        interface TalkingClickListener {
            fun onItemClick(talking: Talking)
            fun onItemLongClick(position: Int, view: View): Boolean
        }

}