package com.krc.bionark

import android.app.Activity
import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.krc.bionark.databinding.ActivityProfilimBinding
import java.io.IOException
import java.util.*
import com.squareup.picasso.Picasso


class profilim : AppCompatActivity() {

    private  lateinit var binding : ActivityProfilimBinding
    private  lateinit var auth : FirebaseAuth

    private var kacinci = "0"
    private var docid = "0"
    private var durum= 0
    private  lateinit var filename : String
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private lateinit var newurl : String

    lateinit var filepath : Uri
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    private lateinit var db : FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilimBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        db = Firebase.firestore


        getdata()



        binding.imageButton3.setOnClickListener {



        }

        binding.imageButton.setOnClickListener {

            val intent = Intent(this@profilim, chatlist::class.java)

            startActivity(intent)

        }

        binding.imageButton2.setOnClickListener {

            val intent = Intent(this@profilim, begenmeler::class.java)

            startActivity(intent)

        }
        binding.imageButton31.setOnClickListener {

            val intent = Intent(this@profilim, mainview::class.java)

            startActivity(intent)

        }



        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        binding.button10.setOnClickListener {
            startfFileChooser()

            kacinci = "1"
        }
        binding.button11.setOnClickListener {
            startfFileChooser()

            kacinci = "2"
        }

        binding.editTextTextPersonName5.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val washingtonRef = db.collection("users").document(docid)

                washingtonRef
                    .update("medeni", binding.editTextTextPersonName5.text.toString())
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })

        binding.editTextTextPersonName5.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val washingtonRef = db.collection("users").document(docid)

                washingtonRef
                    .update("medeni", binding.editTextTextPersonName5.text.toString())
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })

        binding.editTextTextPersonName9.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val washingtonRef = db.collection("users").document(docid)

                washingtonRef
                    .update("yas", binding.editTextTextPersonName9.text.toString())
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })

        binding.editTextTextPersonName6.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val washingtonRef = db.collection("users").document(docid)

                washingtonRef
                    .update("egitim", binding.editTextTextPersonName6.text.toString())
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })

        binding.editTextTextPersonName7.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val washingtonRef = db.collection("users").document(docid)

                washingtonRef
                    .update("boy", binding.editTextTextPersonName7.text.toString())
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })

        binding.editTextTextPersonName8.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val washingtonRef = db.collection("users").document(docid)

                washingtonRef
                    .update("kilo", binding.editTextTextPersonName8.text.toString())
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })

        binding.editTextTextPersonName10.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val washingtonRef = db.collection("users").document(docid)

                washingtonRef
                    .update("sehir", binding.editTextTextPersonName10.text.toString())
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })

        binding.editTextTextPersonName11.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val washingtonRef = db.collection("users").document(docid)

                washingtonRef
                    .update("cocuk", binding.editTextTextPersonName11.text.toString())
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })

    }



    @RequiresApi(Build.VERSION_CODES.N)
    private fun uploadfile() {
        if (filepath!= null) {
            var pd = ProgressDialog(this)
            pd.setTitle("Yükleniyor")
            pd.show()
            val  formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_SS", Locale.getDefault())
            val now = Date()
            filename = formatter.format(now)
            var imageRef = FirebaseStorage.getInstance().reference.child("user_image/$filename")
            imageRef.putFile(filepath).addOnSuccessListener {
                pd.dismiss()

                imageRef.downloadUrl.addOnSuccessListener {
                    newurl = it.toString()
                    // Add a new document with a generated id.
                    if(kacinci == "1") {
                        val washingtonRef = db.collection("users").document(docid)

                        washingtonRef
                            .update("img", newurl)
                            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
                    }
                    if(kacinci == "2") {
                        val washingtonRef = db.collection("users").document(docid)

                        washingtonRef
                            .update("img2", newurl)
                            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
                    }

                }

            }.addOnFailureListener{

            }
        }
    }

    private fun startfFileChooser() {
        var i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(i,"Görsel Seç"),1111)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode ==1111 && resultCode == Activity.RESULT_OK && data != null) {
            filepath = data.data!!
            var bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filepath)
            if(kacinci == "1"){
                binding.imageView.setImageBitmap(bitmap)
                uploadfile()

            }
            if(kacinci == "2"){
                binding.imageView8.setImageBitmap(bitmap)
                uploadfile()

            }
        }
    }




    private  fun getdata() {



        db.collection("users").whereEqualTo("email",auth.currentUser?.email ).addSnapshotListener { value, error ->

            if(error != null) {
                Toast.makeText(this,error.localizedMessage, Toast.LENGTH_LONG).show()
            }else {

                if (value != null) {

                    if(!value.isEmpty) {

                        val doc = value.documents
                        for (docc in doc) {


                            docid = docc.id



                        }
                    }

                }


            }
            if (durum == 0) {
                gettwo()
                durum = 1
            }


        }

    }

    fun gettwo() {
        val docRef = db.collection("users").document(docid)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    binding.textView7.text = document.get("isim") as String
                    binding.textView9.text = document.get("kredi") as String + "Kredi"
                    binding.editTextTextPersonName5.setText(document.get("medeni") as String)
                    binding.editTextTextPersonName9.setText(document.get("yas") as String)
                    binding.editTextTextPersonName6.setText(document.get("egitim") as String)
                    binding.editTextTextPersonName7.setText(document.get("boy") as String)
                    binding.editTextTextPersonName8.setText(document.get("kilo") as String)
                    binding.editTextTextPersonName10.setText(document.get("sehir") as String)
                    binding.editTextTextPersonName11.setText(document.get("cocuk") as String)
                    Picasso.get().load(document.get("img")as String).into(binding.imageView)
                    Picasso.get().load(document.get("img2")as String).into(binding.imageView8)


                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }


}