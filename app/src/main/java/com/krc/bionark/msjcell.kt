package com.krc.bionark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.krc.bionark.databinding.InchatcellBinding
import com.squareup.picasso.Picasso

class msjcell (private  val postList: ArrayList<inmsjlist>) : RecyclerView.Adapter<msjcell.PostHolder>() {
    private  lateinit var binding : InchatcellBinding
    private  var begendimi = 0
    private lateinit var mListener : onItemClickListener
    private lateinit var db2: FirebaseFirestore

    interface  onItemClickListener{
        fun onItemClick(position: Int)



    }

    fun setOnClickListener(listener: msjcell.onItemClickListener){
        mListener = listener

    }




    class PostHolder(val binding: InchatcellBinding, listener: onItemClickListener) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }


    private  lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore






    override fun getItemCount(): Int {
        return  postList.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.binding.textView26.text = postList.get(position).o
        holder.binding.textView27.text = postList.get(position).ben


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = InchatcellBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  PostHolder(binding,mListener)


    }

}