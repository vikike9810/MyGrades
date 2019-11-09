package com.szakdolgozat.mygrades.recyclerview.adapter

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.firebase.FirebaseStorageProvider
import com.szakdolgozat.mygrades.model.Person
import com.szakdolgozat.mygrades.model.Talking
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.util.ImageProvider
import java.io.File
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
                holder.pesonTo=talking.person2
            }
            else{
                holder.name.text=talking.person1.getName()
                holder.pesonTo=talking.person1
            }

            holder.pesonTo?.getuserId()?.let { holder.getAvatarByUser(it) }

            if(talking.person1.equals(talking.person2)){
                holder.date.visibility=View.INVISIBLE
                holder.talking_last_date_label.visibility=View.INVISIBLE
            }
            else{
                holder.date.text=talking.getLastMessageDate()}

        }

        fun addItem(talking: Talking) {
            val size = talkings.size
            talkings.add(talking)
            notifyItemInserted(size)
        }


        fun addAll(newTalkings: ArrayList<Talking>) {
            val size = talkings.size
            talkings.addAll(newTalkings)
            talkings.sortWith(Talking.TalkingComparator)
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

        fun addPeoples(newTalkings: ArrayList<Person>){
            newTalkings.forEach {
                if(!(it.equals(User.person))) {
                    talkings.add(Talking(it))
                }
                talkings.sortWith(Talking.TalkingComparator)
            }
            notifyDataSetChanged()
        }

        fun talkingsClear(){
            talkings.clear()
            notifyDataSetChanged()
        }

        fun addNewItems(newTalkings: ArrayList<Talking>){
            talkings.clear()
            talkings.addAll(newTalkings)
            talkings.sortWith(Talking.TalkingComparator)
            notifyDataSetChanged()
        }



        override fun getItemCount() = talkings.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var talking: Talking? = null

            val name: TextView =view.findViewById(R.id.talking_name)
            val date: TextView =view.findViewById(R.id.talking_last_date)
            var pesonTo: Person?=null
            val talking_last_date_label= view.findViewById<TextView>(R.id.talking_last_date_label)
            val talking_avatar_progressbar=view.findViewById<ProgressBar>(R.id.talking_avatar_progressbar)
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

            fun getAvatarByUser(useId: String){
                talking_avatar_progressbar.visibility=View.VISIBLE
                FirebaseStorageProvider.downloadImage({image,userId -> imageDownloaded(image, userId)}, {userId -> imageDownloadError(userId)}, useId)

            }

            fun imageDownloaded(image : File, userId: String){
                val newavatar=ImageProvider.formatImage(image)
                avatar.setImageBitmap(newavatar)
                avatar.visibility=View.VISIBLE
                talking_avatar_progressbar.visibility=View.INVISIBLE
            }

            fun imageDownloadError(userId: String){
                avatar.visibility=View.VISIBLE
                talking_avatar_progressbar.visibility=View.INVISIBLE
            }

        }

        interface TalkingClickListener {
            fun onItemClick(talking: Talking)
            fun onItemLongClick(position: Int, view: View): Boolean
        }

}