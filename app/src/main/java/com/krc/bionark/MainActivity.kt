package com.krc.bionark

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.krc.bionark.databinding.ActivityKayitBinding
import com.krc.bionark.databinding.ActivityMainBinding
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class MainActivity : AppCompatActivity() {
    private  lateinit var binding : ActivityMainBinding
    private var storage: StorageReference? = null

    private lateinit var db : FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        db = Firebase.firestore


        getdata()


    }



    private  fun getdata() {

        println("arqw")

        db.collection("yemek").addSnapshotListener { value, error ->

            if(error != null) {
                Toast.makeText(this,error.localizedMessage, Toast.LENGTH_LONG).show()
            }else {

                if (value != null) {

                    if(!value.isEmpty) {
                        println("arqw")

                        val doc = value.documents
                        for (docc in doc) {


                            var gun = findViewById<TextView>(R.id.textView)
                            gun.text = docc.get("one").toString()
                            println("arqw31")

                            println(docc.get("one").toString())

                            var two = findViewById<TextView>(R.id.textView5)
                            two.text = docc.get("two").toString()

                            var q = findViewById<TextView>(R.id.textView6)
                            q.text = docc.get("g").toString()

                            var w = findViewById<TextView>(R.id.textView7)
                            w.text = docc.get("w").toString()

                            var e = findViewById<TextView>(R.id.textView8)
                            e.text = docc.get("e").toString()



                        }
                    }

                }


            }



        }

    }
}