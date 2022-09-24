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
import com.krc.bionark.databinding.ActivityMainviewBinding

class mainview : AppCompatActivity() {
    private lateinit var binding: ActivityMainviewBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var db2: FirebaseFirestore

    private lateinit var postarraylist : ArrayList<mainlist>

    private var varsorgum : String = "0"
    private var kontrol : Int = 0
    private  lateinit var feedAdapter : userscell

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth



        db = Firebase.firestore
        db2 = Firebase.firestore

        binding.imageButton31.setOnClickListener {



        }

        binding.imageButton.setOnClickListener {

            val intent = Intent(this@mainview, chatlist::class.java)

            startActivity(intent)

        }

        binding.imageButton2.setOnClickListener {

            val intent = Intent(this@mainview, begenmeler::class.java)

            startActivity(intent)

        }
        binding.button3.setOnClickListener {
            val intent = Intent(this@mainview, buykredi::class.java)
            startActivity(intent)
        }
        binding.imageButton3.setOnClickListener {

            val intent = Intent(this@mainview, profilim::class.java)

            startActivity(intent)

        }



        postarraylist = ArrayList<mainlist>()

        binding.recma.layoutManager = LinearLayoutManager(this)



        feedAdapter = userscell(postarraylist)
        feedAdapter.setOnClickListener(object : userscell.onItemClickListener{
            override fun onItemClick(position: Int) {


            }




        })

        binding.recma.adapter = feedAdapter

        getusers()

    }


    private  fun getusers() {
        kontrol = 1
        postarraylist.clear()
        println("hopaldim")

        db.collection("users").addSnapshotListener { value, error ->
            println("hopaldim7")

            if(error != null) {
                Toast.makeText(this,error.localizedMessage, Toast.LENGTH_LONG).show()
                println("hopaldim2"+error)

            }else {
                println("hopaldim8")

                if (value != null) {
                    println("hopaldim10")

                    if(!value.isEmpty) {

                        val doc = value.documents
                        for (docc in doc) {
                            varsorgum = "0"

                            if (docc.get("email").toString() == auth.currentUser?.email) {
                                varsorgum = "1"
                                kontrol = 0
                            }
                            else {
                                kontrol = 1
                            }


                            db2.collection("begendiklerim").whereEqualTo("ben",auth.currentUser?.email).addSnapshotListener { value2, error ->


                                if (error != null) {
                                    Toast.makeText(this, error.localizedMessage, Toast.LENGTH_LONG)
                                        .show()

                                } else {


                                    if (value2 != null) {


                                        if (!value2.isEmpty) {
                                                varsorgum = "1"

                                            val doc2 = value2.documents
                                            for (docc2 in doc2) {
                                                if (docc2.get("o").toString() == docc.get("email").toString()) {
                                                    print("geri")
                                                    kontrol = 0
                                                }
                                                else {
                                                    kontrol = 1
                                                }
                                            }
                                        }
                                    }
                                    else {

                                    }
                                }

                            }
                            if (kontrol == 1) {
                                val post = mainlist(docc.get("email") as String ,docc.get("isim") as String,docc.get("medeni") as String,docc.get("yas") as String,docc.get("kilo") as String,docc.get("sehir") as String,docc.get("cocuk") as String,docc.get("egitim") as String,docc.get("img") as String)
                                postarraylist.add(post)
                            }


                            if (varsorgum == "0") {
                                varsorgum = "0"


                            }






                        }

                    }

                    feedAdapter.notifyDataSetChanged()

                }


                feedAdapter.notifyDataSetChanged()
            }
            feedAdapter.notifyDataSetChanged()


        }
        feedAdapter.notifyDataSetChanged()



    }

}