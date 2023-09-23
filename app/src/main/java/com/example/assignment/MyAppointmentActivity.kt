package com.example.assignment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


class MyAppointmentActivity : BaseNavigationActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var appointmentAdapter: AppointmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_appointment)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        println("hi")
        supportActionBar?.title = "My Appointment"
        initToolbarAndNavigation()

        // 初始化RecyclerView
        recyclerView = findViewById(R.id.appointmentRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        // 获取预约数据（从Firebase数据库或其他地方）
        val appointmentList = getAppointmentData()

        // 创建并设置适配器
        appointmentAdapter = AppointmentAdapter(appointmentList)
        recyclerView.adapter = appointmentAdapter

    }

    private fun getAppointmentData(): List<Appointment> {

        val appID = ""
        // 在这里从Firebase数据库或其他地方获取预约数据，并返回一个包含预约对象的列表
        // 例如，从Firebase Firestore 获取数据
        val appointmentList = mutableListOf<Appointment>()
        // 获取 Firebase 实例
        val db = FirebaseFirestore.getInstance()

        // 假设您已经有一个用于存储预约信息的集合 "appointments"
        val appointmentsCollection = db.collection("appointment")

        appointmentsCollection.get().addOnSuccessListener { result ->
            for (document in result) {
                val doctorName = document.getString("doctorName") ?: "Default Doctor Name"
                val appointmentDate = document.getString("appointmentDate") ?: "Default Appointment Date"
                val appointmentTime = document.getString("appointmentTime") ?: "Default Appointment Time"
                val doctorProfile = document.getString("doctorProfile") ?: "Default Profile Picture"
                val condition = document.getString("condition") ?: "Default Condition"
                val age = document.getString("age") ?: "Default age"
                val gender = document.getString("gender") ?: "Default gender"
                val name = document.getString("name") ?: "Default name"
                val selectedDuration = document.getString("selectedDuration") ?: "Default selectedDuration"
                val selectedPackage = document.getString("selectedPackage") ?: "Default selectedPackage"
                val total = document.getString("total") ?: "Default total"
                val doctorBio = document.getString("doctorBio") ?: "Default doctorBio"
                val appID = document.id

                // 使用这些值来创建一个 Appointment 对象并添加到列表中
                val appointment = Appointment(doctorName, appointmentDate, appointmentTime, doctorProfile, condition, age, gender, name, selectedDuration, selectedPackage, total, doctorBio, appID)
                appointmentList.add(appointment)
            }
            // 更新 RecyclerView 以显示数据
            appointmentAdapter.notifyDataSetChanged()
        }.addOnFailureListener { exception ->
            // 处理失败的情况
            Log.e(TAG, "Error getting appointments", exception)
        }

        return appointmentList
    }
}
