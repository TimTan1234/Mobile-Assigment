package com.example.assignment

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class Adapter (private val context: Context, private val dataSource: List<Model.ListItem>): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int = dataSource.size

    override fun getItem(position: Int): Any = dataSource[position]

    override fun getItemId(position: Int): Long = position.toLong()

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = convertView ?: inflater.inflate(R.layout.activity_doctor_list_item, parent, false)

        val doctorNameTextView = rowView.findViewById<TextView>(R.id.doctorname)
        val doctorBioTextView = rowView.findViewById<TextView>(R.id.doctorbio)
        val doctorPicImageView = rowView.findViewById<ImageView>(R.id.doctorProfile)
        val listItem = getItem(position) as Model.ListItem

        doctorNameTextView.text = listItem.doctorName
        doctorBioTextView.text = listItem.doctorBio  // Corrected the attribute name
        if (listItem.imageUrl is String) {
            Glide.with(context)
                .load(listItem.imageUrl as String)
                .into(doctorPicImageView)
        } // Note: We've removed the else case since imageResId should be a string (URL) in this context

        return rowView
    }
}