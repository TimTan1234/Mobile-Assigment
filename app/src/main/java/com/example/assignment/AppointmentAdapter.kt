package com.example.assignment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class AppointmentAdapter(private val appointmentList: List<Appointment>) :
    RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val doctorNameTextView: TextView = itemView.findViewById(R.id.doctorNameTextView)
        val appointmentDateTextView: TextView = itemView.findViewById(R.id.appointmentDateTextView)
        val appointmentTimeTextView: TextView = itemView.findViewById(R.id.appointmentTimeTextView)
        val doctorImageView: ImageView = itemView.findViewById(R.id.doctorImageView)
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
    }


    override fun getItemCount(): Int {
        return appointmentList.size
    }
}

