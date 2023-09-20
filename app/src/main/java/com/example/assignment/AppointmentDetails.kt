package com.example.assignment

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class AppointmentDetails  : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment_details)

        // 获取传递的数据
        val doctorName = intent.getStringExtra("doctorName")
        val appointmentDate = intent.getStringExtra("appointmentDate")
        val appointmentTime = intent.getStringExtra("appointmentTime")
        val doctorProfile = intent.getStringExtra("doctorProfile")
        val age = intent.getStringExtra("age")
        val condition = intent.getStringExtra("condition")
        val gender = intent.getStringExtra("gender")
        val fullname = intent.getStringExtra("name")
        val selectedDuration = intent.getStringExtra("selectedDuration")
        val selectedPackage = intent.getStringExtra("selectedPackage")
        val total = intent.getStringExtra("total")
        val doctorBio = intent.getStringExtra("doctorBio")


            // 在适当的TextView和ImageView上显示数据
            val doctorNameTextView = findViewById<TextView>(R.id.doctorName)
            val appointmentDateTextView = findViewById<TextView>(R.id.appointmentDateTextView)
            val appointmentTimeTextView = findViewById<TextView>(R.id.appointmentTimeTextView)
            val doctorImageView = findViewById<ImageView>(R.id.doctorImageView)
            val doctorBioTextView = findViewById<TextView>(R.id.doctorBio)
            val nameTextView = findViewById<TextView>(R.id.fullName)
            val genderTextView = findViewById<TextView>(R.id.gender)
            val ageTextView = findViewById<TextView>(R.id.age)
            val conditionTextView = findViewById<TextView>(R.id.problem)
            val packageTextView = findViewById<TextView>(R.id.packageName)
            val totalTextView = findViewById<TextView>(R.id.price)
            val packageDurationTextView = findViewById<TextView>(R.id.packageDuration)
            val packageDetailsTextView = findViewById<TextView>(R.id.packageDetails)


            doctorNameTextView.text = doctorName
            appointmentDateTextView.text = appointmentDate
            appointmentTimeTextView.text = appointmentTime
            Glide.with(this)
                .load(doctorProfile)
                .placeholder(R.drawable.profile)
                .error(R.drawable.profile)
                .into(doctorImageView)
            doctorBioTextView.text = doctorBio
            nameTextView.text = fullname
            genderTextView.text = gender
            ageTextView.text = age
            conditionTextView.text = condition
            packageTextView.text = selectedPackage
            totalTextView.text = "$" + total
            packageDurationTextView.text = "/" + selectedDuration

            if (selectedPackage == "Call"){
                packageDetailsTextView.text = "Call with doctor"
            }else if(selectedPackage == "Email"){
                packageDetailsTextView.text = "Email messaging with doctor"
            }else if(selectedPackage == "Video Call"){
                packageDetailsTextView.text = "Video call with doctor"
            }
        }

    }
