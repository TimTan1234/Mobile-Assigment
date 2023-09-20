package com.example.assignment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
class AppointmentDateAndTime : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment_date_time)

        val selectedDateView = findViewById<CalendarView>(R.id.calendarView)
        var formattedDate = "" // 将formattedDate移动到外部作用域
        var selectedTime = "" // 保存用户选择的时间

        // 设置默认日期为当前日期
        val defaultDate = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        formattedDate = dateFormat.format(defaultDate.time)

        selectedDateView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // 当用户选择日期时，这个回调会被调用
            // year、month、dayOfMonth 分别是用户选择的年、月、日

            // 创建日期对象并格式化为字符串
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)
            formattedDate = dateFormat.format(selectedDate.time)
        }

        // 获取所有时间按钮的引用
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
        // 以此类推，获取其他按钮的引用

        // 默认选中的按钮为9:00 AM按钮
        var selectedTimeButton: Button = timeButton1 // 默认选中9:00 AM按钮
        selectedTimeButton.setBackgroundColor(Color.GRAY) // 设置默认选中按钮的背景颜色
        selectedTime = "9:00 AM" // 设置默认时间



        // 为每个按钮设置点击事件监听器
        timeButton1.setOnClickListener {
            // 检查是否有选中的按钮，如果有则将其恢复原本颜色
            selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))

            // 设置新的选中按钮和颜色
            selectedTimeButton = timeButton1
            timeButton1.setBackgroundColor(Color.GRAY)
            selectedTime = "9:00 AM" // 用户选择了时间按钮1（9:00 AM）
        }
        timeButton2.setOnClickListener {
            selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
            selectedTimeButton = timeButton2
            timeButton2.setBackgroundColor(Color.GRAY)
            selectedTime = "9:30 AM" // 用户选择了时间按钮2（9:30 AM）
        }
        timeButton3.setOnClickListener {
            selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
            selectedTimeButton = timeButton3
            timeButton3.setBackgroundColor(Color.GRAY)
            selectedTime = "10:00 AM" // 用户选择了时间按钮1（10:00 AM）
        }
        timeButton4.setOnClickListener {
            selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
            selectedTimeButton = timeButton4
            timeButton4.setBackgroundColor(Color.GRAY)
            selectedTime = "10:30 AM" // 用户选择了时间按钮2（10:30 AM）
        }
        timeButton5.setOnClickListener {
            selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
            selectedTimeButton = timeButton5
            timeButton5.setBackgroundColor(Color.GRAY)
            selectedTime = "11:00 AM" // 用户选择了时间按钮1（11:00 AM）
        }
        timeButton6.setOnClickListener {
            selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
            selectedTimeButton = timeButton6
            timeButton6.setBackgroundColor(Color.GRAY)
            selectedTime = "11:30 AM" // 用户选择了时间按钮2（11:30 AM）
        }
        timeButton7.setOnClickListener {
            selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
            selectedTimeButton = timeButton7
            timeButton7.setBackgroundColor(Color.GRAY)
            selectedTime = "15:00 PM" // 用户选择了时间按钮1（15:00 PM）
        }
        timeButton8.setOnClickListener {
            selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
            selectedTimeButton = timeButton8
            timeButton8.setBackgroundColor(Color.GRAY)
            selectedTime = "15:30 PM" // 用户选择了时间按钮2（15:30 PM）
        }
        timeButton9.setOnClickListener {
            selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
            selectedTimeButton = timeButton9
            timeButton9.setBackgroundColor(Color.GRAY)
            selectedTime = "16:00 PM" // 用户选择了时间按钮1（16:00 PM）
        }
        timeButton10.setOnClickListener {
            selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
            selectedTimeButton = timeButton10
            timeButton10.setBackgroundColor(Color.GRAY)
            selectedTime = "16:30 PM" // 用户选择了时间按钮2（16:30 PM）
        }
        timeButton11.setOnClickListener {
            selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
            selectedTimeButton = timeButton11
            timeButton11.setBackgroundColor(Color.GRAY)
            selectedTime = "17:00 PM" // 用户选择了时间按钮1（17:00 PM）
        }
        timeButton12.setOnClickListener {
            selectedTimeButton?.setBackgroundColor(Color.parseColor("#428FFD"))
            selectedTimeButton = timeButton12
            timeButton12.setBackgroundColor(Color.GRAY)
            selectedTime = "17:30 PM" // 用户选择了时间按钮2（17:30 PM）
        }

        val currentTime = Calendar.getInstance()
        val selectedTimeCalendar = Calendar.getInstance()
//        selectedTimeCalendar.time = selectedDateView // 用户选择的日期
        val currentDate = currentTime.time
        val selectedDate = selectedTimeCalendar.time

        if (selectedDate.before(currentDate)) {
            // 用户选择的日期早于当前日期
            // 显示错误消息，或者执行其他适当的操作
            Toast.makeText(this, "Invalid Date and Time", Toast.LENGTH_SHORT).show()
        } else {
            // 用户选择的日期是将来的日期，可以继续
            // 保存医生姓名和信息到SharedPreferences
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("appointmentDate", formattedDate)
            editor.putString("appointmentTime", selectedTime) // 保存用户选择的时间
            editor.apply()

            val intent = Intent(this@AppointmentDateAndTime, PackageActivity::class.java)
            startActivity(intent)
        }

        val buttonNext = findViewById<Button>(R.id.buttonNext)

        buttonNext.setOnClickListener {
            // 保存医生姓名和信息到SharedPreferences
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("appointmentDate", formattedDate)
            editor.putString("appointmentTime", selectedTime) // 保存用户选择的时间
            editor.apply()

            val intent = Intent(this@AppointmentDateAndTime, PackageActivity::class.java)
            startActivity(intent)
        }
    }
}


