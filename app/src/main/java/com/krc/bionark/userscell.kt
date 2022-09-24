package com.krc.bionark

import android.app.Dialog
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.krc.bionark.databinding.MainrecBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.NonCancellable.cancel
import kotlinx.coroutines.NonCancellable.start

class userscell (private  val postList: ArrayList<mainlist>) : RecyclerView.Adapter<userscell.PostHolder>() {
    private  lateinit var binding : MainrecBinding
    private  var begendimi = 0
    private lateinit var mListener : onItemClickListener
    private lateinit var db2: FirebaseFirestore

    interface  onItemClickListener{
        fun onItemClick(position: Int)



    }

    fun setOnClickListener(listener: onItemClickListener){
        mListener = listener

    }




    class PostHolder(val binding : MainrecBinding, listener: userscell.onItemClickListener) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = MainrecBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  PostHolder(binding,mListener)


    }
    private  lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore


    override fun onBindViewHolder(holder: userscell.PostHolder, position: Int) {

        holder.binding.textView16.text = postList.get(position).uname
        holder.binding.textView17.text = "Medeni Hali: "+postList.get(position).medeni
        holder.binding.textView18.text = "Yaş: "+postList.get(position).yas
        holder.binding.textView20.text = "Kilo: "+postList.get(position).kilo
        holder.binding.textView19.text = "Şehir: "+postList.get(position).sehir
        holder.binding.textView23.text = "Çocuk: "+postList.get(position).cocuk
        holder.binding.textView21.text = "Eğitim: "+postList.get(position).egitim
        Picasso.get().load(postList.get(position).img).into(holder.binding.imageView2)





        holder.binding.imageButton6.setOnClickListener {
            if (begendimi == 0) {

            println("dasdasd")
            holder.binding.imageButton6.setColorFilter(Color.argb(255, 255, 0, 0));
            auth = Firebase.auth

            db = Firebase.firestore

            val data = hashMapOf(
                "o" to postList.get(position).id,
                "ben" to auth.currentUser?.email
            )


            db.collection("begendiklerim")
                .add(data)
                .addOnSuccessListener { documentReference ->
                    Log.d(ContentValues.TAG, "DocumentSnapshot written with ID: ${documentReference.id}")



                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding document", e)
                }

            }
            else {

            }

        }

        holder.binding.button4.setOnClickListener { v ->

            println("girdi")

            auth = Firebase.auth

            db2 = Firebase.firestore
            db2.collection("begendiklerim")
                .whereEqualTo("ben", auth.currentUser?.email).whereEqualTo("o",postList.get(position).id)
                .addSnapshotListener { value2, error ->

                    if (error != null) {


                    } else {

                        if (value2 != null) {

                            if (!value2.isEmpty) {
                                println("agabe6")

                                val intent = Intent(v.context, chatpage::class.java)
                                intent.putExtra("kim",postList.get(position).id)
                                intent.putExtra("durum","1")
                                intent.putExtra("krc","0")
                                v.context.startActivity(intent)

                            }

                        }
                        else {
                            val intent = Intent(v.context, chatpage::class.java)
                            intent.putExtra("kim",postList.get(position).id)
                            intent.putExtra("durum","0")
                            intent.putExtra("krc","0")

                            v.context.startActivity(intent)
                        }

                    }



                }
        }






    }



    override fun getItemCount(): Int {
        return  postList.size
    }


}


