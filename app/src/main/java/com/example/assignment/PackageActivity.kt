package com.example.assignment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioButton
import android.widget.Spinner
import androidx.appcompat.widget.Toolbar

class PackageActivity : BaseNavigationActivity() {
    private lateinit var selectedDuration: String
    private lateinit var selectedPackage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        println("hi")
        supportActionBar?.title = "Appointment Package"
        initToolbarAndNavigation()

        // 获取 Spinner
        val durationSpinner = findViewById<Spinner>(R.id.durationSpinner)

        // 创建一个 ArrayAdapter 以提供选项
        val durationOptions = arrayOf("30 minutes", "1 hour", "1 hour 30 minutes")

        // 创建适配器并设置它
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, durationOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        durationSpinner.adapter = adapter

        // 设置默认选中的值为 "30 minutes"
        val defaultDuration = "30 minutes"
        val defaultDurationPosition = durationOptions.indexOf(defaultDuration)
        durationSpinner.setSelection(defaultDurationPosition)

        // 设置Spinner选择监听器
        durationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedDuration = durationOptions[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 如果没有选择任何项，可以在这里设置默认值
            }
        }


        // 获取 RadioButton 引用
        val emailRadioButton = findViewById<RadioButton>(R.id.emailRadioButton)
        val callRadioButton = findViewById<RadioButton>(R.id.callRadioButton)
        val videoCallRadioButton = findViewById<RadioButton>(R.id.videoCallRadioButton)


        // 设置默认选中的包裹方式为 "Email"
        emailRadioButton.isChecked = true
        selectedPackage = "Email"

        // 设置 RadioButton 点击监听器
        emailRadioButton.setOnClickListener {
            emailRadioButton.isChecked = true
            callRadioButton.isChecked = false
            videoCallRadioButton.isChecked = false
            selectedPackage = "Email"
        }

        callRadioButton.setOnClickListener {
            emailRadioButton.isChecked = false
            callRadioButton.isChecked = true
            videoCallRadioButton.isChecked = false
            selectedPackage = "Call"
        }

        videoCallRadioButton.setOnClickListener {
            emailRadioButton.isChecked = false
            callRadioButton.isChecked = false
            videoCallRadioButton.isChecked = true
            selectedPackage = "Video Call"
        }

        val buttonNext = findViewById<Button>(R.id.buttonNext)

        buttonNext.setOnClickListener {
            // 保存医生姓名和信息到SharedPreferences
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("duration", selectedDuration)
            editor.putString("package", selectedPackage)
            editor.apply()

            val intent = Intent(this@PackageActivity, PatientDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}

