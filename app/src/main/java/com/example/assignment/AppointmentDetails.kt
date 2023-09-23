package com.example.assignment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AppointmentDetails : BaseNavigationActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment_details)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        println("hi")
        supportActionBar?.title = "My Appointment"
        initToolbarAndNavigation()

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

        if (selectedPackage == "Call") {
            packageDetailsTextView.text = "Call with doctor"
        } else if (selectedPackage == "Email") {
            packageDetailsTextView.text = "Email messaging with doctor"
        } else if (selectedPackage == "Video Call") {
            packageDetailsTextView.text = "Video Call with doctor"
        }

        val appointmentButton = findViewById<Button>(R.id.appointmentButton)
        val currentDate = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val parsedAppointmentDate = dateFormat.parse(appointmentDate)

        // 获取当前时间
        val currentTime = Calendar.getInstance()
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val formattedCurrentTime = timeFormat.format(currentTime.time)

         //Start - 更改按钮的背景颜色
        if (appointmentDate != null && appointmentTime != null) {
            val todayDate = Calendar.getInstance()
            val selectedDateTime = Calendar.getInstance()
            val selectedDateArray = appointmentDate.split("/")
            selectedDateTime.set(Calendar.YEAR, selectedDateArray[0].toInt())
            selectedDateTime.set(Calendar.MONTH, selectedDateArray[1].toInt() - 1)
            selectedDateTime.set(Calendar.DAY_OF_MONTH, selectedDateArray[2].toInt())

            // 设置用户选择的时间
            val selectedTimeArray = appointmentTime.split(":")
            if (selectedTimeArray.size == 2) {
                val selectedHour = selectedTimeArray[0].toInt()
                val selectedMinute = selectedTimeArray[1].toInt()
                selectedDateTime.set(Calendar.HOUR_OF_DAY, selectedHour)
                selectedDateTime.set(Calendar.MINUTE, selectedMinute)
                selectedDateTime.set(Calendar.SECOND, 0)
            }//check date
            if (selectedDateTime.before(todayDate)) {
                //check time
                selectedDateTime.add(Calendar.MINUTE, 43)
                if (selectedDateTime.after(todayDate)) {

                }else{
                    appointmentButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFA59A9A"))
                }
            }
            else {
                appointmentButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFA59A9A"))
            }
         } else {
            appointmentButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFA59A9A"))
         }
         //End - 更改按钮的背景颜色


        // Start - 執行操作
        appointmentButton.setOnClickListener {

            if (appointmentDate != null && appointmentTime != null) {
                val todayDate = Calendar.getInstance()
                val selectedDateTime = Calendar.getInstance()
                val selectedDateArray = appointmentDate.split("/")
                selectedDateTime.set(Calendar.YEAR, selectedDateArray[0].toInt())
                selectedDateTime.set(Calendar.MONTH, selectedDateArray[1].toInt() - 1)
                selectedDateTime.set(Calendar.DAY_OF_MONTH, selectedDateArray[2].toInt())

                // 设置用户选择的时间
                val selectedTimeArray = appointmentTime.split(":")
                if (selectedTimeArray.size == 2) {
                    val selectedHour = selectedTimeArray[0].toInt()
                    val selectedMinute = selectedTimeArray[1].toInt()
                    selectedDateTime.set(Calendar.HOUR_OF_DAY, selectedHour)
                    selectedDateTime.set(Calendar.MINUTE, selectedMinute)
                    selectedDateTime.set(Calendar.SECOND, 0)
                }

                //check date
                if (selectedDateTime.before(todayDate)) {
                    //check time
                    selectedDateTime.add(Calendar.MINUTE, 15)
                    if (selectedDateTime.after(todayDate)) {
                        //correct date and time
                        when (selectedPackage) {
                            "Call" -> {
                                // 自动拨打电话给医生（模拟操作）
                                val phoneNumber = "0123456789"
                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                                startActivity(intent)
                            }

                            "Video Call" -> {
//                                // 启动发送短信应用（模拟操作）
//                                val phoneNumber = "0123456789"
//                                val smsText = "Hi Doctor"
//
//                                val smsUri = Uri.parse("smsto:$phoneNumber")
//                                val intent = Intent(Intent.ACTION_SENDTO, smsUri)
//                                intent.putExtra("sms_body", smsText)
//                                startActivity(intent)
                                // 启动 Google Meet 应用（如果已安装），或者打开 Google Meet 网页链接
                                val googleMeetUrl = "https://meet.google.com/spi-fjso-meg"

                                // 创建 Intent 打开 Google Meet 链接
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(googleMeetUrl))
                                startActivity(intent)
                            }

                            "Email" -> {
                                // 启动发送邮件应用（模拟操作）
                                val recipientEmail = "doctor@gmail.com"
                                val subject = "Hi Doctor"
                                val intent = Intent(Intent.ACTION_SEND)
                                intent.type = "text/plain"
                                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipientEmail))
                                intent.putExtra(Intent.EXTRA_SUBJECT, subject)
                                startActivity(intent)
                            }
                        }

                    }else{
                        appointmentButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFA59A9A"))
                        showToast("You have missed your appointment time.")
                    }
                } else {
                    appointmentButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFA59A9A"))
                    showToast("Please Wait Until Your Appointment Date and Time")
                }
            } else {
                appointmentButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFA59A9A"))
                showToast("Error")
            }
        }
        // End - 執行操作
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}



