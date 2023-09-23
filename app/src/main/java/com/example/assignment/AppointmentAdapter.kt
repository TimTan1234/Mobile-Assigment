package com.example.assignment

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar

class AppointmentAdapter(private val appointmentList: List<Appointment>) :
    RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val doctorNameTextView: TextView = itemView.findViewById(R.id.doctorNameTextView)
        val appointmentDateTextView: TextView = itemView.findViewById(R.id.appointmentDateTextView)
        val appointmentTimeTextView: TextView = itemView.findViewById(R.id.appointmentTimeTextView)
        val doctorImageView: ImageView = itemView.findViewById(R.id.doctorImageView)
        val rescheduleButton: Button = itemView.findViewById(R.id.rescheduleButton)
        val cancelButton: Button = itemView.findViewById(R.id.cancelButton)

        // 添加一个属性来存储文档的 ID
        var documentId: String = ""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_appointment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment = appointmentList[position]

        holder.doctorNameTextView.text = appointment.doctorName
        holder.appointmentDateTextView.text = appointment.appointmentDate
        holder.appointmentTimeTextView.text = appointment.appointmentTime
        // 设置文档的 ID
        holder.documentId = appointment.appID


        Glide.with(holder.itemView)
            .load(appointment.doctorProfile)
            .placeholder(R.drawable.profile)
            .error(R.drawable.profile)
            .into(holder.doctorImageView)

        // 添加点击事件处理程序
        holder.itemView.setOnClickListener {
            // 创建一个意图，启动AppointmentDetailsActivity
            val intent = Intent(holder.itemView.context, AppointmentDetails::class.java)

            // 将所选预约的数据传递到AppointmentDetailsActivity
            intent.putExtra("doctorName", appointment.doctorName)
            intent.putExtra("appointmentDate", appointment.appointmentDate)
            intent.putExtra("appointmentTime", appointment.appointmentTime)
            intent.putExtra("doctorProfile", appointment.doctorProfile)
            intent.putExtra("age", appointment.age)
            intent.putExtra("condition", appointment.condition)
            intent.putExtra("gender", appointment.gender)
            intent.putExtra("name", appointment.name)
            intent.putExtra("selectedDuration", appointment.selectedDuration)
            intent.putExtra("selectedPackage", appointment.selectedPackage)
            intent.putExtra("total", appointment.total)
            intent.putExtra("doctorBio", appointment.doctorBio)

            // 启动Activity
            holder.itemView.context.startActivity(intent)
        }

        // 当用户点击要重新安排的预约时
        holder.rescheduleButton.setOnClickListener {
            // 轉去reschedule的頁面
            val intent = Intent(holder.itemView.context, RescheduleAppointmentDateTime::class.java)
            // 获取预约的 Firebase 文档 ID
            val appointmentDocId = holder.documentId
            println(appointmentDocId)
            intent.putExtra("appointmentDocId", appointmentDocId)
            // 启动 Activity
            holder.itemView.context.startActivity(intent)
        }

        holder.cancelButton.setOnClickListener {
            // 创建一个确认对话框
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Cancel Appointment")
                .setMessage("Are you sure you want to cancel this appointment?")
                .setPositiveButton("Yes") { _, _ ->
                    // 用户确认取消预约
                    // 在这里执行取消预约的操作，可以是向服务器发送请求等

                    // 显示Toast消息，表示取消请求已发送
                    Snackbar.make(holder.itemView, "Your cancel request has been sent. Please wait for contact.", Snackbar.LENGTH_LONG).show()
                }
                .setNegativeButton("No", null) // 用户取消取消预约操作
                .show()
        }

    }

    override fun getItemCount(): Int {
        return appointmentList.size
    }
}

