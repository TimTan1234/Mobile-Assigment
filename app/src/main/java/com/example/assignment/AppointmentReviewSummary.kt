package com.example.assignment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore

class AppointmentReviewSummary : BaseNavigationActivity() {
    private lateinit var appointmentDateTextView: TextView
    private lateinit var appointmentTimeTextView: TextView
    private lateinit var packageTextView: TextView
    private lateinit var durationTextView: TextView
    private lateinit var totalTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment_review_summary)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        println("hi")
        supportActionBar?.title = "Appointment Review Summary"
        initToolbarAndNavigation()

        // Initialize TextViews
        appointmentDateTextView = findViewById(R.id.date)
        appointmentTimeTextView = findViewById(R.id.time)
        durationTextView = findViewById(R.id.duration)
        packageTextView = findViewById(R.id.packageAppointment)
        totalTextView = findViewById(R.id.total)

        // 获取信息从SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val appointmentDate = sharedPreferences.getString("appointmentDate", "")
        val appointmentTime = sharedPreferences.getString("appointmentTime", "")
        val selectedDuration = sharedPreferences.getString("duration", "")
        val selectedPackage = sharedPreferences.getString("package", "")
        val paymentMethod = sharedPreferences.getString("paymentMethod", "")

        // 获取信息从SharedPreferences
        val sharedPreferences2 = getSharedPreferences("DoctorPrefs", Context.MODE_PRIVATE)
        val doctorName = sharedPreferences2.getString("doctorName", "")
        val doctorBio = sharedPreferences2.getString("doctorBio", "")
        val doctorProfile = sharedPreferences2.getString("doctorProfile", "")

        // 在适当的TextView显示信息
        val doctorNameTextView = findViewById<TextView>(R.id.doctorName)
        val doctorInfoTextView = findViewById<TextView>(R.id.doctorBio)
        val doctorImageView = findViewById<ImageView>(R.id.doctorImage)
        val appointmentDateTextView = findViewById<TextView>(R.id.date)
        val appointmentTimeTextView = findViewById<TextView>(R.id.time)
        val durationTextView = findViewById<TextView>(R.id.duration)
        val packageTextView = findViewById<TextView>(R.id.packageAppointment)
        val amountTextView = findViewById<TextView>(R.id.amount)
        val durationCalculateTextView = findViewById<TextView>(R.id.durationCalculate)
        val totalTextView = findViewById<TextView>(R.id.total)
        val paymentTextView = findViewById<TextView>(R.id.payment)
        val paymentImageView = findViewById<ImageView>(R.id.paymentImageView)

        doctorNameTextView.text = doctorName
        doctorInfoTextView.text = doctorBio
        // Load the image into the ImageView using Glide
        Glide.with(this)
            .load(doctorProfile) // Pass the image URL here
            .into(doctorImageView)
        appointmentDateTextView.text = appointmentDate
        appointmentTimeTextView.text = appointmentTime
        durationTextView.text = selectedDuration
        packageTextView.text = selectedPackage
        paymentTextView.text = paymentMethod

        // 根据selectedPackage设置amountTextView的文本
        when (selectedPackage) {
            "Video Call" -> amountTextView.text = "50"
            "Call" -> amountTextView.text = "40"
            "Email" -> amountTextView.text = "20"
            else -> amountTextView.text = "" // 默认情况，可以设置为空或其他值
        }

        when (selectedDuration) {
            "30 minutes" -> {
                val amount = amountTextView.text.toString().toInt()
                durationCalculateTextView.text = "1*$amount"
                totalTextView.text = (1 * amount).toString()
            }

            "1 hour" -> {
                val amount = amountTextView.text.toString().toInt()
                durationCalculateTextView.text = "2*$amount"
                totalTextView.text = (2 * amount).toString()
            }

            "1 hour 30 minutes" -> {
                val amount = amountTextView.text.toString().toInt()
                durationCalculateTextView.text = "3*$amount"
                totalTextView.text = (3 * amount).toString()
            }
            // 其他情况可以继续添加
            else -> {
                durationCalculateTextView.text = ""
            }
        }

        if (paymentMethod == "Tngo Wallet") {
            // 用户选择了 Tngo Wallet，设置对应的图片资源
            paymentImageView.setImageResource(R.drawable.tngo)
        } else if(paymentMethod == "PayPal") {
            paymentImageView.setImageResource(R.drawable.paypal)
        }else if(paymentMethod == "Google Pay"){
            paymentImageView.setImageResource(R.drawable.google)
        }else{
            paymentImageView.visibility = View.GONE // 隐藏 ImageView
        }



        val buttonConfirm = findViewById<Button>(R.id.buttonConfirm)

        buttonConfirm.setOnClickListener {
            // 创建一个AlertDialog.Builder
            val builder = AlertDialog.Builder(this@AppointmentReviewSummary)
            builder.setTitle("Confirmation Message")
            builder.setMessage("Are you sure want to make this appointment?")

            // 添加确认按钮
            builder.setPositiveButton("Yes") { dialog, which ->
                // 上传预约信息到 Firebase
                uploadAppointmentToFirebase()

                // 启动主活动或执行其他操作
                val intent = Intent(this@AppointmentReviewSummary, MainActivity::class.java)
                startActivity(intent)
            }

            // 添加取消按钮
            builder.setNegativeButton("No") { dialog, which ->
                // 在这里执行取消后的操作，或者不执行任何操作
                dialog.dismiss()
            }

            // 显示AlertDialog
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }

    private fun uploadAppointmentToFirebase() {
        // 获取用户信息
        val sharedPreferences = getSharedPreferences("PatientPrefs", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        val age = sharedPreferences.getString("age", "")
        val condition = sharedPreferences.getString("condition", "")
        val gender = sharedPreferences.getString("gender", "")

        // 获取其他信息
        val appointmentDate = appointmentDateTextView.text.toString()
        val appointmentTime = appointmentTimeTextView.text.toString()
        val selectedPackage = packageTextView.text.toString()
        val selectedDuration = durationTextView.text.toString()
        val total = totalTextView.text.toString()

        val sharedPreferences2 = getSharedPreferences("DoctorPrefs", Context.MODE_PRIVATE)
        val doctorName = sharedPreferences2.getString("doctorName", "")
        val doctorProfile = sharedPreferences2.getString("doctorProfile", "")
        val doctorBio = sharedPreferences2.getString("doctorBio", "")

        // 获取Firebase Firestore实例
        val db = FirebaseFirestore.getInstance()

        // 创建一个包含预约信息的Map
        val appointmentInfo = hashMapOf(
            "name" to name,
            "age" to age,
            "condition" to condition,
            "gender" to gender,
            "appointmentDate" to appointmentDate,
            "appointmentTime" to appointmentTime,
            "selectedPackage" to selectedPackage,
            "selectedDuration" to selectedDuration,
            "total" to total,
            "doctorProfile" to doctorProfile,
            "doctorName" to doctorName,
            "doctorBio" to doctorBio
        )

        // 将信息上传到Firebase，Firebase将自动生成唯一的文档ID
        db.collection("appointment")

            .add(appointmentInfo) // 使用add()方法，Firebase会自动生成唯一的文档ID
            .addOnSuccessListener { documentReference ->
                // 上传成功的处理
                val newAppointmentId = documentReference.id // 获取新文档的ID
                Toast.makeText(this, "Appointment details saved to Firebase with ID: $newAppointmentId", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                // 上传失败的处理
                Toast.makeText(this, "Error saving appointment details: $e", Toast.LENGTH_SHORT).show()
            }

    }

}