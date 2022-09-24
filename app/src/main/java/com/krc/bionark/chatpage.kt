package com.krc.bionark

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.MobileAds
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.type.DateTime
import com.krc.bionark.databinding.ActivityChatpageBinding
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class chatpage : AppCompatActivity() {
    private lateinit var binding: ActivityChatpageBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private var idsi : String = ""

    private  lateinit var feedAdapter : msjcell
    private lateinit var db2: FirebaseFirestore
    private lateinit var db3: FirebaseFirestore

    private lateinit var postarraylist : ArrayList<inmsjlist>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatpageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth

        db = Firebase.firestore
        db3 = Firebase.firestore

        db2 = Firebase.firestore

        if (getIntent().getStringExtra("krc") as String == "1") {
            idsi = getIntent().getStringExtra("idsi") as String

        }
        else {
            gecmisvarmi()
        }



        if (getIntent().getStringExtra("durum") == "1") {
        }
        else {
            alertim0()

        }
        binding.imageButton.setOnClickListener {



        }

        binding.imageButton31.setOnClickListener {

            val intent = Intent(this@chatpage, mainview::class.java)

            startActivity(intent)

        }

        binding.imageButton2.setOnClickListener {

            val intent = Intent(this@chatpage, begenmeler::class.java)

            startActivity(intent)

        }
        binding.imageButton3.setOnClickListener {

            val intent = Intent(this@chatpage, profilim::class.java)

            startActivity(intent)

        }

        getben()

        postarraylist = ArrayList<inmsjlist>()

        binding.msjrew.layoutManager = LinearLayoutManager(this)



        feedAdapter = msjcell(postarraylist)
        feedAdapter.setOnClickListener(object : msjcell.onItemClickListener{
            override fun onItemClick(position: Int) {


            }




        })
        MobileAds.initialize(this) {}

        binding.msjrew.adapter = feedAdapter

        binding.button5.setOnClickListener {
            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            val formatted = current.format(formatter)
            // Add a new document with a generated id.
            val data = hashMapOf(
                "gonderen" to auth.currentUser?.email,
                "msj" to binding.editTextTextPersonName4.text.toString(),
                "id" to idsi,
                "tarih" to formatted.toString()
            )

            db.collection("mesajlaricerik")
                .add(data)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                    binding.editTextTextPersonName4.setText("")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }

        }

        getdetay()


    }


    fun gecmisvarmi() {

        if(idsi == ""){
            val data = hashMapOf(
                "gonderen" to auth.currentUser?.email,
                "alan" to getIntent().getStringExtra("kim")
            )

            db.collection("mesajlar")
                .add(data)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                    idsi = documentReference.id


                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
        else {


        }


    }


    private  fun getben() {

        println("test1")

        db.collection("mesajlar").whereEqualTo("gonderen",auth.currentUser?.email).whereEqualTo("alan",getIntent().getStringExtra("kim")).addSnapshotListener { value, error ->

            if(error != null) {
                Toast.makeText(this,error.localizedMessage, Toast.LENGTH_LONG).show()

            }else {
                println("test2")

                if (value != null) {

                    if(!value.isEmpty) {
                        println("test3")

                        val doc = value.documents
                        for (docc in doc) {

                            idsi = docc.id

                        }

                    }


                }
                println("test4")


            }
            if (idsi == "") {

                println("test5")
                geto()

            }
            else {
                getdatas()
            }



        }

    }
    private  fun geto() {
        println("test6")


        db.collection("mesajlar").whereEqualTo("alan",auth.currentUser?.email).whereEqualTo("gonderen",getIntent().getStringExtra("kim")).addSnapshotListener { value, error ->

            if(error != null) {
                Toast.makeText(this,error.localizedMessage, Toast.LENGTH_LONG).show()

            }else {
                println("test7")

                if (value != null) {

                    if(!value.isEmpty) {
                        println("test8")

                        val doc = value.documents
                        for (docc in doc) {

                            idsi = docc.get("id") as String
                            getdatas()

                        }

                    }
                    println("test9")



                }

                println("test10")

            }
            println("test11")



        }

    }


    private  fun getdatas() {
        println("test12")

        db.collection("mesajlaricerik").whereEqualTo("id",idsi).orderBy("tarih").addSnapshotListener { value, error ->

            if(error != null) {
                Toast.makeText(this,error.localizedMessage, Toast.LENGTH_LONG).show()

            }else {
                println("test13")
                if (value != null) {
                    postarraylist.clear()
                    if(!value.isEmpty) {
                        println("test14")
                        val doc = value.documents
                        for (docc in doc) {

                            if (docc.get("gonderen") as String == auth.currentUser?.email.toString()) {
                                println("test15")
                            val post = inmsjlist("" , docc.get("msj") as String)
                                postarraylist.add(post)

                            }
                            if (docc.get("gonderen") as String == getIntent().getStringExtra("kim")) {
                                println("test16")
                                val post = inmsjlist(docc.get("msj") as String ,"" )
                                postarraylist.add(post)

                            }


                        }

                    }
                    println("test17")

                }
                println("test18")
                feedAdapter.notifyDataSetChanged()

            }
            feedAdapter.notifyDataSetChanged()


        }

    }


    fun getdetay() {

        db3.collection("users").whereEqualTo("email",getIntent().getStringExtra("kim")).addSnapshotListener { value3, error3 ->

            if (error3 != null) {
                Toast.makeText(this, error3.localizedMessage, Toast.LENGTH_LONG)
                    .show()

            } else {

                if (value3 != null) {

                    if (!value3.isEmpty) {
                        val doc2 = value3.documents
                        for (docc2 in doc2) {

                            binding.textView7.setText(docc2.get("isim") as String)
                            binding.textView8.setText(docc2.get("bio") as String)
                            Picasso.get().load(docc2.get("img") as String).into(binding.imageView)

                        }
                    }
                } else {

                }
            }
        }


    }

    fun alertim0() {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Mesaj Kredisi Hatırlatma")
        //set message for alert dialog
        builder.setMessage("Karşılıklı beğeni olmadığı için mesajlaşma başlatırsanız 1 krediniz eksilir")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Başlat(-1 Kredi)"){dialogInterface, which ->
            Toast.makeText(applicationContext,"clicked yes", Toast.LENGTH_LONG).show()
        }
        //performing cancel action
        builder.setNeutralButton("İptal"){dialogInterface , which ->
            val intent = Intent(this@chatpage, mainview::class.java)

            startActivity(intent)        }

        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

}