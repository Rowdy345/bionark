package com.krc.bionark

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.krc.bionark.databinding.ActivityChatlistBinding
import com.krc.bionark.databinding.ActivityMainviewBinding

class chatlist : AppCompatActivity() {
    private lateinit var binding: ActivityChatlistBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var db2: FirebaseFirestore
    private lateinit var db3: FirebaseFirestore

    private lateinit var idsi : String
    private var sayac : Int = 0
    private lateinit var kimmis : String
    private  var lastmsj : String = ""
    private  var lastimg : String = ""

    private lateinit var postarraylist : ArrayList<chatarraymain>


    private  lateinit var feedAdapter : chatmaincell

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatlistBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth

        db = Firebase.firestore
        db2 = Firebase.firestore
        db3 = Firebase.firestore


        binding.imageButton.setOnClickListener {



        }

        binding.imageButton31.setOnClickListener {

            val intent = Intent(this@chatlist, mainview::class.java)

            startActivity(intent)

        }

        binding.imageButton2.setOnClickListener {

            val intent = Intent(this@chatlist, begenmeler::class.java)

            startActivity(intent)

        }
        binding.imageButton3.setOnClickListener {

            val intent = Intent(this@chatlist, profilim::class.java)

            startActivity(intent)

        }


        postarraylist = ArrayList<chatarraymain>()


        binding.recmes.layoutManager = LinearLayoutManager(this)



        feedAdapter = chatmaincell(postarraylist)
        feedAdapter.setOnClickListener(object : chatmaincell.onItemClickListener{
            override fun onItemClick(position: Int) {


                val intent = Intent(this@chatlist, chatpage::class.java)

                intent.putExtra("idsi",postarraylist.get(position).id)
                intent.putExtra("krc","1")
                intent.putExtra("durum","1")
                intent.putExtra("kim",postarraylist.get(position).kim)

                startActivity(intent)


            }




        })

        binding.recmes.adapter = feedAdapter


        getben()


    }


    private  fun getben() {
        sayac = 0
        postarraylist.clear()
        db.collection("mesajlar").addSnapshotListener { value, error ->

            if(error != null) {
                Toast.makeText(this,error.localizedMessage, Toast.LENGTH_LONG).show()

            }else {

                if (value != null) {

                    if(!value.isEmpty) {

                        val doc = value.documents
                        for (docc in doc) {
                            if(docc.get("alan") as String == auth.currentUser?.email  || docc.get("gonderen") as String == auth.currentUser?.email) {

                            lastmsj = ""
                            sayac ++

                            db2.collection("mesajlaricerik").whereEqualTo("id",docc.id).addSnapshotListener { value2, error2 ->

                                if (error2 != null) {
                                    Toast.makeText(this, error2.localizedMessage, Toast.LENGTH_LONG)
                                        .show()

                                } else {

                                    if (value2 != null) {

                                        if (!value2.isEmpty) {
                                            val doc2 = value2.documents
                                            for (docc2 in doc2) {
                                                lastmsj = docc2.get("msj") as String
                                                println("mesajvar"+lastmsj)
                                            }
                                        }
                                    }
                                }
                            }



                            if(docc.get("alan") as String == auth.currentUser?.email) {
                                kimmis = docc.get("gonderen") as String
                                    println("çek1")

                                db3.collection("users").whereEqualTo("email",kimmis).addSnapshotListener { value3, error3 ->
                                    println("çek3")

                                    if (error3 != null) {
                                        Toast.makeText(this, error3.localizedMessage, Toast.LENGTH_LONG)
                                            .show()

                                    } else {

                                        if (value3 != null) {

                                            if (!value3.isEmpty) {
                                                val doc3 = value3.documents
                                                for (docc3 in doc3) {
                                                    lastimg = docc3.get("img") as String
                                                    val post = chatarraymain(docc.id as String,lastimg,kimmis,lastmsj)
                                                    postarraylist.add(post)
                                                    feedAdapter.notifyDataSetChanged()


                                                }
                                            }
                                        } else {

                                        }
                                    }
                                }




                            }
                            else {
                                println("çek2")

                                kimmis = docc.get("alan") as String
                                db3.collection("users").whereEqualTo("email",kimmis).addSnapshotListener { value3, error3 ->
                                    println("çek4")

                                    if (error3 != null) {
                                        Toast.makeText(this, error3.localizedMessage, Toast.LENGTH_LONG)
                                            .show()

                                    } else {

                                        if (value3 != null) {

                                            if (!value3.isEmpty) {
                                                val doc3 = value3.documents
                                                for (docc3 in doc3) {
                                                    lastimg = docc3.get("img") as String
                                                    val post = chatarraymain(docc.id as String,lastimg,kimmis,lastmsj)
                                                    postarraylist.add(post)
                                                    feedAdapter.notifyDataSetChanged()


                                                }
                                            }
                                        } else {

                                        }

                                    }
                                }



                            }






                            }

                        }
                        binding.textView24.setText("Mesajlarım ($sayac)")
                        feedAdapter.notifyDataSetChanged()

                    }
                    println("hopaldim4")
                    feedAdapter.notifyDataSetChanged()


                }
                feedAdapter.notifyDataSetChanged()

                println("hopaldim5")

            }

            feedAdapter.notifyDataSetChanged()

        }
        feedAdapter.notifyDataSetChanged()

    }



}