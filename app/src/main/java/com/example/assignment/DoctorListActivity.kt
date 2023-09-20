package com.example.assignment

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class DoctorListActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_doctor_list) // 使用正确的布局文件名



        list()
    }

    fun list() {
        val doctorCollection = db.collection("/doctor")
        doctorCollection.get().addOnSuccessListener { querySnapshot ->
            val items = querySnapshot.documents.map { document ->
                val doctorBio = document.getString("doctorBio") ?: ""
                val doctorName = document.getString("doctorName") ?: ""
                val imageUrl = document.getString("doctorPic") ?: ""
                Model.ListItem(doctorName, doctorBio, imageUrl)  // Corrected the sequence
            }
            val adapter = Adapter(this, items)
            val listView: ListView = findViewById(R.id.DiseaselistView)
            listView.adapter = adapter
            listView.setOnItemClickListener { parent, view, position, id ->
                val selectedItem = items[position]
                val sharedPreferences = getSharedPreferences("DoctorPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("doctorName", selectedItem.doctorName)
                editor.putString("doctorBio", selectedItem.doctorBio)
                editor.putString("doctorProfile", selectedItem.imageUrl)
                editor.apply()

                val intent = Intent(this@DoctorListActivity, DoctorDetailsActivity::class.java)
                startActivity(intent)
                //Toast.makeText(this, selectedItem.doctorName, Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener { exception ->
            Log.d(TAG, "Error getting documents: ", exception)
        }
    }
}