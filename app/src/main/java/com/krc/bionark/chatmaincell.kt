package com.krc.bionark

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.krc.bionark.databinding.ActivityChatlistBinding
import com.krc.bionark.databinding.ChatlistcellBinding
import com.squareup.picasso.Picasso

class chatmaincell (private  val postList: ArrayList<chatarraymain>) : RecyclerView.Adapter<chatmaincell.PostHolder>() {
    private lateinit var binding: ChatlistcellBinding
    private var begendimi = 0
    private lateinit var mListener: onItemClickListener
    private lateinit var db2: FirebaseFirestore

    interface onItemClickListener {
        fun onItemClick(position: Int)


    }

    fun setOnClickListener(listener: onItemClickListener) {
        mListener = listener

    }


    class PostHolder(
        val binding: ChatlistcellBinding,
        listener: chatmaincell.onItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding =
            ChatlistcellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostHolder(binding, mListener)


    }

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore


    override fun onBindViewHolder(holder: chatmaincell.PostHolder, position: Int) {

        holder.binding.textView22.text = postList.get(position).kim
        holder.binding.textView25.text = postList.get(position).mesaj
        if (postList.get(position).img != "") {

        Picasso.get().load(postList.get(position).img).into(holder.binding.imageView3)

        }

    }


    override fun getItemCount(): Int {
        return postList.size
    }
}