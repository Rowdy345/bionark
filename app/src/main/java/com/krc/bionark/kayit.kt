package com.krc.bionark

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.krc.bionark.databinding.ActivityKayitBinding

class kayit : AppCompatActivity() {

    private  lateinit var binding : ActivityKayitBinding
        private  lateinit var auth : FirebaseAuth
        private lateinit var db : FirebaseFirestore

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityKayitBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)
        auth = Firebase.auth

        db = Firebase.firestore



        binding.button2.setOnClickListener {

            if (binding.editTextTextEmailAddress.text.isEmpty()) {
                binding.textView2.setTextColor(Color.RED)
            }

            else {

                auth.createUserWithEmailAndPassword(binding.editTextTextEmailAddress.text.toString(), binding.editTextTextEmailAddress2.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(ContentValues.TAG, "Kayıt Başarılı")
                            gekayit()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(ContentValues.TAG, "${task.exception.toString()}", task.exception)
                            Toast.makeText(baseContext, "${task.exception?.message}",
                                Toast.LENGTH_SHORT).show()
                        }
                    }


            }


        }

            binding.imageButton.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)

                startActivity(intent)
            }


    }


    private  fun gekayit() {
        val data = hashMapOf(

            "isim" to binding.editTextTextPersonName.text.toString(),
            "yas" to binding.editTextTextPersonName2.text.toString(),
            "kilo" to binding.editTextTextPersonName3.text.toString(),
            "email" to binding.editTextTextEmailAddress.text.toString(),
            "boy" to "-",
            "cocuk" to "-",
            "img" to "https://firebasestorage.googleapis.com/v0/b/bionark-e466a.appspot.com/o/image-removebg-preview%20(12).png?alt=media&token=43f9bbed-517b-4bdc-afe5-3060da381dad",
            "img2" to "https://firebasestorage.googleapis.com/v0/b/bionark-e466a.appspot.com/o/image-removebg-preview%20(12).png?alt=media&token=43f9bbed-517b-4bdc-afe5-3060da381dad",
            "kredi" to "0",
            "kredi_last" to "-",
            "medeni" to "-",
            "sehir" to "-",
            "egitim" to "-",
            "bio" to "Biyografi Girilmedi",

        )

        db.collection("users")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "Kayıt Başarılı")


                val intent = Intent(this, MainActivity::class.java)

                startActivity(intent)
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }
    }


}