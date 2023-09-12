package com.example.tracking

import android.content.Intent
import android.graphics.Color
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.tracking.adapter.ListViewAdapter
import com.example.tracking.model.ListViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore


class DiseaseActivity : BaseNavigationActivity(){
    var receivedLatLng = LatLng(0.0,0.0)
    private lateinit var db :DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.disease)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Disease"
        initToolbarAndNavigation()
        val intent = intent
        val latitude = intent.getDoubleExtra("latitude", 0.0) // default value 0.0
        val longitude = intent.getDoubleExtra("longitude", 0.0) // default value 0.0
        receivedLatLng = LatLng(latitude, longitude)
        chart()
        list()
        fetchAddress(receivedLatLng)

    }

    fun chart(){
        val lineChart: LineChart = findViewById(R.id.lineChart)
        val customColor = Color.parseColor("#007AFE")


        // Example data
        val entries = ArrayList<Entry>()
        entries.add(Entry(0f, 1f))
        entries.add(Entry(1f, 2f))
        entries.add(Entry(2f, 3f))
        entries.add(Entry(3f, 5f))
        entries.add(Entry(4f, 3f))
        entries.add(Entry(5f, 7f))

        // Create dataset
        val dataSet = LineDataSet(entries, "Sample Data")
        dataSet.color = customColor
        dataSet.valueTextColor = Color.RED
        dataSet.lineWidth = 2.5f
        dataSet.setCircleColor(Color.RED)
        dataSet.circleRadius = 5f
        dataSet.setDrawCircleHole(false)
        dataSet.valueTextSize = 12f

// Design
        lineChart.description.isEnabled = false
        lineChart.setDrawGridBackground(false)
        lineChart.setTouchEnabled(true)
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(true)
        lineChart.setPinchZoom(true)


// Remove right y-axis
        lineChart.axisRight.isEnabled = false
        lineChart.xAxis.isEnabled = false
        lineChart.axisLeft.isEnabled = false

// X-Axis design
        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textSize = 12f
        xAxis.textColor = Color.BLACK
        xAxis.setDrawAxisLine(true)
        xAxis.setDrawGridLines(false)
        xAxis.labelCount = 4
        xAxis.granularity = 1f
        lineChart.xAxis.setDrawGridLines(false)
        lineChart.axisLeft.setDrawGridLines(false)
        xAxis.position = XAxis.XAxisPosition.TOP
        xAxis.isEnabled = true

// Legend design
        val legend = lineChart.legend
        legend.form = Legend.LegendForm.LINE
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER

// Apply data
        val lineData = LineData(dataSet)
        lineChart.data = lineData
        lineChart.invalidate()
    }

    fun list(){
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("your_collection_name").document("your_document_id")
        val listView: ListView = findViewById(R.id.DiseaselistView)

        db.collection("disease").document("Kuala Lumpur")
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot != null && documentSnapshot.exists()) {
                    // Extracting field names from the document
                    val fieldNames = documentSnapshot.data?.keys?.map {
                        ListViewModel.ListItem(it, R.drawable.btn_icon)
                    } ?: listOf()

                    // Set the fetched data to your ListView using your custom adapter

                    val adapter = ListViewAdapter(this, fieldNames)
                    listView.adapter = adapter
                }
            }

//        val items = listOf(
//            ListViewModel.ListItem("Sample text 1", R.drawable.btn_icon),
//            ListViewModel.ListItem("Sample text 2", R.drawable.btn_icon),
//            ListViewModel.ListItem("Sample text 3", R.drawable.btn_icon),
//            ListViewModel.ListItem("Sample text 4", R.drawable.btn_icon),
//            ListViewModel.ListItem("Sample text 5", R.drawable.btn_icon),
//            // ... add more items as needed
//        )
//
//        val adapter = ListViewAdapter(this, items)
//        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, DetailActivity::class.java)

            if (receivedLatLng != null) {
                intent.putExtra("latitude", receivedLatLng.latitude)
                intent.putExtra("longitude", receivedLatLng.longitude)
            }
            startActivity(intent)
        }
        }

    private fun fetchAddress(latLng: LatLng) {
        val geocoder = Geocoder(this)
        try {
            val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            if (addresses != null) {
                if (addresses.isNotEmpty()) {
                    val address = addresses?.get(0)
                    // You can get more details from 'address' as per your requirement
                    if (address != null) {
                        Toast.makeText(this, address.getAddressLine(0), Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Address not found!", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error fetching address. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }


}