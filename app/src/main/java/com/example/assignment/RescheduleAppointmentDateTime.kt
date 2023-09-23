package com.example.assignment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RescheduleAppointmentDateTime : BaseNavigationActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reschedule)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Reschedule Appointment Date And Time"
        initToolbarAndNavigation()


        //從list拿指定的ID
        val appointmentDocId = intent.getStringExtra("appointmentDocId")
        println(appointmentDocId)

        //從firebase通過ID拿appointment date and time
        val db = FirebaseFirestore.getInstance()
        val appointmentsCollection = db.collection("appointment")
        if (appointmentDocId != null) {
            appointmentsCollection.document(appointmentDocId).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        // 获取预约的日期和时间
                        val appointmentDate = document.getString("appointmentDate")
                        val appointmentTime = document.getString("appointmentTime")
                        println(appointmentTime)

                        // 将预约日期设置为默认日期
                        val selectedDateView = findViewById<CalendarView>(R.id.calendarView)
                        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                        val calendar = Calendar.getInstance()

                        // 将 appointmentDate 解析为日期对象
                        val appointmentDateObj = dateFormat.parse(appointmentDate)
                        if (appointmentDateObj != null) {
                            calendar.time = appointmentDateObj
                            val defaultDateInMillis = calendar.timeInMillis
                            // 设置默认日期
                            selectedDateView.date = defaultDateInMillis

                            //Get Button
                            val timeButton1 = findViewById<Button>(R.id.timeButton1)
                            val timeButton2 = findViewById<Button>(R.id.timeButton2)
                            val timeButton3 = findViewById<Button>(R.id.timeButton3)
                            val timeButton4 = findViewById<Button>(R.id.timeButton4)
                            val timeButton5 = findViewById<Button>(R.id.timeButton5)
                            val timeButton6 = findViewById<Button>(R.id.timeButton6)
                            val timeButton7 = findViewById<Button>(R.id.timeButton7)
                            val timeButton8 = findViewById<Button>(R.id.timeButton8)
                            val timeButton9 = findViewById<Button>(R.id.timeButton9)
                            val timeButton10 = findViewById<Button>(R.id.timeButton10)
                            val timeButton11 = findViewById<Button>(R.id.timeButton11)
                            val timeButton12 = findViewById<Button>(R.id.timeButton12)

                            //從firebase拿時間後，確認是哪一個button
                            var selectedTimeButton: Button? = null
                            // 根据 appointmentTime 的值设置默认选中的按钮
                            when (appointmentTime) {
                                "9:00" -> {
                                    selectedTimeButton = timeButton1
                                }

                                "9:30" -> {
                                    selectedTimeButton = timeButton2
                                }

                                "10:00" -> {
                                    selectedTimeButton = timeButton3
                                }

                                "10:30" -> {
                                    selectedTimeButton = timeButton4
                                }

                                "11:00" -> {
                                    selectedTimeButton = timeButton5
                                }

                                "11:30" -> {
                                    selectedTimeButton = timeButton6
                                }

                                "15:00" -> {
                                    selectedTimeButton = timeButton7
                                }

                                "15:30" -> {
                                    selectedTimeButton = timeButton8
                                }

                                "16:00" -> {
                                    selectedTimeButton = timeButton9
                                }

                                "16:30" -> {
                                    selectedTimeButton = timeButton10
                                }

                                "17:00" -> {
                                    selectedTimeButton = timeButton11
                                }

                                "17:30" -> {
                                    selectedTimeButton = timeButton12
                                }
                            }
                            // 设置默认选中按钮的背景颜色
                            if (selectedTimeButton != null) {
                                selectedTimeButton.setBackgroundColor(Color.GRAY)
                            }

                            //當用戶重新選時間的時候
                            var selectedTime = ""
                            // 为每个按钮设置点击事件监听器
                            timeButton1.setOnClickListener {
                                // 检查是否有选中的按钮，如果有则将其恢复原本颜色
                                selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))

                                // 设置新的选中按钮和颜色
                                selectedTimeButton = timeButton1
                                timeButton1.setBackgroundColor(Color.GRAY)
                                selectedTime = "9:00" // 用户选择了时间按钮1（9:00 AM）
                            }
                            timeButton2.setOnClickListener {
                                selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
                                selectedTimeButton = timeButton2
                                timeButton2.setBackgroundColor(Color.GRAY)
                                selectedTime = "9:30" // 用户选择了时间按钮2（9:30 AM）
                            }
                            timeButton3.setOnClickListener {
                                selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
                                selectedTimeButton = timeButton3
                                timeButton3.setBackgroundColor(Color.GRAY)
                                selectedTime = "10:00" // 用户选择了时间按钮1（10:00 AM）
                            }
                            timeButton4.setOnClickListener {
                                selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
                                selectedTimeButton = timeButton4
                                timeButton4.setBackgroundColor(Color.GRAY)
                                selectedTime = "10:30" // 用户选择了时间按钮2（10:30 AM）
                            }
                            timeButton5.setOnClickListener {
                                selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
                                selectedTimeButton = timeButton5
                                timeButton5.setBackgroundColor(Color.GRAY)
                                selectedTime = "11:00" // 用户选择了时间按钮1（11:00 AM）
                            }
                            timeButton6.setOnClickListener {
                                selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
                                selectedTimeButton = timeButton6
                                timeButton6.setBackgroundColor(Color.GRAY)
                                selectedTime = "11:30" // 用户选择了时间按钮2（11:30 AM）
                            }
                            timeButton7.setOnClickListener {
                                selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
                                selectedTimeButton = timeButton7
                                timeButton7.setBackgroundColor(Color.GRAY)
                                selectedTime = "15:00" // 用户选择了时间按钮1（15:00 PM）
                            }
                            timeButton8.setOnClickListener {
                                selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
                                selectedTimeButton = timeButton8
                                timeButton8.setBackgroundColor(Color.GRAY)
                                selectedTime = "15:30" // 用户选择了时间按钮2（15:30 PM）
                            }
                            timeButton9.setOnClickListener {
                                selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
                                selectedTimeButton = timeButton9
                                timeButton9.setBackgroundColor(Color.GRAY)
                                selectedTime = "16:00" // 用户选择了时间按钮1（16:00 PM）
                            }
                            timeButton10.setOnClickListener {
                                selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
                                selectedTimeButton = timeButton10
                                timeButton10.setBackgroundColor(Color.GRAY)
                                selectedTime = "16:30" // 用户选择了时间按钮2（16:30 PM）
                            }
                            timeButton11.setOnClickListener {
                                selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
                                selectedTimeButton = timeButton11
                                timeButton11.setBackgroundColor(Color.GRAY)
                                selectedTime = "17:00" // 用户选择了时间按钮1（17:00 PM）
                            }
                            timeButton12.setOnClickListener {
                                selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
                                selectedTimeButton = timeButton12
                                timeButton12.setBackgroundColor(Color.GRAY)
                                selectedTime = "17:30" // 用户选择了时间按钮2（17:30 PM）
                            }

                            //做Validation
                            val selectedDateView = findViewById<CalendarView>(R.id.calendarView)
                            var formattedDate = "" // 将formattedDate移动到外部作用域

                            val selectedDate = Calendar.getInstance()
                            val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                            selectedDateView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                                // 当用户选择日期时，这个回调会被调用
                                // year、month、dayOfMonth 分别是用户选择的年、月、日
                                // 创建日期对象并格式化为字符串
                                selectedDate.set(year, month, dayOfMonth)
                                formattedDate = dateFormat.format(selectedDate.time)

                                // 获取今天的日期
                                val todayDate = Calendar.getInstance()

                                //不能選之前的日期在calander
                                if (selectedDate.before(todayDate)) {
                                    // 用户选择了之前的日期，显示错误消息
                                    showToast("Cannot select a past date.")
                                    // 重置日期为默认值
                                    selectedDateView.date = todayDate.timeInMillis
                                }

                                //當選了日期和時間后，按next要check時間
                                val buttonConfirm = findViewById<Button>(R.id.buttonConfirm)
                                buttonConfirm.setOnClickListener {
                                    val selectedDateTime = Calendar.getInstance()
                                    selectedDateTime.timeInMillis = selectedDateView.date // 用户选择的日期

                                    // 设置用户选择的时间
                                    val selectedTimeArray = selectedTime.split(":")
                                    if (selectedTimeArray.size == 2) {
                                        val selectedHour = selectedTimeArray[0].toInt()
                                        val selectedMinute = selectedTimeArray[1].toInt()
                                        selectedDateTime.set(Calendar.HOUR_OF_DAY, selectedHour)
                                        selectedDateTime.set(Calendar.MINUTE, selectedMinute)
                                        selectedDateTime.set(Calendar.SECOND, 0)
                                    }

                                    val currentDate = Calendar.getInstance()
                                    if (selectedDateTime.after(currentDate) || selectedDate.after(currentDate)) {
                                        // 创建一个包含要更新的字段和新值的映射
                                        val updatedData = mapOf(
                                            "appointmentDate" to formattedDate,
                                            "appointmentTime" to selectedTime
                                        )

                                        // 获取Firebase Firestore实例
                                        val db = FirebaseFirestore.getInstance()

                                        // 指定要更新的文档ID，并更新指定的字段
                                        db.collection("appointment")
                                            .document(appointmentDocId)
                                            .update(updatedData) // 使用update()方法来更新指定的字段
                                            .addOnSuccessListener {
                                                // 更新成功的处理
                                                Toast.makeText(
                                                    this,
                                                    "Appointment date and time updated in Firebase",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                            .addOnFailureListener { e ->
                                                // 更新失败的处理
                                                Toast.makeText(
                                                    this,
                                                    "Error updating appointment date and time: $e",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }

                                        //跳回去my appointment
                                        val intent = Intent(
                                            this@RescheduleAppointmentDateTime,
                                            MyAppointmentActivity::class.java
                                        )
                                        startActivity(intent)
                                    } else {
                                        // 用户选择的日期或时间无效，显示错误消息
                                        showToast("Invalid Date and Time.")
                                    }
                                }
                            }
                        }
                    } else {
                        // 处理文档不存在的情况
                    }
                }
                .addOnFailureListener { exception ->
                    // 处理查询失败的情况
                }
        } else {
            showToast("No such appointment ID")
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
