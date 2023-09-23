package com.example.assignment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class PatientDetailsActivity : BaseNavigationActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_details)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        println("hi")
        supportActionBar?.title = "Patient Details"
        initToolbarAndNavigation()

        val genderSpinner = findViewById<Spinner>(R.id.genderSpinner)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextAge = findViewById<EditText>(R.id.editTextAge)
        val editTextCondition = findViewById<EditText>(R.id.editTextCondition)
        val buttonNext = findViewById<Button>(R.id.buttonNext)

        // 初始化SharedPreferences
        sharedPreferences = getSharedPreferences("PatientPrefs", MODE_PRIVATE)

        // 创建一个 ArrayAdapter 以提供选项
        val genderOptions = arrayOf("Female", "Male")

        // 创建适配器并设置它
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genderSpinner.adapter = adapter

        // 设置默认性别选项为 "Female"
        genderSpinner.setSelection(0)

        buttonNext.setOnClickListener {
            // 获取用户输入的信息
            val name = editTextName.text.toString().trim()
            val age = editTextAge.text.toString().trim()
            val condition = editTextCondition.text.toString().trim()
            val selectedGender = genderSpinner.selectedItem.toString()

            // 进行表单验证
            if (name.isEmpty()) {
                showToast("Please enter your name")
                return@setOnClickListener
            }

            if (age.isEmpty()) {
                showToast("Please enter your age")
                return@setOnClickListener
            }

            if (condition.isEmpty()) {
                showToast("Please describe your problem")
                return@setOnClickListener
            }

            // 使用SharedPreferences保存用户输入的信息
            val editor = sharedPreferences.edit()
            editor.putString("name", name)
            editor.putString("age", age)
            editor.putString("condition", condition)
            editor.putString("gender", selectedGender)
            editor.apply()

            // 如果表单验证通过，将用户输入的信息传递给下一个活动
            val intent = Intent(this@PatientDetailsActivity, PaymentActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
