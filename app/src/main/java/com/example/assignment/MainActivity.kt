package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 获取查看医生列表按钮
        val doctorListButton = findViewById<Button>(R.id.doctorListButton)

        // 添加按钮点击事件监听器
        doctorListButton.setOnClickListener(View.OnClickListener {
            // 创建一个Intent来启动DoctorListActivity
            val intent = Intent(this@MainActivity, DoctorListActivity::class.java)

            // 启动DoctorListActivity
            startActivity(intent)
        })

        val myappointmentButton = findViewById<Button>(R.id.myAppointmentButton)

        myappointmentButton.setOnClickListener {
            val intent = Intent(this@MainActivity, MyAppointmentActivity::class.java)
            startActivity(intent)
        }

    }
}