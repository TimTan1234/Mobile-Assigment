package com.example.assignment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide

class DoctorDetailsActivity : BaseNavigationActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        println("hi")
        supportActionBar?.title = "Doctor Details"
        initToolbarAndNavigation()

        // 获取信息从SharedPreferences
        val sharedPreferences = getSharedPreferences("DoctorPrefs", Context.MODE_PRIVATE)
        val doctorName = sharedPreferences.getString("doctorName", "")
        val doctorBio = sharedPreferences.getString("doctorBio", "")
        val doctorProfile = sharedPreferences.getString("doctorProfile", "")


        // Find the TextViews and ImageView in the layout
        val doctorNameTextView = findViewById<TextView>(R.id.doctorName)
        val doctorInfoTextView = findViewById<TextView>(R.id.doctorInfo)
        val doctorImageView = findViewById<ImageView>(R.id.doctorImage)

        // Set the doctor's information in the layout
        doctorNameTextView.text = doctorName
        doctorInfoTextView.text = doctorBio
        // Load the image into the ImageView using Glide
        Glide.with(this)
            .load(doctorProfile) // Pass the image URL here
            .into(doctorImageView)

        // Set a click listener for the appointment button if needed
        val appointmentButton = findViewById<Button>(R.id.appointmentButton)
        appointmentButton.setOnClickListener {
            val intent = Intent(this@DoctorDetailsActivity, AppointmentDateAndTime::class.java)
            startActivity(intent)
        }
    }
}
