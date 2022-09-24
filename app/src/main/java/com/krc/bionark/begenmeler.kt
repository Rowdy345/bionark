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
import com.krc.bionark.databinding.ActivityBegenmelerBinding
import com.krc.bionark.databinding.ActivityMainviewBinding

class begenmeler : AppCompatActivity() {

    private lateinit var binding: ActivityBegenmelerBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var db2: FirebaseFirestore
    private  var sayac = 0
    private  var begendimsayac = 0
    private  var begendisayac = 0
    private lateinit var postarraylist : ArrayList<mainlist>


    private  lateinit var feedAdapter : userscell
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBegenmelerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth

        db = Firebase.firestore
        db2 = Firebase.firestore


        postarraylist = ArrayList<mainlist>()


        getusers()

        binding.recbeg.layoutManager = LinearLayoutManager(this)



        feedAdapter = userscell(postarraylist)
        feedAdapter.setOnClickListener(object : userscell.onItemClickListener{
            override fun onItemClick(position: Int) {






            }


        })

        binding.recbeg.adapter = feedAdapter

        binding.imageButton2.setOnClickListener {



        }

        binding.imageButton.setOnClickListener {

            val intent = Intent(this@begenmeler, chatlist::class.java)

            startActivity(intent)

        }

        binding.imageButton3.setOnClickListener {

            val intent = Intent(this@begenmeler, profilim::class.java)

            startActivity(intent)

        }
        binding.imageButton31.setOnClickListener {

            val intent = Intent(this@begenmeler, mainview::class.java)

            startActivity(intent)

        }

        binding.button12.setOnClickListener {
            begendimsayac = 0
            begendisayac = 0
            getusers()

        }

        binding.button13.setOnClickListener {
            begendimsayac = 0
            begendisayac = 0
            getuserstwo()
        }


    }

    private  fun getusers() {
        println("agabe")
        postarraylist.clear()
        begendimsayac = 0
        begendisayac = 0
        binding.textView14.setText("Beğendiklerim ($begendimsayac)")
        db.collection("begendiklerim").whereEqualTo("ben",auth.currentUser?.email).addSnapshotListener { value, error ->
            println("agabe2")

            if(error != null) {
                Toast.makeText(this,error.localizedMessage, Toast.LENGTH_LONG).show()

            }else {

                if (value != null) {
                    println("agabe3")

                    if (!value.isEmpty) {
                        println("agabe4")

                        val doc = value.documents
                        for (docc in doc) {
                            println("agabe5")
                            begendimsayac ++

                            db2.collection("users")
                                .whereEqualTo("email", docc.get("o").toString())
                                .addSnapshotListener { value2, error ->

                                    if (error != null) {
                                        Toast.makeText(
                                            this,
                                            error.localizedMessage,
                                            Toast.LENGTH_LONG
                                        )
                                            .show()

                                    } else {

                                        if (value2 != null) {

                                            if (!value2.isEmpty) {
                                                println("agabe6")

                                                val doc2 = value2.documents
                                                for (docc2 in doc2) {
                                                    println("agabe7")
                                                    sayac ++
                                                    val post = mainlist(

                                                        docc2.get("email") as String,
                                                        docc2.get("isim") as String,
                                                        docc2.get("medeni") as String,
                                                        docc2.get("yas") as String,
                                                        docc2.get("kilo") as String,
                                                        docc2.get("sehir") as String,
                                                        docc2.get("cocuk") as String,
                                                        docc2.get("egitim") as String,
                                                        docc2.get("img") as String

                                                    )
                                                    println("agabe8"+post)
                                                    postarraylist.add(post)
                                                    println("agabe8"+postarraylist)

                                                    println("hopaldim3")


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


                    }
                    println("hopaldim4")


                }

                println("hopaldim5")

                feedAdapter.notifyDataSetChanged()
            }
            binding.textView14.setText("Beğenenler ($begendimsayac)")

            feedAdapter.notifyDataSetChanged()


        }



    }
    private  fun getuserstwo() {
        begendisayac = 0
        begendimsayac = 0

        binding.textView14.setText("Beğenenler ($begendisayac)")
        postarraylist.clear()

        println("agabe")
        db.collection("begendiklerim").whereEqualTo("o",auth.currentUser?.email).addSnapshotListener { value, error ->
            println("agabe2")

            if(error != null) {
                Toast.makeText(this,error.localizedMessage, Toast.LENGTH_LONG).show()

            }else {

                if (value != null) {
                    println("agabe3")

                    if (!value.isEmpty) {
                        println("agabe4")

                        val doc = value.documents
                        for (docc in doc) {
                            println("agabe5")
                            begendisayac ++
                            begendimsayac ++
                            db2.collection("users")
                                .whereEqualTo("email", docc.get("o").toString())
                                .addSnapshotListener { value2, error ->

                                    if (error != null) {
                                        Toast.makeText(
                                            this,
                                            error.localizedMessage,
                                            Toast.LENGTH_LONG
                                        )
                                            .show()

                                    } else {

                                        if (value2 != null) {

                                            if (!value2.isEmpty) {
                                                println("agabe6")

                                                val doc2 = value2.documents
                                                for (docc2 in doc2) {
                                                    println("agabe7")
                                                    sayac ++
                                                    val post = mainlist(

                                                        docc2.get("email") as String,
                                                        docc2.get("isim") as String,
                                                        docc2.get("medeni") as String,
                                                        docc2.get("yas") as String,
                                                        docc2.get("kilo") as String,
                                                        docc2.get("sehir") as String,
                                                        docc2.get("cocuk") as String,
                                                        docc2.get("egitim") as String,
                                                        docc2.get("img") as String

                                                    )
                                                    println("agabe8"+post)
                                                    postarraylist.add(post)
                                                    println("agabe8"+postarraylist)

                                                    println("hopaldim3")


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


                    }
                    println("hopaldim4")


                }
                binding.textView14.setText("Beğenenler ($begendisayac)")


                println("hopaldim5")

                feedAdapter.notifyDataSetChanged()
            }
            feedAdapter.notifyDataSetChanged()


        }



    }

}