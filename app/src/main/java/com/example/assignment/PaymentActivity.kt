package com.example.assignment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class PaymentActivity : BaseNavigationActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        println("hi")
        supportActionBar?.title = "Payment"
        initToolbarAndNavigation()

        val googlePayRadioButton = findViewById<RadioButton>(R.id.googlePayRadioButton)
        val payPalRadioButton = findViewById<RadioButton>(R.id.paypalRadioButton)
        val tngoWalletRadioButton = findViewById<RadioButton>(R.id.tngoRadioButton)


        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // 设置默认选中的支付方式为 "Google Pay"
        googlePayRadioButton.isChecked = true
        editor.putString("paymentMethod", "Google Pay")
        editor.apply()


        // 设置点击事件监听器来获取用户选择的支付方式
        googlePayRadioButton.setOnClickListener {
            googlePayRadioButton.isChecked = true
            payPalRadioButton.isChecked = false
            tngoWalletRadioButton.isChecked = false
            editor.putString("paymentMethod", "Google Pay")
            editor.apply()
        }

        payPalRadioButton.setOnClickListener {
            googlePayRadioButton.isChecked = false
            payPalRadioButton.isChecked = true
            tngoWalletRadioButton.isChecked = false
            editor.putString("paymentMethod", "PayPal")
            editor.apply()
        }

        tngoWalletRadioButton.setOnClickListener {
            googlePayRadioButton.isChecked = false
            payPalRadioButton.isChecked = false
            tngoWalletRadioButton.isChecked = true
            editor.putString("paymentMethod", "Tngo Wallet")
            editor.apply()
        }
        val buttonNext = findViewById<Button>(R.id.buttonNext)

        buttonNext.setOnClickListener {
            val intent = Intent(this@PaymentActivity, AppointmentReviewSummary::class.java)
            startActivity(intent)
        }
    }
}